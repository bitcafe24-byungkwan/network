package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PhoneList01 {

	public static void main(String[] args) {

		//기반스트림
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {			
			//보조스트림1(bytes -> char)
			isr = new InputStreamReader(new FileInputStream("phone.txt"), "utf-8");
			
			br = new BufferedReader(isr);
			String line =null;
			while((line = br.readLine()) != null)
			{
				StringTokenizer st = new StringTokenizer(line, "\t ");

				int i=0;
				String[] tempStr = {":","-","-","\n"};
				while(st.hasMoreElements()) {
					String token = st.nextToken();
					System.out.print(token);
					System.out.print(tempStr[i++%4]);
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
