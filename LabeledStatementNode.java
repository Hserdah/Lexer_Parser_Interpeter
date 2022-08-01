package TestCode;

public class LabeledStatementNode extends StatementNode {
	private String lbl;
	private Node Label;

	public LabeledStatementNode(String lb, Node rea) {
		this.lbl = lb;
		this.Label = rea;
	}

	public LabeledStatementNode(String getValue) {
		this.lbl = getValue;
	}

	public String getLbl() {
		return lbl;
	}

	public Node getLabel() {
		return Label;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		if (Label != null) {
			return "LabeledStatementNode(" + lbl +" "+ Label + ")";
		} else {
			return "LabeledStatementNode(" + lbl + ")";
		}
	}
}
