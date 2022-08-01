package TestCode;

public class AssignmentNode extends StatementNode {
	
	VariableNode VariNode;
	Node listNodes;
	
	public AssignmentNode(VariableNode NodeVari,Node Nodelist) {
		this.listNodes = Nodelist;
		this.VariNode = NodeVari;
		
	}
	
	
	public VariableNode getVariNode(){
		return VariNode;
	}
	
	
	public Node  getlistNodes(){
		return listNodes;
	}
	
	
	
	
	@Override
	public String toString() {
		return "AssignmentNode(" + VariNode + listNodes +")";
	}
}
