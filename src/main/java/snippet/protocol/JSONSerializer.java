package snippet.protocol;

import com.google.gson.Gson;


import static java.nio.charset.StandardCharsets.UTF_8;
import static snippet.protocol.SerializerAlgorithm.JSON;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/15
 * @since
 */
public class JSONSerializer implements Serializer {
    private static final Gson gson = new Gson();

    @Override
    public byte getSerializerAlgorithm() {
        return JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return gson.toJson(object).getBytes(UTF_8);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return gson.fromJson(new String(bytes, UTF_8), clazz);
    }
}
