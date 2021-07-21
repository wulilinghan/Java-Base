package thread.borrowbook;

/**
 * @author GY
 * @since 2019年7月6日
 * @说明: 图书馆
 */
public class Library {
    /**
     * 就一本
     */
    private static Book thinkingInJava = new Book("Tinking in Java", 20);

    public void borrowBookRead(Person person) {
        int page = thinkingInJava.getPage();
        for (int i = 1; i <= page; i++) {
            System.out.println(person.getName() + "在看" + thinkingInJava.getName() + "的第" + i + "页");
        }
    }

}
