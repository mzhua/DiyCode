package im.hua.diycode.data.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 2016/12/9.
 */

public class GsonConverterUtil {
    private static Gson mGson = new Gson();

    /**
     * json array 转换为list
     *
     * @param clazz
     * @param value
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonArrayParse(Class<T> clazz, String value) throws IllegalStateException,JsonSyntaxException {
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(value).getAsJsonArray();

        List<T> lcs = new ArrayList<T>();

        for (JsonElement obj : Jarray) {
            T cse = mGson.fromJson(obj, clazz);
            lcs.add(cse);
        }

        return lcs;
    }

    public static <T> T jsonObjectParse(Class<T> clazz, String value) throws JsonSyntaxException{
        return mGson.fromJson(value, clazz);
    }

    /*public static <T> List<T> read(Class<T> type, JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        List<T> list = new ArrayList<>();
        in.beginArray();
        TypeAdapter<T> adapter = mGson.getAdapter(type);
        while (in.hasNext()) {
            T instance = adapter.read(in);
            list.add(instance);
        }
        in.endArray();

        return list;
    }*/
}
