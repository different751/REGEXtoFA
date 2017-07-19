/*Name: Tyler Coy
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



import java.util.LinkedList;
import java.util.ListIterator;

public class FA {
	int start;//initialize start,holds start state
	int end;//initiialize end, holds end state
	LinkedList<Transistions> transistions;//intitizlize transistions as a list 
	char epsilon='E';//declare epsilon as char E
	
	public FA(int count){//FA constructor 
		this.start=count;//change start state to count
		this.end=count+1;//change end state to count
		this.transistions = new LinkedList<Transistions>();//attach list to NFA
	}
	
	
	public void cat(FA other){//concatenation method for NFA
		Transistions cats = new Transistions(this.end,other.start,'E');//make a new Transition for first NFA and second NFA
		this.transistions.add(cats);//add the transitions to the list
		this.transistions.addAll(other.transistions);//add all of second FA to the first FA list
		this.end=other.end;//change end to the second FA end
	}
	
	public void union(FA newunion,int count){//method of union 
		FA newFA=new FA(count);//construct a new FA
		Transistions first=new Transistions(newFA.start,this.start,epsilon);//add transition for newFA to first NFA start
		this.transistions.add(first);//add the transition to list
		Transistions second=new Transistions(newFA.start,newunion.start,epsilon);//add transition for newFA to second NFA start
		this.transistions.add(second);//add the transition to the list
		Transistions third=new Transistions(this.end,newFA.end,epsilon);//add transition for first NFA to newFA final 
		this.transistions.add(third);//add transition to the list
		Transistions fourth=new Transistions(newunion.end,newFA.end,epsilon);//add transition for second NFA to newFA final
		this.transistions.add(fourth);//add transition to the list
		this.transistions.addAll(newunion.transistions);//add transitions for the second NFA to the first nfa transition list
		this.end=newFA.end;//set end state to newFA end state
		this.start=newFA.start;//set start state to newFA start state
	}
	
	public void star(int count){//star method
		Transistions first = new Transistions(this.end,this.start,epsilon);//add transition for first NFA end to first NFA start
		this.transistions.add(first);//add Transition to the list
		Transistions second=new Transistions(this.start,this.end+1,epsilon);//add transition for first NFA start to a new state 
		this.transistions.add(second);//add Transitions to the list
		this.end=this.end+1;//set the end state to new end state
	}

}
