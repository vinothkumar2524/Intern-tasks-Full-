package maximum.character;
import java.util.*;
public class CharacterCount
{
	LinkedHashMap<Character,Integer> lm=new LinkedHashMap<Character,Integer>();
	public void checkCount(Character a)
	{
		if(lm.size()!=0)
		{
			if(lm.containsKey(a))
			{
				System.out.println("The Character "+a+" has been repeated "+lm.get(a).toString()+" times");
			}
			else
			{
				System.out.println("The Character "+a+" is not repeated");
			}
		}
		else
		{
			System.out.println("You must enter any word or sentence first!");
		}
		menu();
	}
	public void menu()
	{
		Scanner inp=new Scanner(System.in);
		System.out.println("Press 1 to enter input");
		System.out.println("Press 2 to check character count");
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
		case 1: lm.clear();
				Scanner inp1=new Scanner(System.in);
				System.out.println("Enter the Input");
				String input=inp1.nextLine();
				process(input);
				menu();
		break;	
				
		case 2: Scanner inp2=new Scanner(System.in);
				System.out.println("Enter the character");
				char inpChar=inp2.next().charAt(0);
				checkCount(inpChar);
		case 0: inp.close();
				
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
			String maxkey=new String();
			count=0;
			while(itr.hasNext())
			{
				String maxString=itr.next().toString();
				String maxValue=lm.get(maxString.charAt(0)).toString();
				if(max.equals(maxValue))
				{
					maxkey=maxString;
					count++;
				}
			}
			System.out.println();
			System.out.println(maxkey+" has been repeated " +max+" times");
		}
	}
	public static void main(String args[])
	{
		CharacterCount obj=new CharacterCount();
		obj.menu();
		System.out.println("Thank you");
		
	}
}