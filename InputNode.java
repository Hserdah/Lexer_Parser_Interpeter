package TestCode;

import java.util.List;

public class InputNode extends StatementNode {

	private List<Node> Word;

	public InputNode(List<Node> Wording) {
		this.Word = Wording;
	}

	public List<Node> getWord() {
		return Word;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Input(" + Word +")";

	}
}