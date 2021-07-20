package thread;

public class ThreadTest {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("run");
				System.out.println("gogogo");
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.start();
	}

}
