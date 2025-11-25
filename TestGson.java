import com.google.gson.*;

public class TestGson {
    public static void main(String[] args) {
        JsonObject o = new JsonObject();
        o.addProperty("hello", "world");
        System.out.println(o.toString());
    }
}
