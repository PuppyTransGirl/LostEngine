package dev.lost.furnace.utils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PngOptimizer {

    public static @Nullable Path EXE;

    public static byte[] optimise(byte[] png) {
        if (EXE == null || !Files.exists(EXE)) {
            return png;
        }
        try {
            Path in = Files.createTempFile("orig", ".png");
            Path out = Files.createTempFile("opt", ".png");
            Files.write(in, png);

            new ProcessBuilder(EXE.toString(), "-o", "max", "-s", "-a", "-Z", "--out", out.toString(), in.toString())
                    .redirectError(ProcessBuilder.Redirect.DISCARD)
                    .redirectOutput(ProcessBuilder.Redirect.DISCARD)
                    .start()
                    .onExit()
                    .get();

            byte[] bytes = Files.readAllBytes(out);
            Files.deleteIfExists(in);
            Files.deleteIfExists(out);

            return bytes.length < png.length ? bytes : png;

        } catch (Exception ignored) {
            return png;
        }
    }

    public static void downloadOxipng(@NotNull Path target) throws IOException {
        Os os = Os.current();
        if (Files.isDirectory(target)) {
            String file = os == Os.WINDOWS ? "oxipng.exe" : "oxipng";
            target = target.resolve(file);
        } else if (Files.exists(target)) {
            target.toFile().setExecutable(true, false);
            EXE = target;
            return;
        }

        String release = "v9.1.5";
        URI archiveUri = URI.create("https://github.com/shssoichiro/oxipng/releases/download/" + release + "/" + switch (os) {
            case WINDOWS -> "oxipng-9.1.5-x86_64-pc-windows-msvc.zip";
            case LINUX -> "oxipng-9.1.5-x86_64-unknown-linux-musl.tar.gz";
            case MACOS -> "oxipng-9.1.5-x86_64-apple-darwin.tar.gz";
        });

        Path tmp = Files.createTempFile("oxipng-dl", extensionOf(archiveUri));

        try (InputStream inputStream = archiveUri.toURL().openStream()) {
            Files.copy(inputStream, tmp, StandardCopyOption.REPLACE_EXISTING);
        }

        String wanted = os == Os.WINDOWS ? "oxipng.exe" : "oxipng";
        boolean found = false;

        if (archiveUri.toString().endsWith(".zip")) {
            try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tmp))) {
                ZipEntry ze;
                while ((ze = zis.getNextEntry()) != null) {
                    if (ze.getName().endsWith(wanted)) {
                        Files.copy(zis, target, StandardCopyOption.REPLACE_EXISTING);
                        found = true;
                        break;
                    }
                }
            }
        } else {
            try (TarArchiveInputStream tar = new TarArchiveInputStream(new GzipCompressorInputStream(Files.newInputStream(tmp)))) {
                TarArchiveEntry tarArchiveEntry;
                while ((tarArchiveEntry = tar.getNextEntry()) != null) {
                    if (tarArchiveEntry.getName().endsWith(wanted)) {
                        Files.copy(tar, target, StandardCopyOption.REPLACE_EXISTING);
                        found = true;
                        break;
                    }
                }
            }
        }

        Files.deleteIfExists(tmp);
        if (!found) throw new FileNotFoundException("oxipng binary not found inside archive");

        target.toFile().setExecutable(true, false);
        EXE = target;
    }

    @NotNull
    private static String extensionOf(@NotNull URI uri) {
        String path = uri.getPath();
        return path.substring(path.lastIndexOf('.'));
    }

    public enum Os {
        WINDOWS, LINUX, MACOS;

        public static Os current() {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) return WINDOWS;
            if (os.contains("mac")) return MACOS;
            if (os.contains("nix") || os.contains("nux") || os.contains("aix")) return LINUX;
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }
    }

}