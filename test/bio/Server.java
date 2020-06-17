/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo Weifeng
 * @version: 1.0
 * @create: 2020/6/17 10:59
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress("localhost", 8888));
        boolean started = true;

        while (started) {
            // 阻塞，只有等客户端连接，才能继续
            Socket accept = socket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            //while (read != -1) {
            //    System.out.println(read);
            //    read = Integer.valueOf(reader.readLine());
            //}
            System.out.println(reader.readLine());
            accept.close();
        }

        socket.close();
    }
}
