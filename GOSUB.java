package TestCode;

public class GOSUB extends StatementNode {

	private Node Gos;

	public GOSUB(Node Sub) {

		this.Gos = Sub;
	}

	public Node GetGos() {
		return Gos;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "GOSUB(" + Gos + ")";
	}

}
