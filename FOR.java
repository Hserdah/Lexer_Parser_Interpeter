package TestCode;

import java.util.List;

public class FOR extends StatementNode {

	private List<Node> FOR;
	private Node Nxt;
	public FOR(List<Node> FORCON) {
		this.FOR = FORCON;
	}

	public List<Node> getLabel() {
		return FOR;
	}
	
	public Node getNxt(Node nxt) {
		return this.Nxt = nxt;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FOR(" + FOR + ")";
	}

}
