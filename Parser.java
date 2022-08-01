package TestCode;

import java.util.ArrayList;
import java.util.List;
import TestCode.BooleanOperationNode.OpType;
import TestCode.Token.Type;

public class Parser {

	private List<Token> lex;

	public Parser(List<Token> token) {

		this.lex = token;
		System.out.println(lex);

	}

	public StatementsNode parse() throws Exception {
		Node ATS = null;
		List<Node> Nodeparse = new ArrayList<Node>();
		while (!lex.isEmpty()) {
			ATS = Statements();
			Nodeparse.add(ATS);
			// Thread.sleep(2500);
		}
		return new StatementsNode(Nodeparse);

	}

	public Token matchandRemove(Token.Type verify) {
		// goes to the tokens and compares them to Type to see if they are valid
		// System.out.println(lex.size() + " size");
		// System.out.println(verify + "-ver");
		if (!lex.isEmpty()) {
			if (lex.get(0).getType() != verify) {
				// System.out.println(lex.get(0).GetValue() + " String Null");
				return null;
			} else {
				Token retToken = lex.get(0);
				// System.out.println(retToken + " -ret");
				return retToken;
			}
		}
		return null;

	}

	public Node Statements() throws Exception {
		Node State = Statement();
		if (State == null) {
			return null;

		}
		return State;

	}

//looks to see if there is anything in Assignment or a Statement?????
	public Node Statement() throws Exception {
// if it is a label I slap everything in the Label Node
		Token lbl = matchandRemove(Token.Type.Label);
		if (lbl != null) {
			lex.remove(0);
			if (matchandRemove(Token.Type.PRINT) != null) {
				lex.remove(0);
				Node PRint = PrintStatement();
				if (PRint == null) {
					return null;
				} else {
					return new LabeledStatementNode(lbl.GetValue(), PRint);
				}
			}
			if (matchandRemove(Token.Type.INPUT) != null) {
				lex.remove(0);
				Node INP = Input();
				if (INP == null) {
					return null;
				} else {

					return new LabeledStatementNode(lbl.GetValue(), INP);
				}
			}
			if (matchandRemove(Token.Type.DATA) != null) {
				lex.remove(0);
				Node Dat = Data();
				if (Dat == null) {
					return null;
				} else {
					return new LabeledStatementNode(lbl.GetValue(), Dat);
				}
			}
			if (matchandRemove(Token.Type.READ) != null) {

				lex.remove(0);
				Node Rea = Read();
				if (Rea == null) {
					return null;
				} else {

					return new LabeledStatementNode(lbl.GetValue(), Rea);
				}
			}
			if (matchandRemove(Token.Type.RETURN) != null) {
				Token RET = matchandRemove(Token.Type.RETURN);
				lex.remove(0);
				return new LabeledStatementNode(lbl.GetValue(), new RETURN(RET.GetValue()));
			}
			if (matchandRemove(Token.Type.GOSUB) != null) {
				lex.remove(0);
				Token GO = matchandRemove(Token.Type.Identifier);
				VariableNode var = new VariableNode(GO.GetValue());
				lex.remove(0);

				return new LabeledStatementNode(lbl.GetValue(), new GOSUB(var));
			}
			if (matchandRemove(Token.Type.FOR) != null) {
				lex.remove(0);
				Node FORLOOP = FOR();
				if (FORLOOP == null) {
					return null;
				} else {
					// System.out.println(FORLOOP);
					return new LabeledStatementNode(lbl.GetValue(), FORLOOP);
				}

			}
			if (matchandRemove(Token.Type.IF) != null) {
				lex.remove(0);
				Node Bool = BoolNode();
				if (Bool == null) {
					return null;
				} else {
					// System.out.println(FORLOOP);
					return new LabeledStatementNode("IF", Bool);
				}
			}
			if (matchandRemove(Token.Type.RANDOM) != null || matchandRemove(Token.Type.LEFT$) != null
					|| matchandRemove(Token.Type.RIGHT$) != null || matchandRemove(Token.Type.MID$) != null
					|| matchandRemove(Token.Type.NUM$) != null || matchandRemove(Token.Type.VAL) != null
					|| matchandRemove(Token.Type.Words) != null) {

				Node Ass = Assignment();
				if (Ass == null) {
					return null;

				}
				return Ass;
			}

		}
		// this is all where does everything go match
		if (matchandRemove(Token.Type.PRINT) != null) {
			lex.remove(0);
			Node PRint = PrintStatement();
			if (PRint == null) {
				return null;
			} else {
				return PRint;
			}
		}
		if (matchandRemove(Token.Type.INPUT) != null) {
			lex.remove(0);
			Node INP = Input();
			if (INP == null) {
				return null;
			} else {

				return INP;
			}
		}
		if (matchandRemove(Token.Type.DATA) != null) {
			lex.remove(0);
			Node Dat = Data();
			if (Dat == null) {
				return null;
			} else {
				return Dat;
			}
		}
		if (matchandRemove(Token.Type.READ) != null) {
			lex.remove(0);
			Node Rea = Read();
			if (Rea == null) {
				return null;
			} else {

				return Rea;
			}
		}

		if (matchandRemove(Token.Type.RETURN) != null) {
			Node Ret = RETURN();
			if (Ret == null) {
				return null;
			} else {

				return Ret;
			}
		}
		if (matchandRemove(Token.Type.GOSUB) != null) {
			lex.remove(0);
			Node gos = GOSUB();
			if (gos == null) {
				return null;
			} else {
				// System.out.println(gos);
				return gos;
			}
		}
		if (matchandRemove(Token.Type.FOR) != null) {
			lex.remove(0);
			Node FORLOOP = FOR();
			if (FORLOOP == null) {
				return null;
			} else {

				return FORLOOP;
			}
		}
		if (matchandRemove(Token.Type.IF) != null) {
			lex.remove(0);
			Node Bool = BoolNode();
			if (Bool == null) {
				return null;
			} else {
				System.out.println(Bool);
				return Bool;
			}
		}

		if (matchandRemove(Token.Type.Identifier) != null || matchandRemove(Token.Type.Words) != null) {

			Node Ass = Assignment();
			if (Ass == null) {
				return null;

			}
			return Ass;
		}

		if (matchandRemove(Token.Type.NEXT) != null) {
			lex.remove(0);
			Node nxt = next();
			if (nxt == null) {
				return null;
			} else {
				return nxt;
			}
		}
		return null;
	}

//follows input ends when it is not a Identifier or a String("")

