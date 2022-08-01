package TestCode;

import java.util.ArrayList;
import java.util.List;

public class StatementsNode extends StatementNode{
	
	private List<Node> StateNode = new ArrayList<Node>(); 
	private int I;
	public StatementsNode(List<Node> NodeState) {
		this.StateNode = NodeState;
	}
	
	public List<Node> getStateNode(){
		return StateNode;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Statements("+ StateNode + ")" ;
	}


}
