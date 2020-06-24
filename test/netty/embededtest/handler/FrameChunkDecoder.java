package netty.embededtest.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/24 15:27
 * @description:
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {
    private final int maxSize;

    public FrameChunkDecoder(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int read = in.readableBytes();

        if (read > this.maxSize) {
            in.clear();
            throw new TooLongFrameException();
        }

        ByteBuf buf = in.readBytes(read);
        out.add(buf);
    }
}