	public Node next() {
		Token GO = matchandRemove(Token.Type.Identifier);

		VariableNode var = new VariableNode(GO.GetValue());
		lex.remove(0);
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new NextNode(var);
		}
		return null;
	}

	public Node Input() throws Exception {
		List<Node> NodeList = new ArrayList<Node>();
		while (matchandRemove(Token.Type.Identifier) != null || matchandRemove(Token.Type.String) != null) {
			Token Var = matchandRemove(Token.Type.Identifier);

			if (Var != null) {
				Node var = new VariableNode(Var.GetValue());
				NodeList.add(var);
				lex.remove(0);
			}

			Token String = matchandRemove(Token.Type.String);

			if (String != null) {
				Node Str = new StringNode(String.GetValue());
				NodeList.add(Str);
				lex.remove(0);
			}

			Token Word = matchandRemove(Token.Type.Words);

			if (Word != null) {
				Node vara = new VariableNode(Word.GetValue());
				NodeList.add(vara);
				lex.remove(0);
			}

		}
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new InputNode(NodeList);
		}
		return null;

	}

// looks for a string and or a number
	public Node Data() throws Exception {
		List<Node> NodeList = new ArrayList<Node>();
		while (matchandRemove(Token.Type.String) != null || matchandRemove(Token.Type.Num) != null
				|| (matchandRemove(Token.Type.Com) != null)) {
			Token Quotetok = matchandRemove(Token.Type.String);

			if (Quotetok != null) {
				Node quote = new StringNode(Quotetok.GetValue());
				NodeList.add(quote);
				lex.remove(0);
			}
			Token num = matchandRemove(Token.Type.Num);

			if (num != null) {
				if (num.GetValue().contains(".")) {
					Node flo = new FloatNode(Float.parseFloat(num.GetValue()));
					NodeList.add(flo);
					lex.remove(0);
				} else {
					Node regint = new IntegerNode(Integer.parseInt(num.GetValue()));
					NodeList.add(regint);
					lex.remove(0);
				}
			}

		}
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new Data(NodeList);
		}
		return null;

	}

