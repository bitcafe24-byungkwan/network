package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddr = InetAddress.getLocalHost();
			String hostName = inetAddr.getHostName();
			String hostAddr = inetAddr.getHostAddress();
			
			byte[] addrs = inetAddr.getAddress();
			for (byte addr : addrs)
			{
				System.out.print(addr & 0x000000ff);				
				System.out.print('.');
			}
			
			
			
			
			String tempStr = String.format("%s : %s",
					hostName, hostAddr);
			System.out.println(tempStr);
			
			
			
//			
//			InetAddress[] inetAddrs= InetAddress.getAllByName("");
//			for(InetAddress addr : inetAddrs) 
//			{
//				System.out.println(addr.getHostAddress());
//			}
			
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
