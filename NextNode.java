package TestCode;

public class NextNode extends StatementNode {

	private Node Next;

	public NextNode(Node NodeNext) { // get the value from parser
		this.Next = NodeNext;
	}

	public Node getinterNode() {
		return Next;
	}


	public String ToString() {
		// TODO Auto-generated method stub
		return "NextNode(" + Next + ")";
	}
}