// just looks for variables that is it 
	public Node Read() throws Exception {
		List<Node> NodRead = new ArrayList<Node>();
		while (matchandRemove(Token.Type.Identifier) != null
				|| (matchandRemove(Token.Type.Com) != null || (matchandRemove(Token.Type.Words) != null))) {
			Token Identy = matchandRemove(Token.Type.Identifier);
			// System.out.println("hello?");
			if (Identy != null) {

				Node var = new VariableNode(Identy.GetValue());
				NodRead.add(var);
				lex.remove(0);
			}

			Token Word = matchandRemove(Token.Type.Words);

			if (Word != null) {
				Node vara = new VariableNode(Word.GetValue());
				NodRead.add(vara);
				lex.remove(0);
			}

			Token Com = matchandRemove(Token.Type.Com);
			if (Com != null) {
				lex.remove(0);
			}

		}
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new Read(NodRead);
		}
		return null;

	}
// finds print and an assigment and prints it out

// has and order goes variable-> num-> TO-> Num->NEXT OR STEP->APPROPRATE token 
	public Node FOR() throws Exception {
		List<Node> Nodefor = new ArrayList<Node>();
		Token Var = matchandRemove(Token.Type.Identifier);
		lex.remove(0);
		if (Var != null) {
			Node var = new VariableNode(Var.GetValue());
			Nodefor.add(var);
		}
		Type Tok;
		if (matchandRemove(Token.Type.Equals) != null) {
			lex.remove(0);
			Tok = Token.Type.Equals;
		}
		Token num = matchandRemove(Token.Type.Num);
		lex.remove(0);
		if (num != null) {

			if (num.GetValue().contains(".")) {
				Node flo = new FloatNode(Float.parseFloat(num.GetValue()));
				Nodefor.add(flo);
			} else if (!num.GetValue().contains(".")) {
				Node intgr = new IntegerNode(Integer.parseInt(num.GetValue()));
				Nodefor.add(intgr);
			}

			if (matchandRemove(Token.Type.TO) != null) {
				lex.remove(0);
			}

			Token numincment = matchandRemove(Token.Type.Num);
			lex.remove(0);
			if (numincment != null) {

				if (numincment.GetValue().contains(".")) {
					Node floinc = new FloatNode(Float.parseFloat(numincment.GetValue()));
					Nodefor.add(floinc);
				} else if (!numincment.GetValue().contains(".")) {
					Node intgrinc = new IntegerNode(Integer.parseInt(numincment.GetValue()));
					Nodefor.add(intgrinc);
				}

				if (matchandRemove(Token.Type.STEP) != null) {
					lex.remove(0);

					Token number = matchandRemove(Token.Type.Num);
					lex.remove(0);
					if (number != null) {

						if (number.GetValue().contains(".")) {
							Node flot = new FloatNode(Float.parseFloat(number.GetValue()));
							Nodefor.add(flot);
						} else if (!number.GetValue().contains(".")) {
							Node intger = new IntegerNode(Integer.parseInt(number.GetValue()));
							Nodefor.add(intger);
						}
					}

				}
			}
		}
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new FOR(Nodefor);
		}
		return null;
	}

// looks for return
	public Node RETURN() throws Exception {
		Token RET = matchandRemove(Token.Type.RETURN);
		lex.remove(0);
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new RETURN(RET.GetValue());
		}
		return null;
	}

// looks for a single variable
	public Node GOSUB() throws Exception {
		Token GO = matchandRemove(Token.Type.Identifier);
		lex.remove(0);
		VariableNode var = new VariableNode(GO.GetValue());
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new GOSUB(var);
		}
		return null;
	}

