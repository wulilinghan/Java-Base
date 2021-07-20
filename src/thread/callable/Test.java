package thread.callable;

import java.util.concurrent.*;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		String s = new String("");
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				synchronized (s) {
//					try {
//						s.wait(5000L);
////						Thread.sleep(5000);
//						System.out.println("agin go....");
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//		}).start();
//		Thread.sleep(10);
//		synchronized (s) {
//			s.notify();
//			System.out.println("会前面执行");
//		}
		new Thread(new Runnable() {
			@Override
			public void run() {
			}
		}).start();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 6, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
				new ThreadPoolExecutor.CallerRunsPolicy());
		pool.allowCoreThreadTimeOut(true);
		Future<Boolean> future = pool.submit(new MyCallable());
		Boolean boolean1 = future.get();
		System.out.println(boolean1);

		MyCallable myCallable = new MyCallable();
	}
}
