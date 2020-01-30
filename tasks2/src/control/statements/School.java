package control.statements;

public class School {
	boolean notPaidFees=true;
	int mark;
	public void sendReminder()
	{
		do
		{
			System.out.println("Send reminder message");
		}
		while(notPaidFees);
	}
	public void studentPerformance()
	{
		if(mark>=40)
		{
			System.out.println("pass...");
			if(mark>=80)
			{
				System.out.println("performed well..");
			}
			else
			{
				System.out.println("try to do better..");
			}
		}
		else
		{
			System.out.println("failed...retry and succeed");
		}
	}
	public void studentLevel()
	{
		if(mark>90)
		{
			System.out.println("Excellent");
		}
		else if(mark>60)
		{
			System.out.println("Good");
		}
		else if(mark>=40)
		{
			System.out.println("Danger");
		}
		else 
		{
			System.out.println("Fail");
		}
	}
	public void questionPaperAllocation()
	{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(i==j)
					{
						continue ;
					}
					System.out.println(i+" "+j);
				}
			}
	}
	
}
