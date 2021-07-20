package io.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ServerHandler implements Runnable {

	private Socket socket;

	ServerHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(this.socket.getOutputStream(), true);// auto
																		// flush
			String currentTime = null;
			String body = null;
			while (true) {
				body = br.readLine();
				if (body == null)
					break;
				System.out.println("the time server receive order:" + body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
						? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pw.println(currentTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("服务端 释放线程中开启的io资源");
			pw.close();
			try {
				br.close();
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
