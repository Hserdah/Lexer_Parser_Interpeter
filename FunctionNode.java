package TestCode;

import java.util.List;

public class FunctionNode extends Node {

	private String funcString;
	private List<Node> Func;

	public FunctionNode(String FuncString, List<Node> func) {
		this.funcString = FuncString;
		this.Func = func;
	}

	public List<Node> getFunc() {
		return Func;
	}

	public String getFuncStr() {
		return funcString;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FunctionNode(" + funcString + Func + ")";
	}

}
