package com.quickstar.thread.deviceio;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @Author:lzq
 * @Discription
 * @Date: Created on 2017/4/25.
 * @Modified By:
 */
public class TraditionalClient {
    public static void main(String[] args) throws IOException {
        int port = 1014;
        Socket socket = new Socket("localhost", port);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        boolean flag = true;

        //write message to server
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = null;
                while (true) {
                    try {
                        str = keyboardIn.readLine();
                        if (str.equals("bye")) {
                            socket.close();
                            System.out.println("client exit");
                            break;
                        }
                        byte[] toserver = str.getBytes();
                        out.write(toserver);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        //Read message from server
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (socket.isClosed()) {
                            System.out.println("From server exit");
                            break;
                        }
                        byte[] fromserver = new byte[1024];
                        if (in.read(fromserver) > 0) {
                            System.out.println("from server: " + new String(fromserver));
                        }

                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


//        deviceio.close();

    }
}
