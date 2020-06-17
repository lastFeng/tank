package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/17 21:34
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    SocketChannel sc = SocketChannel.open();
                    sc.configureBlocking(false);
                    Selector selector = Selector.open();
                    sc.register(selector, SelectionKey.OP_CONNECT);
                    sc.connect(new InetSocketAddress("localhost", 8888));
                    boolean started = true;

                    while (started) {
                        int event = selector.select();
                        if (event > 0) {
                            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                            while (iterator.hasNext()) {
                                SelectionKey key = iterator.next();
                                iterator.remove();
                                if (key.isConnectable()) {
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    if (channel.isConnectionPending()) {
                                        channel.finishConnect();
                                    }

                                    channel.configureBlocking(false);
                                    channel.register(key.selector(), SelectionKey.OP_READ);
                                    channel.write(ByteBuffer.wrap(("Hello Server" + Thread.currentThread().getName()).getBytes()));
                                } else if (key.isReadable()) {
                                    SocketChannel channel = (SocketChannel) key.channel();
                                    ByteBuffer buffer = ByteBuffer.allocate(512);
                                    buffer.clear();
                                    int len = channel.read(buffer);
                                    if (len > 0) {
                                        System.out.println("收到服务端的内容：" + new String(buffer.array(), 0, len));
//                                        started = false;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
