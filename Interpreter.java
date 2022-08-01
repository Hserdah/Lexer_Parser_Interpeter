package TestCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter {

	private StatementsNode parser;
// since we are in setup for this assignment all of the hashmap plus a list for data are global
// just because 3 out of 4 are not specified to a specific location and just in case I kept the Node one here too
// the List for data is here too because I assume we will need to use it for read later on 
	HashMap<String, Integer> IntMap = new HashMap<String, Integer>();
	HashMap<String, Float> FloatMap = new HashMap<String, Float>();
	HashMap<String, String> StrMap = new HashMap<String, String>();
	HashMap<String, Node> NodeMap = new HashMap<String, Node>();
	List<Node> newlist = new ArrayList<Node>();
	Stack<Node> NodeStack = new Stack<Node>();

	public Interpreter(StatementsNode node) throws Exception {
		this.parser = node;
		System.err.println(parser);
		lookforNode();

	}

	// Next looks to the next statement(Line)

	public void lookforNode() throws Exception {
		int j;
		int k = 0;
		for (int i = 0; i < parser.getStateNode().size();) {
			if (parser.getStateNode().get(i).getClass().equals(LabeledStatementNode.class)) {
				visitLabel((parser.getStateNode().get(i)));
				i++;
				// this references the next Node or "next" in the rubric
			} else if (parser.getStateNode().get(i).getClass().equals(FOR.class)) {
				j = visitfor((parser.getStateNode().get(i)), i++);
				i = j;
			} else if (parser.getStateNode().get(i).getClass().equals(NextNode.class)) {
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(Data.class)) {
				visitdata((parser.getStateNode().get(i)));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(GOSUB.class)) {
				VisitGosub((parser.getStateNode().get(i)));
				k = i;
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(Read.class)) {
				visitRead(parser.getStateNode().get(i));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(AssignmentNode.class)) {
				visitAssign(parser.getStateNode().get(i));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(InputNode.class)) {
				visitInput(parser.getStateNode().get(i));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(PrintNode.class)) {
				visitPrint(parser.getStateNode().get(i));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(IfNode.class)) {
				visitIf(parser.getStateNode().get(i));
				i++;
			} else if (parser.getStateNode().get(i).getClass().equals(RETURN.class)) {
				if (!NodeStack.empty()) {
					Return();
					i = k++;
				}
				i++;
			}
		}
	}

	public void VisitGosub(Node GO) {
		GOSUB gos = (GOSUB) GO;

		NodeStack.add(gos.GetGos());

	}

	public void Return() throws Exception {
		if (!NodeStack.empty()) {
			Node retNode = NodeStack.pop();

			Node lbl = NodeMap.get(((VariableNode) retNode).getVariable());

			if (lbl.getClass().equals(Data.class)) {
				visitdata(lbl);
			}
			if (lbl.getClass().equals(Read.class)) {
				visitRead(lbl);
			}
			if (lbl.getClass().equals(AssignmentNode.class)) {
				visitAssign(lbl);
			}
			if (lbl.getClass().equals(InputNode.class)) {
				visitInput(lbl);
			}
			if (lbl.getClass().equals(PrintNode.class)) {
				visitPrint(lbl);
			}
			if (lbl.getClass().equals(LabeledStatementNode.class)) {
				visitLabel(lbl);
			}
		}

	}

	public void visitIf(Node If) throws Exception {
		IfNode IF = (IfNode) If;
		Node lbl = IF.getVar();
		Node nod = NodeMap.get(((LabeledStatementNode) lbl).getLbl());
		// if it is true go to one of these functions
		if ((VisitBool(IF.getbool()) == true)) {
			if (nod.getClass().equals(Data.class)) {
				visitdata(nod);
			}
			if (nod.getClass().equals(Read.class)) {
				visitRead(nod);
			}
			if (nod.getClass().equals(AssignmentNode.class)) {
				visitAssign(nod);
			}
			if (nod.getClass().equals(InputNode.class)) {
				visitInput(nod);
			}
			if (nod.getClass().equals(PrintNode.class)) {
				visitPrint(nod);
			}
			if (nod.getClass().equals(LabeledStatementNode.class)) {
				visitLabel(nod);
			}

		} else {
			// just to have something I put false
			System.out.println("FALSE");
		}

	}

// gives true or false to boolean expressions
	public boolean VisitBool(Node Bool) {
		BooleanOperationNode bool = (BooleanOperationNode) Bool;
		Node boolLeft = bool.getLeft();
		Node boolRight = bool.getRight();
		int boolint;
		float boolflaot;
		String boolstring;
		boolean Isbool;
// if it is a float
		if (bool.getLeft().getClass().equals(VariableNode.class)) {
			if (((VariableNode) boolLeft).getVariable().contains("%")) {
				boolflaot = ((FloatNode) boolRight).getFloatNumber();

				if (bool.getOpType() == bool.getOpType().Less) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) < boolflaot;

				}
				if (bool.getOpType() == bool.getOpType().LessEquals) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) <= boolflaot;

				}
				if (bool.getOpType() == bool.getOpType().Greater) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) > boolflaot;

				}
				if (bool.getOpType() == bool.getOpType().GreaterEquals) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) >= boolflaot;

				}
				if (bool.getOpType() == bool.getOpType().NotEquals) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) != boolflaot;

				}
				if (bool.getOpType() == bool.getOpType().Equals) {
					return Isbool = FloatMap.get(((VariableNode) boolLeft).getVariable()) == boolflaot;

				}
				// if it is a int
				// returns true or false
			} else {
				boolint = ((IntegerNode) boolRight).getNumber();

				if (bool.getOpType() == bool.getOpType().Less) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) < boolint;

				}
				if (bool.getOpType() == bool.getOpType().LessEquals) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) <= boolint;

				}
				if (bool.getOpType() == bool.getOpType().Greater) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) > boolint;

				}
				if (bool.getOpType() == bool.getOpType().GreaterEquals) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) >= boolint;

				}
				if (bool.getOpType() == bool.getOpType().NotEquals) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) != boolint;

				}
				if (bool.getOpType() == bool.getOpType().Equals) {
					return Isbool = IntMap.get(((VariableNode) boolLeft).getVariable()) == boolint;

				}

			}

		}
		return false;

	}

