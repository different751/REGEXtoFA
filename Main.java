/*Name: Tyler Coy
 *Email: tyler.coy@wsu.edu
 *Date: 10-4-16
 *Class: CS317
 *Assignment: Project 1 regular expressions to nfa
 *
 *Program description:
 *This program is designed to take in a regular expression in post fix notation from a file
 *and then convert that regular expression into a NFA. The output will consist of states
 *that transition with the given symbol of input. The program uses a stack to hold
 *the constructed NFAs and a list to hold the transitions of each NFA. This program 
 *was assigned to gain a better understanding of regular expressions and how to 
 *design NFAs from regular expressions.  
 * 
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//take input from standard in
	       	System.out.println("Instructions:");
	       	System.out.println("This program will read a file from your computer containing the correctly formatted regular expressions");
	       	System.out.println("Please locate the file location on your computer. The file should look like the following example");
	       	System.out.println("C:/Users/Tyler/Desktop/test.txt  Type the file location directly into the console below and hit enter");
	       	System.out.println("");
		 	System.out.println("Please enter file path to be used below:");//print to notify user for input
	        String s = br.readLine();//take line from standard in and save as a string
	        
	        Parser path=new Parser(s);//send string path to parser class
	        
	        
	        
	        
	}

}
