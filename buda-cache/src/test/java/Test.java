import java.nio.ByteBuffer;

/**
 * @author : zhanghuang
 * @date : 2022-01-05 18:43
 */
public class Test {
    public static void main(String[] args) {
        // 准备出10个大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocateDirect(10);
        // 设置内容
        byte[] temp = { 1, 3, 5, 7, 9,11,13,15,17,19};
        // 设置一组内容
        buf.put(temp);
        buf.flip();
        System.out.print("主缓冲区中的内容：");
        while (buf.hasRemaining()) {
            int x = buf.get();
            System.out.print(x + "、");
        }
    }
}
