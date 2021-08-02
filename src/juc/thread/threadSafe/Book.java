package juc.thread.threadSafe;

public class Book {
	private String name;
	private int page;

	public Book(String name, int page) {
		super();
		this.name = name;
		this.page = page;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void readed(Person person) {
		synchronized(Book.class) {
			for (int i = 1; i <= this.page; i++) {
				System.out.println(person.getName() + "正在读" + this.name + "的第" + i + "页");
			}
		}
	}
	
	public void noted(Person person) {
		synchronized("") {
			for (int i = 1; i <= this.page; i++) {
				System.out.println(person.getName() + "正在给" + this.name + "的第" + i + "页做笔记");
			}
		}
	}
	public synchronized static void read(Book book,Person person){
		
	}
}
