import java.io.FileNotFoundException;
import java.io.IOException;

public class WordSearchDemo {
	public static void main (String[] args) {	
		
		
		try{
			WordSearch.search("textorig.txt", "Wort");
		}
		catch(FileNotFoundException fnf) {
	        System.out.println();
			System.out.println(fnf.getMessage());
		}
	
		catch(IOException io) {
	        System.out.println();
			System.out.println(io.getMessage());
		}
		
		
		
		
		//Schwierigeres Text File
		
		try{
			WordSearch.search("text_modified.txt", "Wort");
		}
		catch(FileNotFoundException fnf) {
	        System.out.println();
			System.out.println(fnf.getMessage());
		}
	
		catch(IOException io) {
	        System.out.println();
			System.out.println(io.getMessage());
		}
	}
}
