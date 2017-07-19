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



import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Parser {
	
	FA[] stack=new FA[255];//stack to hold finite automata
	int stackcounter=0;//counter for stack placement 
	
	
	
	Parser(String filename){
		int count=1;//initialize count for the state counting
		File file=new File(filename);//construct a file object
		try {
			@SuppressWarnings("resource")
			Scanner line = new Scanner(file);//using scanner to read the words from file
			String tocharacters;//initialize string varible
			
			while(line.hasNextLine()){//while the file still has lines 
			tocharacters=line.nextLine();//read in first line
			char array[]=tocharacters.toCharArray();//convert string into array for parsing
			
			if(line.hasNextLine()){//if the file still has lines print information
				System.out.println("");//print newline
				System.out.print("Starting New Regular Expression ");
				for(int b=0;b<array.length;b++){//for loop to print everything in the array
					System.out.print(array[b]);//print element in the array
				}}
				else{//print the last line
					System.out.println("");//print new line
					System.out.print("Starting New Regular Expression ");
					for(int b=0;b<array.length;b++){//for loop to print everything in the array
						System.out.print(array[b]);//print element in the array
					}
				}
			
				System.out.println("");//print new line
			
			for(int i=0;i<array.length;i++){//for loop to start parse
				if ( array[i] == '&') {//if concatenation 
		
					FA nFA2=pop();//pop top of stack,set to fa
					FA nFA1=pop();//pop last element from stack, set to fa
					nFA1.cat(nFA2);//cat nFA1 and nFA2 together
					push(nFA1);//push nFA1 back to stack
					
			} else if (array[i] == '|') {//if union 
				
				
					FA nFA2=pop();//pop top of stack,set to FA
					FA nFA1=pop();//pop last element from stack, set to FA
					nFA1.union(nFA2,count);//union nFA2 and nFA1
					count+=2;//increment count by 2
					push(nFA1);//push nFA1 stack
				
				
				
				} 
				else if ( array[i] == '*') {//if star
	
					FA nFA=pop();//pop top of stack, set to FA
					nFA.star(count);//star nFA
					count++;//increment count 
					push(nFA);//push nFA to stack
					
			} else if((array[i]=='a')||(array[i]=='b')||(array[i]=='c')||(array[i]=='d')||(array[i]=='e')||(array[i]=='E')||(array[i]=='0')){//if letter 
				
					FA newFA = new FA(count);//contruct new FA
					Transistions newTransistions=new Transistions(newFA.start,newFA.end,array[i]);// add transistion for FA
					newFA.transistions.add(newTransistions);//add transistion for FA
					push(newFA);//push NFA to stack
					count+=2;//increment count 
				
			}
			else{
				System.out.println("you have entered an incorrect format for a regular expression");
				System.out.println("regular expressions should only contain a,b,c,d,e,E and be in postfix form");
				System.exit(0);
			}
			
			
			}
			
				this.print();//print NFA
				stackcounter=0;//reset stack pointer
				this.stackreset();//reset stack to null
				count=1;//reset count to 1
				System.out.println("");//print newline 
			
			}
			
		 }catch (FileNotFoundException e) {//error handling for incorrect file name
			System.out.println("The file cannot be found.");
			System.out.println("Please make sure the file path is correct");
			System.out.println("Please restart the program");}
		}
		
			
	public void push(FA auto){//push method for stack
		
		stack[stackcounter]=auto;//set stack element to FA
		stackcounter++;//increment stack
		
	}
	
	public FA pop(){//pop stack method
		
		FA other=stack[stackcounter-1];//take element form stack
		stack[stackcounter-1]=null;//set stack element to null
		stackcounter--;//decrement stack
		return(other);//return element from stack top of stack
		
	}
	
	public void stackreset(){//reset stack method
		for(int n=0;n<255;n++){//for loop to iterate through stack
			stack[n]=null;//set stack element to null
		}
	}
	
	public void print(){//print method 
		int i=0;//Initialize i
		while(i < stackcounter){//while loop, goes until stack end
			for(int z=0;z<stack[i].transistions.size();z++){//for loop to print all the transistions for the FA
			if((stack[i].transistions.get(z).first==stack[i].start)&&(stack[i].transistions.get(z).next==stack[i].end)){//if transistion matches start and end states
				System.out.printf("SF(q%d, %c) -> q%d\n",stack[i].transistions.get(z).first,stack[i].transistions.get(z).in,stack[i].transistions.get(z).next);

			}
			else if((stack[i].transistions.get(z).first==stack[i].start)){//if stack matches start state
				System.out.printf("S(q%d, %c) -> q%d\n",stack[i].transistions.get(z).first,stack[i].transistions.get(z).in,stack[i].transistions.get(z).next);

			}
			else if(stack[i].transistions.get(z).next==stack[i].end){//if stack matches end state
				System.out.printf("F(q%d, %c) -> q%d\n",stack[i].transistions.get(z).first,stack[i].transistions.get(z).in,stack[i].transistions.get(z).next);

			}
			else{//if stack doesnt match start or end states
			System.out.printf("(q%d, %c) -> q%d\n",stack[i].transistions.get(z).first,stack[i].transistions.get(z).in,stack[i].transistions.get(z).next);
			
			}
			}
			i++;//increment i
		}
	}

}
