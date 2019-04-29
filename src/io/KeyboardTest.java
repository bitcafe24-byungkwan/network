package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {
	public static void main(String[] args) {
		//기반스트림(표준입력, 키보드, Sytem.in)
		
		//보조스트림1
		// byte|byte|byte -? char
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(System.in, "utf-8");
			
			br = new BufferedReader(isr);
			String line =null;
			while((line = br.readLine()) != null)
			{
				System.out.println(">>" + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			if(br != null)
			{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//보조스트림2
		// char1|char2|char3|\n -> "char1char2char3\n"
		
		
	}
}
