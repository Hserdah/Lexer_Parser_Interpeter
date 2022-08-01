package TestCode;

public class MathOpNode extends Node {
	int Operand;
	private Node left, right;
	private NumType Operator;

	public enum NumType {
		plus, Multy, Div, Sub, Num

	}

	public MathOpNode(NumType data, Node left, Node right) {
		this.Operator = data;
		this.left = left;
		this.right = right;

	}

	public NumType getNumType() {
		return Operator;

	}

	public Node getLeft() {
		return left;

	}

	public Node getRight() {
		return right;

	}

	@Override
	public String toString() {
		return "MathOP("+ Operator + " " + left +" "+ right + ")";
	}

}
