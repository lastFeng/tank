package netty.embededtest.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import netty.embededtest.handler.AbsIntegerEncoder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/24 15:16
 * @description:
 */
public class AbsIntegerEncoderTest {

    @Test
    public void encoderTest() {
        ByteBuf buf = Unpooled.buffer();

        for (int i = 0; i < 12; i++) {
            buf.writeInt(i * -1);
        }



        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(channel.writeOutbound(buf));
        assertTrue(channel.finish());

        // read byte
        for (int i = 0; i < 12; i++) {
            assertEquals(i, (int) channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}
