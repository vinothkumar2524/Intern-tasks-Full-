package control.statements;

public class SmartRooms {
	int time;
	int personsClockedIn;
	public void lights()
	{
		if(time>=18 && time<=8)
		{
			System.out.println("switch on lights");
		}
		else
		{
			System.out.println("switch off lights");
		}
	}
	public void music()
	{
		while(personsClockedIn>0)
		{
			System.out.println("play music....");
		}
	}
	public void snacks()
	{
		for(int i=0;i<personsClockedIn;i++)
		{
			System.out.println("order snacks");
		}
	}
	public void selectMode()
	{
		int mode=0;//input from user
		switch(mode)
		{
		case 1:System.out.println("Mode one selected");
		break;
		case 2:System.out.println("Mode two selected");
		break;
		case 3:System.out.println("Mode three selected");
		break;
		default:System.out.println("Enter valid option");
		
		}
	}
}
