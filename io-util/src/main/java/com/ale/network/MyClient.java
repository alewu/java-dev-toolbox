package com.ale.network;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class MyClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String serverName = sc.nextLine();
        int port = sc.nextInt();
        try {
            log.info("连接到主机：{}，端口号：{}", serverName, port);
            Socket client = new Socket(serverName, port);
            log.info("远程主机地址：{}", client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            String msgContent = sc.nextLine();
            out.writeUTF("Hello from " + client.getLocalSocketAddress() + msgContent);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            log.info("服务器响应： {}", in.readUTF());
            //            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
