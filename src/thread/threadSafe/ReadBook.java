package thread.threadSafe;

public class ReadBook {

	public static void main(String[] args) {

		Book thinkingInJava = new Book("Thinking in Java", 60);
		Book Neo4j = new Book("Neo4j", 60);
		Person laoWang = new Person("老王");
		Person lvGuang = new Person("绿光");
		new Thread(new PersonReadBook(laoWang, thinkingInJava)).start();
		new Thread(new PersonNode(lvGuang, thinkingInJava)).start();
	}

}

class PersonReadBook implements Runnable {
	private Person person;
	private Book book;

	public PersonReadBook(Person person, Book book) {
		super();
		this.person = person;
		this.book = book;
	}

	@Override
	public void run() {
		book.readed(person);
	}

}

class PersonNode implements Runnable {
	private Person person;
	private Book book;

	public PersonNode(Person person, Book book) {
		super();
		this.person = person;
		this.book = book;
	}

	@Override
	public void run() {
		book.noted(person);
	}

}