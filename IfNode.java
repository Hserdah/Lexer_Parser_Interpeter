package TestCode;

import java.util.List;

public class IfNode extends StatementNode {

	private Node bool;
	private Node var;

	public IfNode(Node Bool , Node Var) {
		this.var = Var;
		this.bool = Bool;
	}

	public Node getbool() {
		return bool;
	}
	
	public Node getVar() {
		return var;
	}
	
	
	
	

	@Override
	public String toString() {
		return "If(" + bool + var + ")";
	}

}
