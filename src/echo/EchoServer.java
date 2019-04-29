package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
	private static final int PORT = 7000;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding) : Socket에 SocketAddress(IP addr + port) 바인딩
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT));
			log("server starts    PORT : " + PORT);
			while (true) {
				// 3. accept : 클라이언트의 연결 요청을 기다린다.
				Socket socket = serverSocket.accept(); // blocking

				Thread acceptThread = new EchoServerReceiveThread(socket);
				acceptThread.start();
			}


		} catch (IOException e) {
			// 발생 위험 낮음
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String log) {
		System.out.println("[server# " +Thread.currentThread().getId()+"] " + log);
	}

}
