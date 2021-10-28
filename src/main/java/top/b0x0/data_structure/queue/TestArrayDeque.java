package top.b0x0.data_structure.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class TestArrayDeque {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();

        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.push("d");

        deque.offerLast("e");
        deque.offerLast("f");
        deque.offerLast("g");
        deque.offerLast("h"); // 这时候扩容了

        deque.push("i");
        deque.offerLast("j");
        while (!deque.isEmpty()){
            System.out.println("deque.pop() = " + deque.pop());
        }
    }
}
