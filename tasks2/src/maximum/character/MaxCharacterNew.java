package maximum.character;
import java.util.*;
public class MaxCharacterNew{
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
		
		char arr[]=input.toCharArray();
		Arrays.sort(arr);
		String sortedString=new String(arr);
		StringBuilder Stringbuild= new StringBuilder(sortedString);
		sortedString=Stringbuild.reverse().toString();
		System.out.println(sortedString);
		LinkedHashSet<Character> obj=new LinkedHashSet<Character>();
		LinkedHashMap<Character,Integer> hm=new LinkedHashMap<Character,Integer>();
		int value=0;
		int count=0;
		for(int i=0;i<input.length();i++)
		{
			if(sortedString.charAt(i)!=' ')
			{
				if(obj.add(sortedString.charAt(i))==false)
				{
					value++;
					count++;
					hm.put(sortedString.charAt(i),value);
				}
				else
				{
					value=1;		
				}
			}
		}
		if(count==0)
		{
			System.out.println("No characters are repeated");
			menu();
		}
		System.out.println(hm);
		String max=Collections.max(hm.values()).toString();
		
		Set<Character> keys=hm.keySet();
		Iterator<Character> itr=keys.iterator();
		System.out.println(keys);
	   String maxkey=new String();
		while(itr.hasNext())
		{
			
			String maxString=itr.next().toString();
			String maxValue=hm.get(maxString.charAt(0)).toString();
			System.out.println(max);
			System.out.println(maxValue);
			if(max.equals(maxValue))
			{
				maxkey=maxString;
				break;			}
		}
		System.out.println(obj);
		System.out.println(maxkey+" has been repeated " +max+" times");
	}
	public static void main(String args[])
	{
		MaxCharacterNew obj=new MaxCharacterNew();
		obj.menu();
	}
}
