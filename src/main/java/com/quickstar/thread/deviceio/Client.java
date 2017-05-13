package com.quickstar.thread.deviceio;

import java.io.*;
import java.net.Socket;

/**
 * @Author:lzq
 * @Discription Send file to server
 * @Date: Created on 2017/4/21.
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) throws IOException {
        int port = 1014;
        Socket socket = new Socket("localhost", port);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
//        PersonMsg.Person.Builder person = PersonMsg.Person.newBuilder();
//        person.setId(12);
//        person.setName("Tom");
//        person.setEmail("sdf");
//        person.addFriends("Jack");
//        person.addFriends("Tony");
//        PersonMsg.Person p = person.build();
//        // 将数据写到输出流，如网络输出流，这里就用ByteArrayOutputStream来代替
////        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        p.writeTo(out);
        String str = "[3G*4700299477*0C1A*TK,this is AMR file test]";
        String str2 = "[3G*4700299477*0C1A*LK,this is AMR file test]";
        out.write(str2.getBytes());
        out.flush();
        byte[] bytes = new byte[1024];
        in.read(bytes);

        System.out.println(new String(bytes));

    }
}
