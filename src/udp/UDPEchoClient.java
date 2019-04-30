package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP = "192.168.1.63";

	public static void main(String[] args) {
		Scanner scanner = null;
		DatagramSocket sock = null;

		try {
			// 1. Scanner 생성(표준 입력 연결)
			scanner = new Scanner(System.in);
			// 2. 소켓 생성
			sock = new DatagramSocket();

			log("connected");
			while (true) {
				// 5. 키보드 입력받기
				System.out.print(">>");
				String line = scanner.nextLine();
				if ("quit".contentEquals(line))
					break;

				// 6. 데이터 쓰기
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT));
				sock.send(sendPacket);

				// 7. 데이터 읽기
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFF_SIZE],
						UDPEchoServer.BUFF_SIZE);
				sock.receive(receivePacket);
				String msg = new String(receivePacket.getData(), 0, receivePacket.getLength(), "utf-8");
				// 8. 콘솔 출력
				System.out.println("<<" + msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (scanner != null)
				scanner.close();
			if (sock != null && !sock.isClosed())
				sock.close();
		}
	}

	public static void log(String log) {
		System.out.println("[client] " + log);
	}

}