// prints everything 
	public void visitPrint(Node Prn) {
		PrintNode print = (PrintNode) Prn;
		for (Node printall : print.getPrintNode()) {
			if (printall.getClass().equals(StringNode.class)) {
				System.out.println(((StringNode) printall).getStr().toString());
			}
			if (printall.getClass().equals(VariableNode.class)) {
				if (((VariableNode) printall).getVariable().contains("%")) {
					System.out.println(FloatMap.get(((VariableNode) printall).getVariable()));
				} else if (((VariableNode) printall).getVariable().contains("$")) {
					System.out.println(StrMap.get(((VariableNode) printall).getVariable()));
				} else {
					System.out.println(IntMap.get(((VariableNode) printall).getVariable()));
				}
			}
		}

	}

// finds which goes to which so flaot goes to flaot and etc
	public void visitAssign(Node assi) throws Exception {
		AssignmentNode assign = (AssignmentNode) assi;
		Node num = assign.getlistNodes();
		if (assign.getVariNode().getVariable().contains("%")) {
			if (assign.getlistNodes().getClass().equals(FloatNode.class)) {
				FloatMap.put(assign.getVariNode().getVariable(), ((FloatNode) num).getFloatNumber());
			} else if (assign.getlistNodes().getClass().equals(MathOpNode.class)) {
				float FloatMath = MathOpFloat(assign.getlistNodes());
				FloatMap.put(assign.getVariNode().getVariable(), FloatMath);
			} else if (assign.getlistNodes().getClass().equals(FunctionNode.class)) {
				Float FloatFunc = FuncFloat(assign.getlistNodes());
				FloatMap.put(assign.getVariNode().getVariable(), FloatFunc);
			}
		} else {
			if (assign.getlistNodes().getClass().equals(IntegerNode.class)) {
				IntMap.put(assign.getVariNode().getVariable(), ((IntegerNode) num).getNumber());
			} else if (assign.getlistNodes().getClass().equals(MathOpNode.class)) {
				int Intmath = MathOpInt(assign.getlistNodes());
				IntMap.put(assign.getVariNode().getVariable(), Intmath);

			} else if (assign.getlistNodes().getClass().equals(FunctionNode.class)) {
				int intFunc = FuncInt(assign.getlistNodes());
				IntMap.put(assign.getVariNode().getVariable(), intFunc);
			}
		}
		if (assign.getVariNode().getVariable().contains("$")) {
			String str = FuncStr(assign.getlistNodes());
			StrMap.put(assign.getVariNode().getVariable(), str);
		}

	}

