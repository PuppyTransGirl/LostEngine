package dev.lost.furnace.files.texture;

import dev.lost.furnace.files.ResourcePackFile;

import java.io.File;
import java.io.IOException;

public class TextureImpl implements Texture {

    private final ResourcePackFile file;

    public TextureImpl(ResourcePackFile file) {
        this.file = file;
    }

    public TextureImpl(String path, File file) throws IOException {
        this.file = new ResourcePackFile(path, file);
    }

    public TextureImpl(String path, byte[] bytes) {
        this.file = new ResourcePackFile(path, bytes);
    }

    public String path() {
        return file.getPath();
    }

    public ResourcePackFile file() {
        return file;
    }
}
