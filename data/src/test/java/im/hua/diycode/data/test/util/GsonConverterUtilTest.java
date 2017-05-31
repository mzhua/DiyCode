package im.hua.diycode.data.test.util;

import com.google.gson.JsonSyntaxException;

import org.junit.Test;

import java.util.List;

import im.hua.diycode.data.entity.NewsEntity;
import im.hua.diycode.data.util.GsonConverterUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hua on 2017/2/21.
 */

public class GsonConverterUtilTest {

    private static final String NEWS_JSON_OBJECT_STRING = "{\n" +
            "    \"id\": 2009,\n" +
            "    \"title\": \"20个很棒的android开源项目帮助你提升开发技能\",\n" +
            "    \"created_at\": \"2017-02-21T09:42:15.452+08:00\",\n" +
            "    \"updated_at\": \"2017-02-21T09:45:29.674+08:00\",\n" +
            "    \"user\": {\n" +
            "      \"id\": 3789,\n" +
            "      \"login\": \"tangmanong\",\n" +
            "      \"name\": \"唐码农\",\n" +
            "      \"avatar_url\": \"https://diycode.cc/system/letter_avatars/2/T/163_163_163/240.png\"\n" +
            "    },\n" +
            "    \"node_name\": \"Android\",\n" +
            "    \"node_id\": 1,\n" +
            "    \"last_reply_user_id\": null,\n" +
            "    \"last_reply_user_login\": null,\n" +
            "    \"replied_at\": null,\n" +
            "    \"address\": \"http://mp.weixin.qq.com/s/0YyuvWQH-uT-Pp9pOhtePw\",\n" +
            "    \"replies_count\": 0\n" +
            "  }";

    private static final String NEWS_JSON_ARRAY_STRING = "[" + NEWS_JSON_OBJECT_STRING + "]";

    @Test
    public void testJsonArrayParse() {
        List<NewsEntity> newsEntities = GsonConverterUtil.jsonArrayParse(NewsEntity.class, NEWS_JSON_ARRAY_STRING);
        assertNotNull(newsEntities);
        assertEquals(1, newsEntities.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testJsonArrayParseWithEmptyJsonString() {
        GsonConverterUtil.jsonArrayParse(NewsEntity.class, "");
    }

    @Test(expected = IllegalStateException.class)
    public void testJsonArrayParseWithErrorJsonString() {
        GsonConverterUtil.jsonArrayParse(NewsEntity.class, "asdjklasdf");
    }

    @Test(expected = JsonSyntaxException.class)
    public void testJsonArrayParseWithErrorJsonElement() {
        GsonConverterUtil.jsonArrayParse(NewsEntity.class, "{title:}");
    }

    @Test
    public void testJsonObjectParse_success() {
        NewsEntity newsEntity = GsonConverterUtil.jsonObjectParse(NewsEntity.class, NEWS_JSON_OBJECT_STRING);
        assertNotNull(newsEntity);
        assertEquals(2009,newsEntity.getId());
        assertEquals("20个很棒的android开源项目帮助你提升开发技能",newsEntity.getTitle());
        assertEquals("Android",newsEntity.getNode_name());
    }

    @Test(expected = JsonSyntaxException.class)
    public void testJsonObjectParse_wrongSyntax() {
        GsonConverterUtil.jsonObjectParse(NewsEntity.class, "asdf");
    }
}
