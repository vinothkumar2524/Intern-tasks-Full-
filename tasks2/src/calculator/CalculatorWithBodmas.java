package calculator;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CalculatorWithBodmas {
	
	 static String math;
	 ArrayList<Integer> digits =new ArrayList<>();
	 ArrayList<Character> operators=new ArrayList<>();
	static Scanner inp=new Scanner(System.in);
	static int option;
	static boolean condition=true;
	public void menu()
	{
		while(condition)
		{
			System.out.println("Press 1 to do calculation");
			System.out.println("Press 0 to exit");
			option=inp.nextInt();
			
			switch(option)
			{
			case 1:this.process();
			break;
			case 0:condition=false;
			break;
			}
		}
	}
	public void process()
	{
		 
		System.out.println("Enter the numbers");
		math=inp.next();
		getDigits();
		getOperators();
		getNextOperator(operators);
		int ans=digits.get(0);
		digits.remove(0);
		System.out.println("The Answer is : "+ans);
	}
	public static void main(String []args)
	{
		CalculatorWithBodmas calc=new CalculatorWithBodmas();
		 calc.menu();
	}
	public void getDigits()
	{
		System.out.println("Getting digits");
		Pattern r= Pattern.compile("(\\d+)");
		Matcher m=r.matcher(math);
		while(m.find())
		{
			int t=Integer.parseInt(math.substring(m.start(),m.end()));
			digits.add(t);
		}
	}
	public void getOperators()
	{
		System.out.println("Getting operators");
		Pattern r= Pattern.compile("[/+,-,/*,//,/^,-]");
		Matcher m=r.matcher(math);
		while(m.find())
		{
			operators.add(math.charAt(m.start()));
		}
		System.out.println(operators);
		System.out.println(digits);
	}
	public  int exponent(int n,int e)
	{
		if(e==0)
		{
			return 1;
		}
		else
		{
			return n*exponent(n,e-1);
		}
		
	}
	public void getNextOperator(ArrayList<Character> operators)
	{
		e: for(int i=0;i<operators.size();i++)
			{
				if(operators.get(i)=='^')
				{
					operators.remove(i);
					digits.set(i, exponent(digits.get(i),digits.get(i+1)));
					digits.remove(i+1);
					i--;
					continue e;
				}	
			}
	a: for(int i=0;i<operators.size();i++)
		{
			if(operators.get(i)=='/')
			{
				operators.remove(i);
				digits.set(i, digits.get(i)/digits.get(i+1));
				digits.remove(i+1);
				i--;
				continue a;
			}
		}
	b: for(int i=0;i<operators.size();i++)
		{
			if(operators.get(i)=='*')
			{
				operators.remove(i);
				digits.set(i, digits.get(i)*digits.get(i+1));
				digits.remove(i+1);
				i--;
				continue b;
			}	
		}
	c: for(int i=0;i<operators.size();i++)
		{
			if(operators.get(i)=='+')
			{
				operators.remove(i);
				digits.set(i, digits.get(i)+digits.get(i+1));
				digits.remove(i+1);
				i--;
				continue c;
			}
			
		}
	d: for(int i=0;i<operators.size();i++)
		{
			if(operators.get(i)=='-')
			{
				operators.remove(i);
				digits.set(i, digits.get(i)-digits.get(i+1));
				digits.remove(i+1);
				i--;
				continue d;
			}
		}
	}
}
