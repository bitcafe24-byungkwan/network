package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		int portNum = 5000;

		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding) : Socket에 SocketAddress(IP addr + port) 바인딩
			InetAddress inetAddr = InetAddress.getLocalHost();
//			String localhost = inetAddr.getHostAddress();
//			serverSocket.bind(new InetSocketAddress(localhost, portNum));

			serverSocket.bind(new InetSocketAddress("0.0.0.0", portNum));

			// 3. accept : 클라이언트의 연결 요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress remoteInetAddr = (InetSocketAddress) socket.getRemoteSocketAddress();
			System.out.println("[server]connected by client - " + remoteInetAddr.getAddress().getHostAddress() + ":"
					+ remoteInetAddr.getPort());

			// 4. IO 스트림 받아오기
			try {
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); //blocking
					if(readByteCount == -1) {
						//클라이언트가 정상종료한 경우
						//close() 메소드 호출을 통해서 
						System.out.println("[server] closed by client");
						break;
					}					
					String data = new String(buffer, 0, readByteCount, "utf-8");
					System.out.println("[server] received:"+data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8"));
				}
			} catch (SocketException e){
				System.out.println("[server] sudden closed by client");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(socket != null && !socket.isClosed())
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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

}