//you just make a list of expressions that is it?

	// looks for comma separated print Statement
	public Node PrintStatement() throws Exception {
		List<Node> NodePrint = new ArrayList<Node>();
		while (matchandRemove(Token.Type.Identifier) != null || (matchandRemove(Token.Type.Com) != null)
				|| (matchandRemove(Token.Type.String) != null) || (matchandRemove(Token.Type.Words) != null)) {
			Token Var = matchandRemove(Token.Type.Identifier);
			if (Var != null) {
				Node PrintVar = new VariableNode(Var.GetValue());
				NodePrint.add(PrintVar);
				lex.remove(0);
			}

			if ((matchandRemove(Token.Type.Com) != null)) {
				lex.remove(0);
			}

			Token String = matchandRemove(Token.Type.String);

			if (String != null) {
				Node Str = new StringNode(String.GetValue());
				NodePrint.add(Str);
				lex.remove(0);
			}

			Token VarWord = matchandRemove(Token.Type.Words);
			// System.err.println(VarWord);

			if (VarWord != null) {
				// System.out.println(VarWord);
				lex.remove(0);
				Node var = new VariableNode(VarWord.GetValue());
				NodePrint.add(var);
			}

			if (matchandRemove(Token.Type.END) != null) {
				lex.remove(0);
				return new PrintNode(NodePrint);
			}

		}
		return null;

	}

// almost like term look for either a var or a num
//but when is see THEN i go find a word (in the basic doc it said label but the example did not have xismall: but xismall so I assumed it was a word
	public Node BoolNode() throws Exception {
		Node stringNode = null;
		Node NumORVar = Factor();
		System.out.println(NumORVar);
		if (NumORVar == null) {
			return null;

		}
		OpType Type = null;
		if (matchandRemove(Token.Type.Less) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.Less;
		}
		if (matchandRemove(Token.Type.LessEquals) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.LessEquals;
		}
		if (matchandRemove(Token.Type.Greater) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.Greater;
		}
		if (matchandRemove(Token.Type.GreaterEquals) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.GreaterEquals;
		}
		if (matchandRemove(Token.Type.NotEquals) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.NotEquals;
		}
		if (matchandRemove(Token.Type.Equals) != null) {
			lex.remove(0);
			Type = BooleanOperationNode.OpType.Equals;
		}

		Node VarORNum = Factor();
		if (VarORNum == null) {
			throw new Exception("No num or Var");
		}

		BooleanOperationNode boolNode = new BooleanOperationNode(Type, NumORVar, VarORNum);

		if (matchandRemove(Token.Type.THEN) != null) {
			lex.remove(0);
		}

		Token Str = matchandRemove(Token.Type.Label);

		if (Str != null) {
			lex.remove(0);
			stringNode = new LabeledStatementNode(Str.GetValue());

		}
		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return new IfNode(boolNode, stringNode);
		}
		return null;

	}

// build the correct OPS
	public Node Assignment() throws Exception {

		Node Variable = Factor();
		if (Variable == null) {
			return null;
		}

		Type Tok;
		if (matchandRemove(Token.Type.Equals) != null) {
			lex.remove(0);
			Tok = Token.Type.Equals;
		}
		Node express = Expression();
		if (express == null) {
			return null;
		}

		Node Assgn = new AssignmentNode((VariableNode) Variable, express);
		// System.out.println(Assgn + "ass");

		if (matchandRemove(Token.Type.END) != null) {
			lex.remove(0);
			return Assgn;
		}
		return null;

	}

