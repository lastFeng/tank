package netty.embededtest.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import netty.embededtest.handler.FixedLengthFrameDecoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/24 14:48
 * @description:
 */
public class FixedLengthFrameDecoderTest {
    @Test
    public void testFrameDecoder() {
        ByteBuf writeBuf = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            writeBuf.writeByte(i);
        }

        ByteBuf readBuf = writeBuf.duplicate();

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));

        // write message, 向内置的channel中写入9个字节的数据
        assertTrue(embeddedChannel.writeInbound(readBuf.retain()));
        // 结束写入
        assertTrue(embeddedChannel.finish());

        // read message， 开始读取内容，每读一次，读取三个字节的内容，与writeBuf中的内容相比较
        ByteBuf read = (ByteBuf)embeddedChannel.readInbound();
        assertEquals(writeBuf.readSlice(3), read);
        // 更新读指针内容
        read.release();

        // 读取三次后，buf内的读指针指向空值
        read = (ByteBuf)embeddedChannel.readInbound();
        assertEquals(writeBuf.readSlice(3), read);
        read.release();

        read = (ByteBuf)embeddedChannel.readInbound();
        assertEquals(writeBuf.readSlice(3), read);
        read.release();

        assertNull(embeddedChannel.readInbound());
        writeBuf.release();
    }

    @Test
    public void testFrameDecoder2() {
        ByteBuf buf = Unpooled.buffer();

        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
//        assertFalse(channel.writeInbound(input.readBytes(2)));
//        assertTrue(channel.writeInbound(input.readBytes(7)));
        assertTrue(channel.writeInbound(input.readBytes(3)));
        assertTrue(channel.writeInbound(input.readBytes(6)));
        assertTrue(channel.finish());

        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();

    }
}