// process string functions
	public String FuncStr(Node Func) {
		FunctionNode Strfunc = (FunctionNode) Func;

		String Str = null;
		int placeholder = 0;
		int Midplaceholder = 0;
		float flo = 0;
		if (Strfunc.getFuncStr().equals("LEFTS")) {
			for (Node left : Strfunc.getFunc()) {
				if (left.getClass().equals(StringNode.class)) {
					Str = ((StringNode) left).getStr();

				}
				if (left.getClass().equals(IntegerNode.class)) {
					placeholder = ((IntegerNode) left).getNumber();

				}

			}
			return Str.substring(placeholder);
		}

		if (Strfunc.getFuncStr().equals("RIGHTS")) {
			for (Node left : Strfunc.getFunc()) {
				if (left.getClass().equals(StringNode.class)) {
					Str = ((StringNode) left).getStr();

				}
				if (left.getClass().equals(IntegerNode.class)) {
					placeholder = ((IntegerNode) left).getNumber();

				}

			}
			return Str.substring(Str.length() - placeholder);
		}

		if (Strfunc.getFuncStr().equals("MIDS")) {
			for (int i = 0; i < Strfunc.getFunc().size(); i++) {
				Node nodefunc = Strfunc.getFunc().get(i);
				if (Strfunc.getFunc().get(i).getClass().equals(StringNode.class)) {
					Str = ((StringNode) nodefunc).getStr();
					// System.err.println(Str);
				}
				if (i == 1) {
					if (Strfunc.getFunc().get(i).getClass().equals(IntegerNode.class)) {
						placeholder = ((IntegerNode) nodefunc).getNumber();
						// System.err.println(placeholder);
					}
				}
				if (i == 2) {
					if (Strfunc.getFunc().get(i).getClass().equals(IntegerNode.class)) {
						Midplaceholder = ((IntegerNode) nodefunc).getNumber();

					}
				}
			}

			return Str.substring(placeholder, Midplaceholder);

		}
		if (Strfunc.getFuncStr().equals("NUMS")) {
			for (Node Num : Strfunc.getFunc()) {
				if (Num.getClass().equals(IntegerNode.class)) {
					placeholder = ((IntegerNode) Num).getNumber();
					return String.valueOf(placeholder);
				} else {
					flo = ((FloatNode) Num).getFloatNumber();
					return String.valueOf(flo);
				}
			}
		}

		return "not me";

	}

//proces Int fuctions
	public int FuncInt(Node Func) {
		FunctionNode intfunc = (FunctionNode) Func;
		Random rand = new Random();
		int random = rand.nextInt();
		if (intfunc.getFuncStr().equals("RANDOM")) {
			return random;
		}

		if (intfunc.getFuncStr().equals("VAL")) {
			for (Node left : intfunc.getFunc()) {
				if (intfunc.getFunc().get(0).getClass().equals(StringNode.class)) {
					String parseString = ((StringNode) left).getStr();
					return Integer.parseInt(parseString);
				}
			}
		}

		return 0;

	}

//process float functions
	public float FuncFloat(Node Func) {
		FunctionNode intfunc = (FunctionNode) Func;

		if (intfunc.getFuncStr().equals("FVAL")) {
			for (Node left : intfunc.getFunc()) {
				if (intfunc.getFunc().get(0).getClass().equals(StringNode.class)) {
					String parseString = ((StringNode) left).getStr();
					return Float.parseFloat(parseString);
				}
			}
		}

		return 0;

	}