//looks for TERM plus|mins TERM
	public Node Expression() throws Exception {
		Node OpMath = null;
		Node NumValid = Term();
		if (NumValid == null) {
			Token rando = matchandRemove(Token.Type.RANDOM);
			if (rando != null) {
				lex.remove(0);
				List<Node> rand = Random();
				if (rand == null) {
					return null;
				}
				return new FunctionNode(rando.GetValue(), rand);
			}
			Token Left = matchandRemove(Token.Type.LEFT$);
			if (Left != null) {
				lex.remove(0);
				List<Node> left = Left();
				if (left == null) {
					return null;
				}
				return new FunctionNode(Left.GetValue(), left);
			}
			Token RIGHT = matchandRemove(Token.Type.RIGHT$);
			if (RIGHT != null) {

				lex.remove(0);
				List<Node> right = Right();
				if (right == null) {
					return null;
				}
				return new FunctionNode(RIGHT.GetValue(), right);
			}

			Token MID = matchandRemove(Token.Type.MID$);
			if (MID != null) {
				lex.remove(0);
				List<Node> mid = Mid();
				if (mid == null) {
					return null;
				}
				return new FunctionNode(MID.GetValue(), mid);
			}

			Token NUM = matchandRemove(Token.Type.NUM$);
			if (NUM != null) {
				lex.remove(0);
				List<Node> num = Num();
				if (num == null) {
					return null;
				}
				return new FunctionNode(NUM.GetValue(), num);
			}
			Token NUMVAL = matchandRemove(Token.Type.VAL);
			if (NUMVAL != null) {
				lex.remove(0);
				List<Node> val = ValInt();
				if (val == null) {
					return null;
				}
				return new FunctionNode(NUMVAL.GetValue(), val);
			}
			Token Words = matchandRemove(Token.Type.Valp);
			if (Words != null) {
				lex.remove(0);
				List<Node> val = ValFloa();
				if (val == null) {
					return null;
				}
				return new FunctionNode(Words.GetValue(), val);
			}

		}

//I realized that when I was making the Tok token - match and remove the if statement would return true 
//BUT also got rid of it so making Tok + or anything would return null so the best was to make it a type
		while (NumValid != null) {
			Type Tok;
			if (matchandRemove(Token.Type.plus) != null) {
				lex.remove(0);
				Tok = Token.Type.plus;
			} else if (matchandRemove(Token.Type.Sub) != null) {
				lex.remove(0);
				Tok = Token.Type.Sub;
			} else {
				return NumValid;

			}

			Node Term = Term();

			if (Term == null) {
				throw new Exception("nope");
			}

			OpMath = new MathOpNode(Tok == Token.Type.plus ? MathOpNode.NumType.plus : MathOpNode.NumType.Sub, NumValid,
					Term);
			NumValid = OpMath;

		}

		return OpMath;

	}

