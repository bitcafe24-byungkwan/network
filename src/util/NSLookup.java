package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(System.in, "utf-8");
			
			br = new BufferedReader(isr);
			String line =null;
			while(true)
			{
				System.out.print(">");
				line = br.readLine();
				if("exit".compareToIgnoreCase(line) == 0)
					break;
				nslookup(line);
			}
		} catch (Exception e){
			System.out.println("[ERR]" + e.getMessage());
		} finally {
			if(br != null)
			{
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	static void nslookup(String hostName)
	{
		InetAddress[] inetAddrs = null;
		try {
			inetAddrs = InetAddress.getAllByName(hostName);
			
			for(InetAddress addr : inetAddrs) 
			{
				System.out.println(hostName +" : "+addr.getHostAddress());
			}
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println("[ERR] UnkownHostException");
		}
	}

}
