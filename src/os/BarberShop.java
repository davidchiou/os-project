package os;

import sun.awt.Mutex;

public class BarberShop 
{
	public int standing; public int waiting; public int sleeping; public int cutNumber; public int enterNumber;
	public int max_waiting;
	
	public int[] array = new int[50];//new added
	public int cut_pointer ;// new added
	public int enter_pointer ; // new added
	
	public Mutex mutex = new Mutex();
	BarberShop(int standing, int waiting, int sleeping, int max_waiting)
	{
		this.standing = standing;
		this.waiting = waiting;
		this.sleeping= sleeping;
		this.max_waiting = max_waiting;
	}
	
	public int get_standing()
	{
		return standing;
	}
	
	public int get_waiting()
	{
		return waiting;
	}
	
	public int get_sleeping()
	{
		return sleeping;
	}
	
	public void set_standing(int standing)
	{
		this.standing = standing;
	}
	
	public void set_waiting(int waiting)
	{
		this.waiting = waiting;
		System.out.println(this.waiting);
	}
	
	public void set_sleeping(int sleeping)
	{
		this.sleeping = sleeping;
	}
}