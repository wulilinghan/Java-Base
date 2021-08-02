package juc.thread.borrowbook;

public class TestBorrowBook {

    public static void main(String[] args) {
        Person lm = new Person("李明");
        Person wmm = new Person("王梅梅");
        Library library = new Library();
        new Thread(new BorrowBookRead(lm, library)).start();
        new Thread(new BorrowBookRead(wmm, library)).start();
    }

}

class BorrowBookRead implements Runnable {
    private Library library;
    private Person person;

    public BorrowBookRead(Person person, Library library) {
        this.person = person;
        this.library = library;
    }

    @Override
    public void run() {
        library.borrowBookRead(person);
    }

}
