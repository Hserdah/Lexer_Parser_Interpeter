package TestCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {

	public static List<Token> Lex(String Input) {
		List<Token> result = new ArrayList<Token>();
		int counter = 0;
		String text = "";
		String words = "";
		String nums = "";
		// takes it apart
		String[] PRINT = { "PRINT" };
		String[] INPUT = { "INPUT" };
		String[] DATA = { "DATA" };
		String[] READ = { "READ" };
		String[] GOSUB = { "GOSUB" };
		String[] RETURN = { "RETURN" };
		String[] FOR = { "FOR" };
		String[] NEXT = { "NEXT" };
		String[] STEP = { "STEP" };
		String[] TO = { "TO" };
		String[] IF = { "IF" };
		String[] RANDOM = { "RANDOM" };
		String[] LEFT$ = { "LEFTS" };
		String[] RIGHT$ = { "RIGHTS" };
		String[] MID$ = { "MIDS" };
		String[] NUM$ = { "NUMS" };
		String[] VAL = { "VAL" };
		String[] THEN = { "THEN" };
		String[] VALp = { "FVAL" };
		for (int i = 0; i < Input.length(); i++) {
			char charInput = Input.charAt(i);
			// System.out.println(i + " i ");
			// see if it is a digit or or not
			Boolean InputNum = Character.isDigit(Input.charAt(i));
			Boolean InputLetter = Character.isLetter(Input.charAt(i));
			// State machine starts and compare
			if (charInput == '+') {
				result.add(new Token(Token.Type.plus, "+"));

			} else if (charInput == '-') {
				result.add(new Token(Token.Type.Sub, "-"));

			} else if (charInput == '*') {
				result.add(new Token(Token.Type.Multy, "*"));

			} else if (charInput == '/') {
				result.add(new Token(Token.Type.Div, "/"));

			} else if (InputNum) {

				for (int k = i; k < Input.length(); k++) {
					// System.out.println(k + " k");
					char numInput = Input.charAt(k);
					Boolean InputNum2 = Character.isDigit(Input.charAt(k));
					if (InputNum2 || numInput == '.') {
						nums += numInput;
						// System.out.println(nums + " nums");
					} else {
						result.add(new Token(Token.Type.Num, nums));
						nums = "";
						break;
						// end for loop

					}
					counter++;
				}

				i += counter;
				counter = 0;

			} else if (charInput == '.') {
				result.add(new Token(Token.Type.point, "(.)"));

			} else if (charInput == '=') {
				result.add(new Token(Token.Type.Equals, "="));

			} else if (charInput == '<') {
				try {
					// looks at next and see if it a = or a <
					char charInput2 = Input.charAt(i += 1);
					if (charInput2 == '>') {
						result.add(new Token(Token.Type.NotEquals, "<>"));
					} else if (charInput2 == '=') {
						result.add(new Token(Token.Type.LessEquals, "<="));

					}else {
						result.add(new Token(Token.Type.Less, "<"));
					}
						
				} catch (StringIndexOutOfBoundsException e) {
					result.add(new Token(Token.Type.Less, "<"));
				}
			} else if (charInput == '>') {
				try {
					// looks at next and see if it a =
					char charInput2 = Input.charAt(i += 1);
					if (charInput2 == '=') {
						result.add(new Token(Token.Type.GreaterEquals, ">="));
					}else {
						result.add(new Token(Token.Type.Greater, ">"));
					}
						
				} catch (StringIndexOutOfBoundsException e) {
					result.add(new Token(Token.Type.Greater, ">"));
				}
			} else if (charInput == '(') {
				result.add(new Token(Token.Type.LPAREN, "("));
			} else if (charInput == ')') {
				result.add(new Token(Token.Type.RPAREN, ")"));

			} else if (charInput == '"') {
				// loop to record words for string ends with "
				for (int j = i + 1; j < Input.length(); j++) {
					char charLetter = Input.charAt(j);
					if (charLetter != '"') {
						text += charLetter;
						counter++;
					} else if (charLetter == '"') {
						result.add(new Token(Token.Type.String, text));
						text = "";
						break;

					}
				}
				i += counter + 1;
				counter = 0;

			} else if (charInput == ',') {
				result.add(new Token(Token.Type.Com, ","));

			} else if (InputLetter) {
				// looks for words and known words and labels rejects anything that isn't a
				// letter
				for (int j = i; j < Input.length(); j++) {
					char charWords = Input.charAt(j);
					Boolean InputWords = Character.isLetter(Input.charAt(j));

					
					if (InputWords) {
						words += charWords;
						counter++;
					}
					
					// keeping a array of resved words (for now PRINT in the future words like if
					// and while will be added and recognized)
					if (Arrays.asList(PRINT).contains(words)) {
						result.add(new Token(Token.Type.PRINT, words));
						System.out.println(words + "words");
						words = "";
						break;
					}
					if (Arrays.asList(INPUT).contains(words)) {
						result.add(new Token(Token.Type.INPUT, words));
						words = "";
						break;
					}
					if (Arrays.asList(DATA).contains(words)) {
						result.add(new Token(Token.Type.DATA, words));
						words = "";
						break;
					}
					if (Arrays.asList(READ).contains(words)) {
						result.add(new Token(Token.Type.READ, words));
						words = "";
						break;
					}
					if (charWords == ':') {
						result.add(new Token(Token.Type.Label, words));
						words = "";
						break;
					}
					
					if (Arrays.asList(GOSUB).contains(words)) {
						result.add(new Token(Token.Type.GOSUB, words));
						words = "";
						break;
					}
					if (Arrays.asList(RETURN).contains(words)) {
						result.add(new Token(Token.Type.RETURN, words));
						words = "";
						break;
					}
					if (Arrays.asList(FOR).contains(words)) {
						result.add(new Token(Token.Type.FOR, words));
						words = "";
						break;
					}
					if (Arrays.asList(STEP).contains(words)) {
						result.add(new Token(Token.Type.STEP, words));
						words = "";
						break;
					}
					if (Arrays.asList(NEXT).contains(words)) {
						result.add(new Token(Token.Type.NEXT, words));
						words = "";
						break;
					}
					if (Arrays.asList(IF).contains(words)) {
						result.add(new Token(Token.Type.IF, words));
						words = "";
						break;
					}
					if (Arrays.asList(RANDOM).contains(words)) {
						result.add(new Token(Token.Type.RANDOM, words));
						words = "";
						break;
					}
					if (Arrays.asList(LEFT$).contains(words)) {
						result.add(new Token(Token.Type.LEFT$, words));
						words = "";
						break;
					}
					if (Arrays.asList(RIGHT$).contains(words)) {
						result.add(new Token(Token.Type.RIGHT$, words));
						words = "";
						break;
					}
					if (Arrays.asList(MID$).contains(words)) {
						result.add(new Token(Token.Type.MID$, words));
						words = "";
						break;
					}
					if (Arrays.asList(NUM$).contains(words)) {
						result.add(new Token(Token.Type.NUM$, words));
						words = "";
						break;
					}
					if (Arrays.asList(VAL).contains(words)) {
						result.add(new Token(Token.Type.VAL, words));
						words = "";
						break;
					}if (Arrays.asList(THEN).contains(words)) {
						result.add(new Token(Token.Type.THEN, words));
						words = "";
						break;
					}if (Arrays.asList(TO).contains(words)) {
						result.add(new Token(Token.Type.TO, words));
						words = "";
						break;
					}if (Arrays.asList(VALp).contains(words)) {
						result.add(new Token(Token.Type.Valp, words));
						words = "";
						break;
					}if (charWords == '%') {
						result.add(new Token(Token.Type.Words, words + "%"));
						words = "";
						break;
					}if (charWords == '$') {
						result.add(new Token(Token.Type.Words, words + "$"));
						words = "";
						break;
					}
					if (!InputWords) {
						result.add(new Token(Token.Type.Identifier, words));
						words = "";
						break;
					}

				}
				i += counter - 1;
				//System.out.println(i + " i");
				counter = 0;
			}if(charInput == '\n') {
				result.add(new Token(Token.Type.END, "END"));	
			}
			

			// State machines add the appropriate token
		}
		result.add(new Token(Token.Type.END, "END"));
		return result;
	}
}
