package basic.mianshi27;

import java.util.*;

public class C1 {

    // 入参 <学生,班级>
    public Map<Integer, List<Integer>> reverse(Map<Integer, Integer> studentToClass) {
        //  TODO
        // Map<班级,List<学生>>
        Map<Integer, List<Integer>> map = new HashMap<>(128);
        Iterator<Map.Entry<Integer, Integer>> entries = studentToClass.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer currentClass = entry.getValue();
            Integer currentStudent = entry.getKey();
            if (map.containsKey(currentClass)) {
                List<Integer> integers = map.get(currentClass);
                integers.add(currentStudent);
                continue;
            }
            List<Integer> newClasses = new ArrayList<>(100);
            map.put(currentClass, newClasses);
            newClasses.add(currentStudent);
        }
        return map;
    }

    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, new Node(4, null))));
//        node.setOldNext(node.getNext());
        Reverse(node);
        System.out.println(node);
    }

    public static void Reverse(Node head) {
//        if(head == null){
//            return;
//        }
        Node next = head.getNext();
        if (head.getOldNext() == null) {
            head.setNext(null);
        }
        if (head.getOldNext() != null) {
            next = head.getOldNext();
        }
        Node nextNext = next.getNext();
        head.setOldNext(null);
        next.setOldNext(nextNext);
        next.setNext(head);
        if (nextNext == null) {
            return;
        }
        // 翻转
        Reverse(next);
    }
}

class Node {
    private int value;
    private Node next;
    private Node oldNext;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node getOldNext() {
        return oldNext;
    }

    public void setOldNext(Node oldNext) {
        this.oldNext = oldNext;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
