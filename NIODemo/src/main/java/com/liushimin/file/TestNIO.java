<<<<<<< HEAD
package com.liushimin.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>标题: </p>
 * <p>功能描述: 通过NIO实现文件IO</p>
 *
 * <p>创建时间: 2019/8/8 8:45</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestNIO {

    @Test
    public void test1() throws Exception {
        FileOutputStream fos = new FileOutputStream("basic.txt");
        FileChannel channel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String msg = "Hello,NIO";
        buffer.put(msg.getBytes());
        buffer.flip();
        channel.write(buffer);

        fos.close();
    }

    @Test
    public void test2() throws Exception {
        File file = new File("basic.txt");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        channel.read(buffer);
        System.out.println(new String(buffer.array()));
        fis.close();
    }

    @Test
    public void test3() throws Exception {
        File file = new File("basic.txt");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("d:\\basic.txt");
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        //fisChannel.transferTo(0, fisChannel.size(), fosChannel);
        fosChannel.transferFrom(fisChannel, 0, fisChannel.size());

        fis.close();
        fos.close();
    }
}
=======
package com.liushimin.file;

/**
 * <p>标题: </p>
 * <p>功能描述: </p>
 *
 * <p>创建时间: 2019/8/8 8:45</p>
 * <p>作者：lshim</p>
 * <p>修改历史记录：</p>
 * ====================================================================<br>
 */
public class TestNIO {


}
>>>>>>> parent of 2edf08d... Netty 练习
