package TestCode;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Basic {

	public static void main(String[] args) throws Exception {
		// Where the file is located
		Path input = Paths.get("C:\\Users\\hafez\\Desktop\\Spring 2021\\ICSI 311\\Proj\\Num.txt");
		// Store it
		List<String> lines = Files.readAllLines(input, StandardCharsets.UTF_8);

		String TheActualLines = "\n";
		// back to a string
		String resoultion = String.join(TheActualLines, lines);
		//System.out.println(resoultion);
		// pass it to the method and get the lexing
		List<Token> tok = Lexer.Lex(resoultion);
		//System.out.println(tok);
		Parser par = new Parser(tok);
		Interpreter Inter = new Interpreter(par.parse());
		try {
			//System.out.println(par.parse());
			//System.out.println(Inter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(tok);
		
	}

}
