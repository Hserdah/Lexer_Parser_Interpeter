package TestCode;

public class FloatNode extends Node {

	private float FloatNumber;

	public FloatNode(float FloatNum) {
		this.FloatNumber = FloatNum;

	}
	
	public float getFloatNumber() {
		return FloatNumber;
	}


	@Override
	public String toString() {
		//to be made later
		return "FloatNode(" + FloatNumber + ")";
	}

	
}
