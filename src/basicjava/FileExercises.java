package basicjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileExercises {

	/**
	 * This method reads a file and turns all the words to lower case. If the file is not found, it returns an error message.
	 * If there is an IO Exception there is also an error message returned. It then goes through the words in the file
	 * and counts the ones that are the same. 
	 * @param word
	 * @return count 
	 * @throws FileNotFoundException,IOException
	 * 
	 */
	public static int counting(String word)throws FileNotFoundException, IOException  {
		 int count = 0;
		    try {
		      BufferedReader reader = new BufferedReader(new FileReader("example.txt"));
		      String line;
		      while ((line = reader.readLine()) != null) {
		        String[] words = line.toLowerCase().split(" ");
		        for (String w : words) {
		          if (w.equals(word.toLowerCase())) {
		            count++;
		          }
		        }
		      }
		      reader.close();
		    } catch (FileNotFoundException e) {
		      System.err.println("Error: " + e.getMessage());
		    } catch (IOException e) {
		      System.err.println("Error: " + e.getMessage());
		    }
		    return count;
		  }
		  
		
	/**
	 * This method reads and creates a new  file with all letters are changed to upper case.
	 * @param inputFile,outputFile
	 * @throws IOException
	 */
	public static void toUpper(String inputFile, String outputFile) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
	    String line;
	    while ((line = reader.readLine()) != null) {
	      String upperLine = line.toUpperCase();
	      writer.write(upperLine);
	      writer.newLine();
	    }
	    reader.close();
	    writer.close();
	  }

}
