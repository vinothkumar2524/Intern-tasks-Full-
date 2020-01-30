package maximum.character;
import java.util.*;
public class MaxCharacterOld
{
	public void menu()
	{
		Scanner inp=new Scanner(System.in);
		System.out.println("Press 1 to enter input");
		System.out.println("Press 0 to exit ");
		int option =inp.nextInt();
		//String name=inp.nextLine();
		switch(option)
		{
		case 1: Scanner inp1=new Scanner(System.in);
				System.out.println("Enter the Input");
				String input=inp1.nextLine();
				maximumCharacter(input);
				menu();
		break;
		case 0:inp.close();
			System.out.println("Thank you");
		break;
		default:System.out.println("Enter valid option");
				menu();
		}
		
	}
	public void maximumCharacter(String input)
	{
		ArrayList<Integer> freq=new ArrayList<Integer>();
		ArrayList<Character> characters=new ArrayList<Character>();
		ArrayList<Character> sameChar=new ArrayList<Character>();
		ArrayList<Integer> asciivalue=new ArrayList<Integer>();
		String string=input;
		for(int i=0;i<string.length();i++)
		{
			characters.add(string.charAt(i));
		}
		//System.out.println(characters);
		int count=0;
		for(int i=0;i<characters.size();i++)
		{
			int k=1;
			freq.add(1);
			for(int j=i+1;j<characters.size();j++)
			{
				
				if(characters.get(i)!='0'&&characters.get(i)!=' ')
				{
					
					if(characters.get(i)==characters.get(j))
					{
						count++;
						k++;
						freq.set(i, k);
						characters.set(j,'0');
					}
				}
			}
		}
		//System.out.println(freq);
		//System.out.println(characters);
		
		if(count!=0)
		{
			int max=freq.get(0);
			char maxchar=' ';
			for(int i=0;i<freq.size();i++)
			{
				if(max<=freq.get(i))
				{
					max=freq.get(i);
					maxchar=characters.get(i);
				}
			}
			//System.out.println(maxchar);
			
			for(int i=0;i<freq.size();i++)
			{
				if(freq.get(i)==max)
				{
					sameChar.add(characters.get(i));
				}
			}
			//System.out.println(sameChar);
			char alpha;
			int ascii;
			for(int i=0;i<sameChar.size();i++)
			{
				alpha=sameChar.get(i);
				ascii=alpha;
				asciivalue.add(ascii);
			}
			//System.out.println(asciivalue);
			int asciimax=asciivalue.get(0);
			char asciicharmax=' ';
			for(int i=0;i<asciivalue.size();i++)
			{
				if(asciimax<=asciivalue.get(i))
				{
					asciimax=asciivalue.get(i);
					asciicharmax=sameChar.get(i);
				}
			}
			System.out.println(asciicharmax+" is the Maximum repeated character");
		}
		else
			System.out.println("No characters are repeated");
		
	}
	public static void main(String args[])
	{
		MaxCharacterOld obj=new MaxCharacterOld();
		obj.menu();
	}
}