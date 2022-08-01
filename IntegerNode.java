package TestCode;

public class IntegerNode extends Node {

	private int number;

	public IntegerNode(int intNum) {
		this.number = intNum;
		
	}
	
	public int getNumber() {
		return number;
	}

	
	
	@Override
	public String toString() {
		return "IntegerNode(" + number + ")";
	}

	
}
