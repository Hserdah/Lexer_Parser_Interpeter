package TestCode;


public class VariableNode extends Node {
	
private String Variable;	
	
public VariableNode(String Vari){
	this.Variable = Vari;
	
}


public String getVariable(){
	return Variable;
}





@Override
public String toString() {
	// TODO Auto-generated method stub
	return "VariableNode("+ Variable + ")" ;
}

}
