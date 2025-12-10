package dev.lost.furnace.files.model;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface Model {

    @NotNull JsonObject toJson();

    String path();

    String parent();

    Model parent(String parent);

    GuiLight guiLight();

    Model guiLight(GuiLight guiLight);

    Map<String, String> textures();

    Model texture(String key, String value);

    List<Element> elements();

    Model element(Element element);

    Display display();

    Model display(Display display);

    Boolean ambientocclusion();

    Model ambientocclusion(Boolean ambientocclusion);

    @Contract(value = "_, _ -> new", pure = true)
    static @NotNull Model model(JsonObject json, String path) {
        return new ModelImpl(json, path);
    }

    @Contract(value = "_ -> new", pure = true)
    static @NotNull Model model(String path) {
        return new ModelImpl(path);
    }

    enum GuiLight {
        FRONT,
        SIDE
    }

    interface Element {

        @Contract(value = " -> new", pure = true)
        static @NotNull Element element() {
            return new ModelImpl.ElementImpl();
        }

        int[] from();

        int[] to();

        Map<Face.FaceType, Face> faces();

        Rotation rotation();

        Boolean shade();

        Integer lightEmission();

        Element from(int[] from);

        Element to(int[] to);

        Element face(Face.FaceType faceType, Face face);

        Element rotation(Rotation rotation);

        Element shade(Boolean shade);

        Element lightEmission(Integer lightEmission);

        interface Rotation {

            @Contract(value = " -> new", pure = true)
            static @NotNull Rotation rotation() {
                return new ModelImpl.ElementImpl.RotationImpl();
            }

            int[] origin();

            Boolean rescale();

            Axis axis();

            int angle();

            Rotation origin(int[] origin);

            Rotation rescale(Boolean rescale);

            Rotation axis(Axis axis);

            Rotation angle(int angle);

            enum Axis {
                X,
                Y,
                Z
            }
        }

        interface Face {

            @Contract(value = " -> new", pure = true)
            static @NotNull Face face() {
                return new ModelImpl.ElementImpl.FaceImpl();
            }

            enum FaceType {
                DOWN,
                UP,
                NORTH,
                SOUTH,
                WEST,
                EAST
            }

            String texture();

            int[] uv();

            FaceType cullface();

            Integer rotation();

            Integer tintindex();

            Face texture(String texture);

            Face uv(int[] uv);

            Face cullface(FaceType cullface);

            Face rotation(Integer rotation);

            Face tintindex(Integer tintindex);
        }

    }

    interface Display extends Map<Display.DisplayType, Display.Transform> {

        @Contract(value = " -> new", pure = true)
        static @NotNull Display display() {
            return new ModelImpl.DisplayImpl();
        }

        enum DisplayType {
            FIRSTPERSON_RIGHTHAND,
            FIRSTPERSON_LEFTHAND,
            THIRDPERSON_RIGHTHAND,
            THIRDPERSON_LEFTHAND,
            GUI,
            HEAD,
            GROUND,
            FIXED,
            ON_SHELF
        }

        interface Transform {

            @Contract(value = " -> new", pure = true)
            static @NotNull Transform transform() {
                return new ModelImpl.DisplayImpl.TransformImpl();
            }

            int[] rotation();

            int[] translation();

            int[] scale();

            Transform rotation(int[] rotation);

            Transform translation(int[] translation);

            Transform scale(int[] scale);
        }

    }
}
