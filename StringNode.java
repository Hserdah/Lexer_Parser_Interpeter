package TestCode;


public class StringNode extends Node {
	
	private String Str;
	
	
	public StringNode(String getValue) {
		this.Str = getValue;
	}

	public String getStr() {
		return Str;
	}
	
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "StringNode("+ Str + ")" ;
	}
	
	
	
	
	
	

}
