package TestCode;

import java.util.List;

public class PrintNode extends StatementNode {

	private List<Node> PrintNode;

	public PrintNode(List<Node> NodePrint) {
		this.PrintNode = NodePrint;
	}

	public List<Node> getPrintNode() {
		return PrintNode;
	}

	@Override
	public String toString() {
		return "PRINT"+ PrintNode +"";
	}

}
