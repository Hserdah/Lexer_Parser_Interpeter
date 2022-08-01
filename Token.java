package TestCode;

public class Token {

	private Type globTok;
	private String ValueString;

//Type of tokens
	public enum Type {
		plus, Multy, Div, Sub, Num, point, LPAREN, RPAREN, Equals, Less, LessEquals, Greater, GreaterEquals, NotEquals,
		String, Words, Identifier, Label, Com, KnownWords, PRINT, INPUT, DATA, READ, NEXT, FOR, STEP, GOSUB, RETURN, TO,THEN,
		IF, RANDOM, LEFT$, RIGHT$, MID$, NUM$, VAL,Valp,END
	}

//Constructor
	public Token(Type TokType, String TokString) {
		this.ValueString = TokString;
		this.globTok = TokType;
	}

//Both the getters
	public Type getType() {
		return globTok;

	}

	public String GetValue() {
		return ValueString;

	}

	@Override
	public String toString() {
		return String.format(("%s (%s)"), globTok, ValueString);
	}
}
