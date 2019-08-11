package src.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;

public class Test {
    public static void main(String args[]) throws Exception {
    }

    @org.junit.Test
    public void testSocket() throws IOException{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 80), 5000);
        ServerSocket serverSocket = new ServerSocket(8080);
        serverSocket.accept();
    }

    @org.junit.Test
    public void testInet() throws UnknownHostException {
        System.out.println(InetAddress.getByName("www.baidu.com"));
        System.out.println(InetAddress.getAllByName("www.baidu.com").length);
        System.out.println(InetAddress.getLocalHost());
    }

    @org.junit.Test
    public void testURLConnection() throws IOException {
        URL url = new URL("http://localhost/getVideoFrame");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.println("time="+1);
        out.close();
        String res = "";
        byte[] bytes = new byte[1024];
        while (connection.getInputStream().read(bytes) != -1){
            res += new String(bytes);
            bytes = new byte[1024];
        }
        System.out.println(res);
    }

    @org.junit.Test
    public void testNetworkInterface() {
        try {
            Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
            while (networkInterface.hasMoreElements()) {
                NetworkInterface eachNetworkInterface = networkInterface.nextElement();
                System.out.println("getName获得网络设备名称=" + eachNetworkInterface.getName());
                System.out.println("getDisplayName获得网络设备显示名称=" + eachNetworkInterface.getDisplayName());
                System.out.println("getIndex获得网络接口的索引=" + eachNetworkInterface.getIndex());
                System.out.println("isUp是否已经开启并运行=" + eachNetworkInterface.isUp());
                System.out.println("isLoopback是否为回调接口=" + eachNetworkInterface.isLoopback());
                System.out.println("getMTU获得最大传输单元=" + eachNetworkInterface.getMTU());
                System.out.println();
                System.out.println();
            }
        }catch (SocketException e){
            e.printStackTrace();
        }
    }
}