// I am taking the most literal expression in the basic.docs which states "RANDOM() – returns a random integer" and that is what I do I return a random IntgerNode between 0 - 1000
	public List<Node> Random() {
		List<Node> rando = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}

		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}

		return rando;

	}

	public List<Node> Left() {
		List<Node> leftlist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}
		Token word = matchandRemove(Token.Type.String);
		if (word != null) {
			lex.remove(0);
			Node WordString = new StringNode(word.GetValue());
			leftlist.add(WordString);
		}

		if (matchandRemove(Token.Type.Com) != null) {
			lex.remove(0);
		}

		Token num = matchandRemove(Token.Type.Num);
		if (num != null) {
			lex.remove(0);
			Node intnum = new IntegerNode(Integer.parseInt(num.GetValue()));
			leftlist.add(intnum);
		}

		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}

		return leftlist;

	}

	public List<Node> Right() {
		List<Node> Rightlist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}
		Token word = matchandRemove(Token.Type.String);
		if (word != null) {
			lex.remove(0);
			Node WordString = new StringNode(word.GetValue());
			Rightlist.add(WordString);
		}

		if ((matchandRemove(Token.Type.Com) != null)) {
			lex.remove(0);
		}

		Token num = matchandRemove(Token.Type.Num);
		if (num != null) {
			lex.remove(0);
			Node intnum = new IntegerNode(Integer.parseInt(num.GetValue()));
			Rightlist.add(intnum);
		}

		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}
		return Rightlist;

	}

	public List<Node> Mid() {
		List<Node> Midlist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}
		Token word = matchandRemove(Token.Type.String);
		if (word != null) {
			lex.remove(0);
			Node WordString = new StringNode(word.GetValue());
			Midlist.add(WordString);
		}

		if (matchandRemove(Token.Type.Com) != null) {
			lex.remove(0);
		}

		Token leftnum = matchandRemove(Token.Type.Num);
		if (leftnum != null) {
			lex.remove(0);
			Node intnum = new IntegerNode(Integer.parseInt(leftnum.GetValue()));
			Midlist.add(intnum);
		}
		if (matchandRemove(Token.Type.Com) != null) {
			lex.remove(0);
		}

		Token rightnum = matchandRemove(Token.Type.Num);
		if (rightnum != null) {
			lex.remove(0);
			Node intnum = new IntegerNode(Integer.parseInt(rightnum.GetValue()));
			Midlist.add(intnum);
		}

		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}

		return Midlist;

	}

	public List<Node> Num() {
		List<Node> numlist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}
		Token num = matchandRemove(Token.Type.Num);
		if (num != null) {

			if (num.GetValue().contains(".")) {
				lex.remove(0);
				Node floa = new FloatNode(Float.parseFloat(num.GetValue()));
				numlist.add(floa);
			} else if (!num.GetValue().contains(".")) {
				lex.remove(0);
				Node intnum = new IntegerNode(Integer.parseInt(num.GetValue()));
				numlist.add(intnum);
			}

		}
		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}
		return numlist;

	}

	public List<Node> ValInt() {
		List<Node> vallist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}

		Token word = matchandRemove(Token.Type.String);
		if (word != null) {
			lex.remove(0);
			Node WordString = new StringNode(word.GetValue());
			vallist.add(WordString);
		}
		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}

		return vallist;

	}

	public List<Node> ValFloa() {
		List<Node> vallist = new ArrayList<Node>();
		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);
		}

		Token word = matchandRemove(Token.Type.String);
		if (word != null) {
			lex.remove(0);
			Node WordString = new StringNode(word.GetValue());
			vallist.add(WordString);
		}
		if (matchandRemove(Token.Type.RPAREN) != null) {
			lex.remove(0);
		}

		return vallist;

	}

// looks for Factor div | multy Factor
	public Node Term() throws Exception {
		Node OpMath = null;
		Node NumValid = Factor();

		while (NumValid != null) {
			Type Tok;
			if (matchandRemove(Token.Type.Multy) != null) {
				lex.remove(0);
				Tok = Token.Type.Multy;

			} else if (matchandRemove(Token.Type.Div) != null) {
				lex.remove(0);
				Tok = Token.Type.Div;

			} else {

				return NumValid;

			}

			Node Term = Factor();

			if (Term == null) {
				throw new Exception("no go");
			}
			OpMath = new MathOpNode(Tok == Token.Type.Multy ? MathOpNode.NumType.Multy : MathOpNode.NumType.Div,
					NumValid, Term);

			NumValid = OpMath;

		}

		return OpMath;
	}

//Number end OperatorCheck here either sent to int or Float and a Identifier
	public Node Factor() throws Exception {

		Token num = matchandRemove(Token.Type.Num);
		if (num != null) {

			if (num.GetValue().contains(".")) {
				lex.remove(0);
				return new FloatNode(Float.parseFloat(num.GetValue()));
			} else if (!num.GetValue().contains(".")) {
				lex.remove(0);
				return new IntegerNode(Integer.parseInt(num.GetValue()));
			}
//matchandRemove(Token.Type.Words) != null
		}
		Token Var = matchandRemove(Token.Type.Identifier);
		// System.err.println(Var);

		if (Var != null) {
			// System.out.println(Var);
			lex.remove(0);
			return new VariableNode(Var.GetValue());

		}

		Token VarWord = matchandRemove(Token.Type.Words);
		// System.err.println(VarWord);

		if (VarWord != null) {
			// System.out.println(VarWord);
			lex.remove(0);
			return new VariableNode(VarWord.GetValue());

		}

		if (matchandRemove(Token.Type.LPAREN) != null) {
			lex.remove(0);

			Node Express = Expression();
			if (Express == null) {
				throw new Exception("No Go");
			}
			if (matchandRemove(Token.Type.RPAREN) != null) {
				lex.remove(0);
				return Express;

			}

		} else {
			return null;
		}

		return null;

	}

}