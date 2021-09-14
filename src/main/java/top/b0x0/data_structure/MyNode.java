package top.b0x0.data_structure;

/**
 * @author ManJiis
 * @since 2019年8月12日
 */
public class MyNode {

    private MyNode preNode;

    private Object value;

    private MyNode nextNode;

    public MyNode(MyNode preNode, Object value, MyNode nextNode) {
        super();
        this.preNode = preNode;
        this.value = value;
        this.nextNode = nextNode;
    }

    public MyNode() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MyNode getPreNode() {
        return preNode;
    }

    public MyNode setPreNode(MyNode preNode) {
        this.preNode = preNode;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public MyNode setValue(Object value) {
        this.value = value;
        return this;
    }

    public MyNode getNextNode() {
        return nextNode;
    }

    public MyNode setNextNode(MyNode nextNode) {
        this.nextNode = nextNode;
        return this;
    }
}
