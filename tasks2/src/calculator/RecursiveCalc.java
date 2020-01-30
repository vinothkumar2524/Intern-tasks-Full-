package calculator;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class RecursiveCalc {
	
	String math;
	ArrayList<Integer> digits =new ArrayList<>();
	ArrayList<Character> operators=new ArrayList<>();
	Scanner inp=new Scanner(System.in);
	int option,ans;
	boolean condition=true;
	public void menu()
	{
		if(condition)
		{
			try
			{
				System.out.println("Press 1 to do calculation");
				System.out.println("Press 0 to exit");
				digits.clear();
				operators.clear();
				option=inp.nextInt();
				switch(option)
				{
				case 1:this.process();
				break;
				case 0:condition=false;
				break;
				default:System.out.println("Enter valid option");
				}
			}
			catch(ArithmeticException e)
			{
				System.out.println("Cannot divide by zero");
				//inp.next();
				menu();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Enter valid input");
				inp.next();
				menu();
				
			}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Enter the input in correct format");
				//inp.next();
				menu();
			}
			catch(Exception e)
			{
				System.out.println(e);
				inp.next();
				menu();
			}
			finally
			{
				menu();
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
		ans=digits.get(0);
		digits.remove(0);
		System.out.println("The Answer is : "+ans);
	}
	
	public void getDigits()
	{
		//System.out.println("Getting digits");
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
		//System.out.println("Getting operators");
		Pattern r= Pattern.compile("[/+,-,/*,//,/^,-]");
		Matcher m=r.matcher(math);
		while(m.find())
		{
			operators.add(math.charAt(m.start()));
		}
		//System.out.println(operators);
		//System.out.println(digits);
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
	public static void main(String []args)
	{
		RecursiveCalc calc=new RecursiveCalc();
		 calc.menu();
	}
}
