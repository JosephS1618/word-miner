package testing;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordMiner {
	
	public static int hashGenerator(String word) {
		int hashCode = 0;
		
		for(int i = 0; i < word.length(); i++) { // finds hash code
			hashCode += word.charAt(i) * (i+1);
		}
		
		hashCode %= 20000; // compresses words greater than 20 000
		
		return hashCode;
	}
	
	public static boolean isValid(String word) { // checks if word is valid
		if(word.length() < 3 || word.length() > 6)
			return false;
		for(int i = 0; i < word.length(); i++) 
			if(word.charAt(i) < 97 || word.charAt(i) > 122)
				return false;
		
		return true;
	}
	
	public static void main(String[] args)throws Exception {
		
		String[][] hashTable = new String[20000][10];
		
		int wordCount = 0;
		
		File infile = new File("dictionary.txt");
		Scanner input = new Scanner(infile);
		
		while(input.hasNext()) {
			String word = input.next();
			
			if(isValid(word) == true) { // if word is valid, put in array
				int row = hashGenerator(word);
				
				for(int y = 0; y < 10; y++) {
					if(hashTable[row][y] != null && hashTable[row][y].equals(word))
						break;
					
					if(hashTable[row][y] == null) {
						hashTable[row][y] = word;
						wordCount++; // Tests number of words
						break;
					}
				}
				
			}
		}
		input.close();
		
		
		
		 /* TEST
			for(int y = 0; y < 10; y++) {
				System.out.print(hashTable[1136][y] + " ");
				
			}
			System.out.println();
		*/
		
		
		System.out.println(wordCount);
		
		File outfile = new File("dictionary.txt"); 
		PrintWriter pw = new PrintWriter(outfile);
		
		for(int x = 0; x < hashTable.length; x++) {
			for(int y = 0; y < hashTable[x].length; y++) {
				if(hashTable[x][y] != null)
					pw.println(hashTable[x][y]);
			}
			
		}
		pw.close();
		

	}

}
