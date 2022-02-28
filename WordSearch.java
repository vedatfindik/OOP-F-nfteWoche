import java.io.*;

public class WordSearch {
	public static String search(String inFileName, String word) throws FileNotFoundException, IOException {

		// append the "word" thing
		String[] nameParts = inFileName.split("\\.");
		String suffix = nameParts[nameParts.length - 1];
		String rest = "";

		for (int i = 0; i < nameParts.length - 2; i++) {
			rest = rest + nameParts[i] + ".";
		}
		rest = rest + nameParts[nameParts.length - 2];

		String outFileName = rest + "_" + word + "." + suffix;

		BufferedReader in = null;
		BufferedWriter out = null;

		try {
			// abstract base class for representing an input stream of bytes
			InputStream inStream = new FileInputStream(inFileName);
			// using a Reader for input
			Reader r = new InputStreamReader(inStream);
			// additional buffer and methods for efficient input
			in = new BufferedReader(r);

			// abstract base class for representing an output stream of bytes
			OutputStream outStream = new FileOutputStream(outFileName);
			// using a Writer for output
			Writer w = new OutputStreamWriter(outStream);
			// additional buffer efficient output
			out = new BufferedWriter(w);

			out.write("Zeilen mit dem Wort: " + word);

			String line;
			int lineNumber = 0;
			boolean foundNothing = true; // If nothing is found, this remains true, and then we write some info into the
											// output file
			while (in.ready()) {
				lineNumber++;
				line = in.readLine(); // reading next line

				// replace eveyrthing this is not a letter with an empty space
				String cleanedLine = line.replaceAll("[^a-zA-Z]", " ");


			
				// Split by whitespaces to get every word
				String[] parts = cleanedLine.split(" "); 

				// Checking if line contains the word
				int counter = 0;
				boolean wordFound = false;
				while (counter < parts.length && wordFound == false) {
					boolean match = parts[counter].equals(word);
					if (match) {
						wordFound = true;
						foundNothing = false;
						String writeMe = "\n" + lineNumber + ": " + line;
						out.write(writeMe);
					} else
						counter++;
				}

			}
			System.out.println("Output to " + outFileName + " finished");
			if (foundNothing == true) {
				System.out.println("No match");
				out.write("\nKeine Uebereinstimmungen gefunden.");
			}

		}
		// may be thrown by FileReader
		catch (FileNotFoundException fnf) {
			// System.out.println(">> FileNotFoundException: " + fnf.getMessage());

			// throw new ArithmeticException("Student is not eligible for registration");
			throw new FileNotFoundException(">> FileNotFoundException: " + fnf.getMessage());
		}
		// my be thrown by FileReader, FileWriter
		catch (IOException io) {
			throw new IOException(">> IOException: " + io.getMessage());
			// System.out.println(">> IOException: " + io.getMessage());
			// throw new IOException();
		}
		// these instructions are always executed
		finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			System.out.println("All closed");
		}

		return "done";
	}
}