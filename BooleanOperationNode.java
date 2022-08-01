package TestCode;


public class BooleanOperationNode extends Node{
	
	private Node left, right;
	private OpType Op;
	
	public enum OpType {
		Equals, Less, LessEquals, Greater, GreaterEquals, NotEquals
	}
	
	public BooleanOperationNode(OpType Type,Node Left,Node Right) {
		this.Op = Type;
		this.left = Left;
		this.right =  Right;
	}
	
	public OpType getOpType() {
		return Op;

	}

	public Node getLeft() {
		return left;

	}

	public Node getRight() {
		return right;

	}

	@Override
	public String toString() {
		return "BooleanOperationNode("+ Op + " " + left +" "+ right + ")";
	}


}
