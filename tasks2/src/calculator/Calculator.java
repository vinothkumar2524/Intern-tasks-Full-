package calculator;
import java.util.*;
public class Calculator {
	
	float result,number1,number2;
	int option;
	boolean condition=true;
	String operation;
	Scanner input=new Scanner(System.in);
	
	private void displayMenu()
	{
		while(condition)
		{
				System.out.println("Enter 1 for Addition");
				System.out.println("Enter 2 for Subtraction");
				System.out.println("Enter 3 for Multiplication");
				System.out.println("Enter 4 for Division");
				System.out.println("Enter 0 for exit");
				System.out.println("Enter your option");
				try
				{
					option=input.nextInt();
					caseMethod(option);	
				}
				catch(InputMismatchException e)
				{
					System.out.println("___________________________________");
					System.out.println("Enter a vaid option");
					System.out.println("-----------------------------------");
					input.next();
					displayMenu();
				}
		}
	}
	private void caseMethod(int option)
	{
		switch(option)
		{
		case 1:subMenu();
				addNumbers(number1,number2);
				break;
		case 2:subMenu();
				subtractNumbers(number1,number2);
				break;
		case 3:subMenu();
				multiplyNumbers(number1,number2);
				break;
		case 4:subMenu();
				divideNumbers(number1,number2);
				break;
		case 0:condition=false;
				System.out.println("Thanks for using this calculator");
				break;
		default:System.out.println("Enter valid option");
		}
	}
	private void subMenu()
	{
		try 
		{
			System.out.println("Enter the first number");
			number1=input.nextFloat();
			System.out.println("Enter the second number");
			number2=input.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("___________________________________");
			System.out.println("Enter only numbers");
			System.out.println("-----------------------------------");
			input.next();
			subMenu();
		}
	}
	private void displayResult(float result,String operation)
	{	
		System.out.println("The "+operation+ " is : "+result);
		System.out.println("------------------------------------");
	}
	private void addNumbers(float number1,float number2)
	{
		result=number1+number2;
		operation="SUM";
		displayResult(result,operation);
	}
	private void subtractNumbers(float number1,float number2)
	{
		result=number1-number2;
		operation="DIFFERENCE";
		displayResult(result,operation);
	}
	private void multiplyNumbers(float number1,float number2)
	{
		result=number1*number2;
		operation="PRODUCT";
		displayResult(result,operation);
	}
	private void divideNumbers(float number1,float number2)
	{
		if(number2==0)
		{
			System.out.println("cannot divide a number by zero");
			caseMethod(4);
			
		}
		else
		{
			result=number1/number2;
			operation="QUOTIENT";
			displayResult(result,operation);
		}
	}
	public static void main(String args[])
	{
		Calculator calculator=new Calculator();
		calculator.displayMenu();
	}
}
