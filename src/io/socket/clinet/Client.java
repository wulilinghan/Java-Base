package io.socket.clinet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			socket = new Socket("127.0.0.1", 10000);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("QUERY TIME ORDER");
//			out.println("QUERY TIME ORDER");
			System.out.println("send order to server succeed.");
			String resp = in.readLine();
//			if (resp == null) {
//			    break;
//			}
			System.out.println(resp);
//			while (true) {
//			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
