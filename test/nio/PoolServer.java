package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/17 21:31
 * @description:
 */
public class PoolServer {
    private int port;
    private String ip;
    private Selector selector;
    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        new PoolServer("localhost", 8888).start();
    }

    public PoolServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void init() {
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(ip, port));
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NioPoolServer started at: " + ssc.getLocalAddress());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel channel = ssc.accept();
            channel.configureBlocking(false);
            channel.register(this.selector, SelectionKey.OP_READ);
            System.out.println("Accept a client: " + channel.socket().getInetAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void start() {
        this.init();

        while (true) {
            try {
                int event = selector.select();

                if (event > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isAcceptable()) {
                            this.accept(key);
                        } else {
                            service.submit(new NioServerHandler(key));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class NioServerHandler implements Runnable{

        private SelectionKey key;

        public NioServerHandler(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            SocketChannel channel = null;
            try {
                if (key.isReadable()) {
                    channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    int len = channel.read(buffer);
                    if (len > 0) {
                        System.out.println("收到客户端消息：" + new String(buffer.array(), 0, len));
                    }

                    channel.write(ByteBuffer.wrap(("返回给客户端消息").getBytes()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