//procces mathop for ints
	public int MathOpInt(Node OpInt) throws Exception {
		MathOpNode IntOp = (MathOpNode) OpInt;
		Node MathInt = IntOp.getLeft();
		Node MathIntR = IntOp.getRight();
		int left = 0;
		int right = 0;
		int sum = 0;
		if (IntOp.getLeft().getClass().equals(IntegerNode.class)) {
			left = ((IntegerNode) MathInt).getNumber();
		} else if (IntOp.getLeft().getClass().equals(MathOpNode.class)) {
			left = MathOpInt(IntOp.getLeft());
		} else {
			throw new Exception("NO FLOAT");
		}
		if (IntOp.getRight().getClass().equals(IntegerNode.class)) {
			right = ((IntegerNode) MathIntR).getNumber();
			// System.err.println(right);
		}
		if (IntOp.getRight().getClass().equals(MathOpNode.class)) {
			right = MathOpInt(IntOp.getRight());
		}
		if (IntOp.getLeft().getClass().equals(MathOpNode.class)) {
			left = MathOpInt(IntOp.getLeft());

		}
		if (IntOp.getLeft().getClass().equals(IntegerNode.class)) {
			left = ((IntegerNode) MathInt).getNumber();
		}
		if (IntOp.getRight().getClass().equals(MathOpNode.class)
				&& IntOp.getLeft().getClass().equals(MathOpNode.class)) {
			System.out.println("hello");
			right = MathOpInt(IntOp.getRight());
			left = MathOpInt(IntOp.getLeft());
			IntegerNode Int1 = new IntegerNode(left);
			IntegerNode Int2 = new IntegerNode(right);
			System.out.println(Int1 + " 1");
			System.out.println(Int2 + " 2");
			MathOpNode Op = new MathOpNode(IntOp.getNumType(), Int1, Int2);
			return MathOpInt(Op);

		}
		// INPUT "my height is" height%
		// INPUT "my name is" name$
		// INPUT "my age is" age
		if (IntOp.getNumType() == IntOp.getNumType().plus) {
			return sum = left + right;
		} else if (IntOp.getNumType() == IntOp.getNumType().Sub) {
			return sum = left - right;
		} else if (IntOp.getNumType() == IntOp.getNumType().Multy) {
			return sum = left * right;
		} else if (IntOp.getNumType() == IntOp.getNumType().Div) {
			return sum = left / right;
		}
		return -99999;

	}

//process float for mathOp
	public float MathOpFloat(Node OpFloat) throws Exception {
		MathOpNode FloatOp = (MathOpNode) OpFloat;
		Node MathFlaot = FloatOp.getLeft();
		Node MathFlaotR = FloatOp.getRight();
		float left = 0;
		float right = 0;
		float sum = 0;
		if (FloatOp.getRight().getClass().equals(IntegerNode.class)) {
			right = ((IntegerNode) MathFlaotR).getNumber();
			// System.err.println(right);
		}
		if (FloatOp.getRight().getClass().equals(MathOpNode.class)) {
			right = MathOpInt(FloatOp.getRight());
		}
		if (FloatOp.getLeft().getClass().equals(MathOpNode.class)) {
			left = MathOpInt(FloatOp.getLeft());

		}
		if (FloatOp.getLeft().getClass().equals(IntegerNode.class)) {
			left = ((IntegerNode) MathFlaot).getNumber();
		}
		if (FloatOp.getRight().getClass().equals(MathOpNode.class)
				&& FloatOp.getLeft().getClass().equals(MathOpNode.class)) {
			System.out.println("hello");
			right = MathOpInt(FloatOp.getRight());
			left = MathOpInt(FloatOp.getLeft());
			FloatNode Int1 = new FloatNode(left);
			FloatNode Int2 = new FloatNode(right);
			System.out.println(Int1 + " 1");
			System.out.println(Int2 + " 2");
			MathOpNode Op = new MathOpNode(FloatOp.getNumType(), Int1, Int2);
			return MathOpInt(Op);

		}
		// INPUT "my height is" height%
		// INPUT "my name is" name$
		// INPUT "my age is" age
		if (FloatOp.getNumType() == FloatOp.getNumType().plus) {
			return sum = left + right;
		} else if (FloatOp.getNumType() == FloatOp.getNumType().Sub) {
			return sum = left - right;
		} else if (FloatOp.getNumType() == FloatOp.getNumType().Multy) {
			return sum = left * right;
		} else if (FloatOp.getNumType() == FloatOp.getNumType().Div) {
			return sum = left / right;
		}
		return 0;

	}

// gets Input and stores them
	public void visitInput(Node inp) {
		Scanner sc = new Scanner(System.in);
		InputNode Inp = (InputNode) inp;
		for (Node InpFlex : Inp.getWord()) {
			if (InpFlex.getClass().equals(StringNode.class)) {
				System.out.println(((StringNode) InpFlex).getStr().toString());
			}
			if (InpFlex.getClass().equals(VariableNode.class)) {
				if (((VariableNode) InpFlex).getVariable().contains("%")) {
					float inpFloat = sc.nextFloat();
					FloatMap.put(((VariableNode) InpFlex).getVariable(), inpFloat);
				} else if (((VariableNode) InpFlex).getVariable().contains("$")) {
					String inpString = sc.nextLine();
					StrMap.put(((VariableNode) InpFlex).getVariable(), inpString);
				} else {
					int inpInt = sc.nextInt();
					IntMap.put(((VariableNode) InpFlex).getVariable(), inpInt);
				}
			}
		}

	}

