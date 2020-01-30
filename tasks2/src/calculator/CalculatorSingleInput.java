package calculator;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CalculatorSingleInput {
	
	static String math;
	static ArrayList<Integer> digits =new ArrayList<>();
	static ArrayList<Character> operators=new ArrayList<>();
	static Scanner inp=new Scanner(System.in);
	static int option;
	static boolean condition=true;
	public static void menu()
	{
		while(condition)
		{
			System.out.println("Press 1 to do calculation");
			System.out.println("Press 0 to exit");
			option=inp.nextInt();
			switch(option)
			{
			case 1:process();
			break;
			case 0:condition=false;
			break;
			default:System.out.println("Enter valid option..!");
			}
		}
	}
	public static void process()
	{
		System.out.println("Enter the numbers");
		math=inp.next();
		getDigits();
		getOperators();
		getNextOperator(operators);
		System.out.println(digits);
	}
	public static void main(String []args)
	{
		
		
		 menu();
	}
	public static  void getDigits()
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
	public static void getOperators()
	{
		System.out.println("Getting operators");
		Pattern r= Pattern.compile("[/+,-,/*,//,-]");
		Matcher m=r.matcher(math);
		while(m.find())
		{
			operators.add(math.charAt(m.start()));
		}
		System.out.println(operators);
		System.out.println(digits);
	}
	public static void getNextOperator(ArrayList<Character> operators)
	{
		
		A: for(int j=0;j<operators.size();j++)
		{
			int i=0;
			j--;
				if(operators.get(i)=='/')
				{
					operators.remove(i);
					digits.set(0,digits.get(i)/digits.get(i+1));
					digits.remove(i+1);
					continue A;
				}
				if(operators.get(i)=='*')
				{
					operators.remove(i);
					digits.set(i,digits.get(i)*digits.get(i+1));
					digits.remove(i+1);
					System.out.println("sub");
					continue A;
				}
				if(operators.get(i)=='+')
				{
					operators.remove(i);
					digits.set(i,digits.get(i)+digits.get(i+1));
					digits.remove(i+1);	
					System.out.println("multi");
					continue A;
				}
				if(operators.get(i)=='-')
				{
					operators.remove(i);
					digits.set(i,digits.get(i)-digits.get(i+1));
					digits.remove(i+1);	
					System.out.println("divide");
					continue A;	
				}
				
		}
	}
}
