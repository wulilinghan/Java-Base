package structure;

import java.util.LinkedList;

public class TestMyNode {

	public static void main(String[] args) {
		
//		HashMap<Integer, String> map;
		
		LinkedList<String> list;
		
		
		MyNode mynode1 = new MyNode().setPreNode(null);
		MyNode mynode2 = new MyNode().setPreNode(mynode1);
		MyNode mynode3 = new MyNode().setNextNode(null).setPreNode(mynode2);
		mynode1.setNextNode(mynode2);
		mynode2.setNextNode(mynode3);

	}

}
