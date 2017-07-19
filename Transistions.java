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



public class Transistions {
	public int first;//initialize first
	public int next;//initialize next
	public char in;//initialize in 
	
	public Transistions(int firststate, int nextstate, char input){//constructor for transitions
		this.first=firststate;//set first to the first state of the NFA
		this.next=nextstate;//set next to the next state of the NFA
		this.in=input;//set the input for the symbol of the NFA
	}

}
