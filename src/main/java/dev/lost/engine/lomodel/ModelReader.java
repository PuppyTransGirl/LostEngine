package dev.lost.engine.lomodel;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModelReader {

    private static final Gson GSON = new Gson();

    public static @NotNull Model read(@NotNull InputStream input) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input))) {
            return GSON.fromJson(bufferedReader, ModelImpl.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Lost Engine model from input stream", e);
        }
    }

    public static @NotNull Model read(@NotNull Path path) {
        try (InputStream input = Files.newInputStream(path)) {
            return read(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Lost Engine model from path: " + path, e);
        }
    }

}
