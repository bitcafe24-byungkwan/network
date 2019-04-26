package io;


import java.io.File;

import java.util.Scanner;


public class PhoneList02 {

	public static void main(String[] args) {
		
		Scanner scanner = null;
		try {			
			scanner = new Scanner(new File("phone.txt"));
			while(scanner.hasNextLine()) {
				String name = scanner.next();
				String pn01 = scanner.next();
				String pn02 = scanner.next();
				String pn03 = scanner.next();
				
				System.out.println(name + ":" + pn01+"-"+pn02+"-"+pn03);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(scanner !=null)
				scanner.close();
		}

	}

}
