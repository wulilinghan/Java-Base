package thread.borrowbook;

/**
 * @author GY
 * @date 2019年7月6日
 * @说明: 人
 */
public class Person {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void readBook(Book book) {
        int page = book.getPage();
        for (int i = 1; i <= page; i++) {
            System.out.println(this.getName() + "在看" + book.getName() + "的第" + i + "页");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
