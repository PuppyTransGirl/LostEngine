import path from "path"
import {defineConfig} from 'vite'
import preact from '@preact/preset-vite'
import {viteSingleFile} from "vite-plugin-singlefile"

export default defineConfig({
    plugins: [preact(), viteSingleFile()],
    build: {
        minify: "terser",
        terserOptions: {
            compress: {
                drop_console: true,
                drop_debugger: true,
            },
            mangle: true,
            format: {
                comments: false,
            },
            toplevel: true,
        },
    },
    resolve: {
        alias: {
            "@": path.resolve(__dirname, "./src"),
        },
    },
})
