package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	private static final String SERVER_IP = "192.168.1.63";
	private static final int SERVER_PORT = 5000;

	public static void main(String[] args) {
		Socket sock = null;

		try {
			// 1. 소켓 생성
			sock = new Socket();
			// 1-1. 소켓 배포 사이즈 확인
			int receiveBufferSize = sock.getReceiveBufferSize();
			int sendBufferSize = sock.getSendBufferSize();
			System.out.println(receiveBufferSize + " : " + sendBufferSize);

			// 1-2. 소켓 버퍼 사이즈 변경
			sock.setReceiveBufferSize(1024 * 10);
			sock.setSendBufferSize(1024 * 10);
			receiveBufferSize = sock.getReceiveBufferSize();
			sendBufferSize = sock.getSendBufferSize();
			System.out.println(receiveBufferSize + " : " + sendBufferSize);

			// 1-3. SO_NODELAY(Nagle algorithm off)
			sock.setTcpNoDelay(true);

			// 1-4. SO_TIMEOUT
			sock.setSoTimeout(1000);
			// 2. 서버 연결
			sock.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] connected");
			// 3. IOStream 받아오기
			InputStream is = sock.getInputStream();
			OutputStream os = sock.getOutputStream();

			// 4. 쓰기
			String data = "Hello World\n";
			os.write(data.getBytes("utf-8"));

			// 5. 읽기
			byte[] buff = new byte[256];
			int readByteCount = is.read(buff);
			if (readByteCount == -1) {
				System.out.println("[client] closed by server");
			}

			data = new String(buff, 0, readByteCount, "utf-8");
			System.out.println("[Client] received : " + data);

		} catch (SocketTimeoutException e) {
			System.out.println("[Client] timeout");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sock != null && !sock.isClosed())
					sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
