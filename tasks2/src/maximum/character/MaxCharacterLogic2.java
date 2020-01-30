package maximum.character;
import java.util.*;
public class MaxCharacterLogic2
{
	public void menu()
	{
		Scanner inp=new Scanner(System.in);
		System.out.println("Press 1 to enter input");
		System.out.println("Press 0 to exit ");
		int option=0;
		try
		{
			option=inp.nextInt();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Enter valid option");
			menu();
		}
		switch(option)
		{
		case 1: Scanner inp1=new Scanner(System.in);
				System.out.println("Enter the Input");
				String input=inp1.nextLine();
				process(input);
				menu();
		break;
		case 0:inp.close();
			System.out.println("Thank you");
		break;
		default:System.out.println("Enter valid option");
				menu();
		}
	}
	public void process(String input)
	{
		String sentence=input;
		char arr[]=sentence.toCharArray();
		Arrays.sort(arr);
		String sortedString=new String(arr);
		StringBuilder stringBuild=new StringBuilder(sortedString);
		sortedString=stringBuild.reverse().toString().trim();
		int count=1;
		LinkedHashMap<Character,Integer> lm=new LinkedHashMap<Character,Integer>();
		int flag=0;
		for(int i=0;i<sortedString.length()-1;i++)
		{
			if(i<sortedString.length())
			{
				if(sortedString.charAt(i)!=' ')
				{
					if(sortedString.charAt(i)==sortedString.charAt(i+1))
					{
						count++;
						lm.put(sortedString.charAt(i), count);
						flag++;
					}
					else
					{
						count=1;
					}
				}
			}
		}
		if(flag==0)
		{
			System.out.println("No Characters has been repeated");
		}
		else
		{
			System.out.println(lm);
			String max=Collections.max(lm.values()).toString();
			Set<Character> keys=lm.keySet();
			System.out.println(keys);
			Iterator<Character> itr=keys.iterator();
			String maxkey[]=new String[10];
			count=0;
			while(itr.hasNext())
			{
				String maxString=itr.next().toString();
				String maxValue=lm.get(maxString.charAt(0)).toString();
				if(max.equals(maxValue))
				{
					maxkey[count]=maxString;
					count++;
				}
			}
			System.out.println();
			System.out.println(maxkey[0]+" has been repeated " +max+" times");
		}
	}
	public static void main(String args[])
	{
		MaxCharacterLogic2 obj=new MaxCharacterLogic2();
		obj.menu();
		
	}
}