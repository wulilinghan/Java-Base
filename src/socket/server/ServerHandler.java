package socket.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        char[] cbuf = new char[10];
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        // Scanner scanner = new Scanner(System.in);
        try {
            isr = new InputStreamReader(socket.getInputStream(), "utf-8");
            osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
            StringBuilder sb = new StringBuilder(2048);
            while ((isr.read(cbuf)) > 0) {
                String str = String.valueOf(cbuf);
                sb.append(str);
                String line = "xxxxxxxx";
                osw.write(line);
                osw.flush();
            }
            System.out.println(sb.toString());
            // String line = scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("是否线程中资源线程即将结束");
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // if(scanner != null) {
            // scanner.close();
            // }
        }
    }

}
