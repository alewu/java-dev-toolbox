package com.ale.network;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

/**
 * @author alewu
 * @date 2020/9/21
 */
@Slf4j
public class MyServer extends Thread {

    private ServerSocket serverSocket;

    public MyServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("等待远程连接，端口号为：{} ...", serverSocket.getLocalPort());
                Socket server = serverSocket.accept();
                log.info("远程主机地址：{}", server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                log.info(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");

            } catch (SocketTimeoutException e) {
                log.error("Socket timed out!", e);
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int port = sc.nextInt();
        try {
            Thread t = new MyServer(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
