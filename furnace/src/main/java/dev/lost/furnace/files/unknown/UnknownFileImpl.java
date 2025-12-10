package dev.lost.furnace.files.unknown;

import dev.lost.furnace.files.ResourcePackFile;

import java.io.File;
import java.io.IOException;

public class UnknownFileImpl implements UnknownFile {

    private final String path;
    private final ResourcePackFile file;

    public UnknownFileImpl(String path, ResourcePackFile file) {
        this.path = path;
        this.file = file;
    }

    public UnknownFileImpl(String path, File file) throws IOException {
        this.path = path;
        this.file = new ResourcePackFile(path, file);
    }

    public UnknownFileImpl(String path, byte[] bytes) {
        this.path = path;
        this.file = new ResourcePackFile(path, bytes);
    }


    public UnknownFileImpl(String path, String utf8Text) {
        this.path = path;
        this.file = new ResourcePackFile(path, utf8Text);
    }

    public String path() {
        return path;
    }

    public ResourcePackFile file() {
        return file;
    }
}
