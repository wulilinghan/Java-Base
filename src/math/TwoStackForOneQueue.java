package math;

import java.util.Queue;
import java.util.Stack;

/**
 * @author ManJiis
 * @since 2020/10/19 8:43
 * @Description: // TODO
 **/
public class TwoStackForOneQueue {
    public static void main(String[] args) {
        Queue1 myqueue = new Queue1();
        System.out.println("3,2,6,1,9 将进入队中");
        myqueue.add(3);
        myqueue.add(2);
        myqueue.add(6);
        myqueue.add(1);
        myqueue.add(9);
        System.out.println("先出两个");
        System.out.println(myqueue.poll());
        System.out.println(myqueue.poll());
        System.out.println("10 将进入队中");
        myqueue.add(10);
        System.out.println("再出剩余");
        System.out.println(myqueue.poll());
        System.out.println(myqueue.poll());
        System.out.println(myqueue.poll());
        System.out.println(myqueue.poll());
    }
}

class Queue1 {
    // 先创建两个栈 stack1，Stack2
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    Queue1() {
        this.stack1 = new Stack<Integer>();
        this.stack2 = new Stack<Integer>();
    }
    /**
     * 入队
     * @param value
     */
    public void add(Integer value){
        stack1.push(value);
    }
    /**
     * 出队
     */
    public Integer poll(){
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }
        // 执行到这里时 说明stack2 是空的
        if(!stack1.isEmpty()) {
            this.change();
        }
        if(stack2.isEmpty())
            return null;
        return stack2.pop();
    }
    private void change() {
        while (!this.stack1.isEmpty()) {
            int value = this.stack1.pop();
            this.stack2.push(value);
        }
    }
}