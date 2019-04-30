package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPTimeServer {

	public static final int PORT = 7000;
	public static final int BUFF_SIZE = 1024;

	public static void main(String[] args) {

		DatagramSocket sock = null;

		try {
			// 1. sock 생성
			sock = new DatagramSocket(PORT);

			while (true) {
				// 2. 데이터 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFF_SIZE], BUFF_SIZE);
				sock.receive(receivePacket);

				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String msg = new String(data,0,length,"UTF-8");
				
				
				
				// 3. 데이터 전송
				byte[] sendData;
				if (msg.length() == 0)
				{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
					sendData = format.format( new Date()).getBytes("utf-8");
					System.out.println("[server] received: time request");
				}
				else
				{
					sendData = msg.getBytes("utf-8");
					System.out.println("[server] received:" + msg);
				}
				DatagramPacket sendPacket =
						new DatagramPacket(sendData, sendData.length,
								receivePacket.getAddress(),
								receivePacket.getPort());
				sock.send(sendPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sock != null && !sock.isClosed())
				sock.close();
		}

	}

}
