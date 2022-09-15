package protocol;

/**
 * 序列化接口
 *
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/9/15
 * @since
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 对象序列化成 Java 原生的 byte 数组
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据反序列化成对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
