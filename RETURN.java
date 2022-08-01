package TestCode;

public class RETURN extends StatementNode{

	private String Ret;

	public RETURN(String turn) {

		this.Ret=turn;
	}

	public String GetRet() {
		return Ret;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RETURN(" + Ret + ")";
	}

	
	
	
	
}
