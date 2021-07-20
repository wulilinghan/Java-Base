package socket.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientStart {

    public static void main(String[] args) {
        Socket socket = null;
        char[] cbuf = new char[10];
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        // Scanner scanner = new Scanner(System.in);
        try {
            socket = new Socket("127.0.0.1", 10000);
            osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            isr = new InputStreamReader(socket.getInputStream(), "utf-8");
            // String nextLine = scanner.nextLine();
            String nextLine = "oooooo";
            osw.write(nextLine);
            osw.flush();
            StringBuilder sb = new StringBuilder(2048);
            int read = isr.read(cbuf);
            if (read > 0) {
                sb.append(String.valueOf(cbuf));
            }
            // while (isr.read(cbuf) > 0) {
            // }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // scanner.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
