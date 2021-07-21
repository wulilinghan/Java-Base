package basic;

import java.util.HashMap;

/**
 * @author: Administrator.
 * @Description: TODO()
 * @since:Created in 2019/10/16 0016.
 * @Modified By:
 */
public class Test2 {

    public static void main(String[] args) {
        Node[] newTable  = new Node[]{
                new Node("1",null)
        };
        Node e = new Node("1-1",
                    new Node("1-2",
                       new Node("1-3",null)));
        Node e2 = e;
        Node next1 = e2.next;
        System.out.println(next1);

        HashMap map;

        Node next = e.next;
        e.next = newTable[0];
        newTable[0] = e;
        System.out.println(e.next == e);
        System.out.println(e);
        System.out.println(e.next);
        e = next;
        System.out.println(e);
        System.out.println(next);
        System.out.println(e.next);
        System.out.println(newTable[0]);
    }

}
class Node {
    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }
    String name;
    Node next;
    public Node(String name, Node next) {
        this.name = name;
        this.next = next;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}