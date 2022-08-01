package TestCode;


import java.util.List;

public class Read extends StatementNode {

	private List<Node> variRead;

	public Read(List<Node> Readvari) {
		this.variRead = Readvari;

	}

	public List<Node> getRead() {
		return variRead;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Read("+ variRead + ")" ;
	}
}
