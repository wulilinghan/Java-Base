package thread.callable;

import java.util.concurrent.*;

public class MyCallable implements Callable<Boolean> {
	@Override
	public Boolean call() throws Exception {
		System.out.println("this is call .... ");
		TimeUnit.SECONDS.sleep(5);
		return true;
	}
	public static void main(String[] args) {
		Callable<Boolean> myCallable = new MyCallable();
		// 结合线程池来使用
//		ExecutorService executorService = Executors.newCachedThreadPool();
		// 将来<T>
		Future<Boolean> submit = Executors.newSingleThreadExecutor().submit(myCallable);
		try {
			Boolean aBoolean = submit.get();
			System.out.println(aBoolean);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