// reads everything from data and assigns them
	public void visitRead(Node read) throws Exception {
		Read red = (Read) read;
		// if it does not work take the Advanced loop aproach
		for (int i = 0; i < newlist.size(); i++) {
			for (Node num : red.getRead()) {
				if (red.getRead().get(i).toString().contains("%")) {
					if (newlist.get(i).getClass().equals(FloatNode.class)) {
						FloatMap.put(((VariableNode) num).getVariable(), ((FloatNode) newlist.get(i)).getFloatNumber());
						i++;
					} else {
						throw new Exception("Type mistmatch1");
					}
				} else if (red.getRead().get(i).toString().contains("$")) {
					if (newlist.get(i).getClass().equals(StringNode.class)) {
						StrMap.put(((VariableNode) num).getVariable(), ((StringNode) newlist.get(i)).getStr());
						i++;
					} else {
						throw new Exception("Type mistmatch2");
					}
				} else {
					if (newlist.get(i).getClass().equals(IntegerNode.class)) {
						IntMap.put(((VariableNode) num).getVariable(), ((IntegerNode) newlist.get(i)).getNumber());
						i++;
					} else {
						throw new Exception("Type mistmatch3");
					}
				}
			}
		}

	}

// goes and makes Label Node and put the name of the Node and name of the node in the Hash
	public void visitLabel(Node lbl) {
		LabeledStatementNode labelNode = (LabeledStatementNode) lbl;
		NodeMap.put(labelNode.getLbl(), labelNode.getLabel());

	}

//just here so that it goes somewhere in the next assignment
	public int visitfor(Node For, int j) throws Exception {
		int k = 0;
		FOR fr = (FOR) For;
		String startVar = null;
		int Start = 0;
		int End = 0;
		int Step = 1;
		for (int i = 0; i < fr.getLabel().size(); i++) {
			Node forNode = fr.getLabel().get(i);
			if (fr.getLabel().get(i).getClass().equals(VariableNode.class)) {
				startVar = ((VariableNode) forNode).getVariable();
			}
			if (i == 1) {
				if (fr.getLabel().get(i).getClass().equals(IntegerNode.class)) {
					Start = ((IntegerNode) forNode).getNumber();
				}
			}
			if (i == 2) {
				if (fr.getLabel().get(i).getClass().equals(IntegerNode.class)) {
					End = ((IntegerNode) forNode).getNumber();
				}
			}
			if (i == 3) {
				if (fr.getLabel().get(i).getClass().equals(IntegerNode.class)) {
					Step = ((IntegerNode) forNode).getNumber();
				}

			}

		}
		IntMap.put(startVar, Start);
		// two fold problem
		// 1) walk through nodes until NEXT is found
		// 2) find next then tell main loop to go to the node right after Next?
		int counter = 0;
		for (int i = IntMap.get(startVar); i <= End; i += Step) {
			for (k = j; k < parser.getStateNode().size(); ++k) {

				if (parser.getStateNode().get(k).getClass().equals(LabeledStatementNode.class)) {
					visitLabel((parser.getStateNode().get(k)));
				} else if (parser.getStateNode().get(k).getClass().equals(Data.class)) {
					visitdata((parser.getStateNode().get(k)));
				} else if (parser.getStateNode().get(k).getClass().equals(Read.class)) {
					visitRead(parser.getStateNode().get(k));
				} else if (parser.getStateNode().get(k).getClass().equals(AssignmentNode.class)) {
					visitAssign(parser.getStateNode().get(k));
				} else if (parser.getStateNode().get(k).getClass().equals(InputNode.class)) {
					visitInput(parser.getStateNode().get(k));
				} else if (parser.getStateNode().get(k).getClass().equals(PrintNode.class)) {
					visitPrint(parser.getStateNode().get(k));
				} else if (parser.getStateNode().get(k).getClass().equals(IfNode.class)) {
					visitIf(parser.getStateNode().get(k));
				} else if (parser.getStateNode().get(k).getClass().equals(NextNode.class)) {
					break;
				}
			}
		}

		IntMap.remove(startVar);

		return k;
	}

// takes the list from data and puts the elements in the new list
	public void visitdata(Node Dat) {
		Data dat = (Data) Dat;

		for (int i = 0; i < dat.getdata().size(); i++) {
			newlist.add(dat.getdata().get(i));

		}

	}

}
