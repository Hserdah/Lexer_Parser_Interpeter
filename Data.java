package TestCode;


import java.util.List;

public class Data extends StatementNode {
	
	private List<Node> data;
	
	public Data(List<Node> StringData) {
		this.data = StringData;
		
		
	}
	
	public List<Node> getdata(){
		return data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Data("+  data + ")" ;
	}
	
	

}
