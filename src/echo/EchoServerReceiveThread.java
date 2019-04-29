package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket _sock;
	
	public EchoServerReceiveThread(Socket socket) {
		_sock = socket;
	}

	@Override
	public void run() {
		InetSocketAddress remoteInetAddr = (InetSocketAddress) _sock.getRemoteSocketAddress();
		EchoServer.log("connected by client - " + remoteInetAddr.getAddress().getHostAddress() + ":"
				+ remoteInetAddr.getPort());

		try {
			// 4. IO 스트림 받아오기
			InputStream is = _sock.getInputStream();
			OutputStream os = _sock.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));				
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(os, "utf-8"), true);
			
			while (true) {
				// 5. 데이터 읽기					
				String data = br.readLine();
				if(data == null) {
					EchoServer.log("closed by client");
					break;
				}							
				
				EchoServer.log("received:"+data);
				
				// 6. 데이터 쓰기
				pr.println(data);
			}
		} catch (SocketException e){
			EchoServer.log("sudden closed by client");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(_sock != null && !_sock.isClosed())
					_sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
