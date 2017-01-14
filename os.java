package os;
import java.lang.*;
import java.util.*;
import java.io.*; 
import os.BarberShop;

public class os 
{
	
	public static void main(String[] argv)
	{
		int standing = 0; int waiting = 0; int sleeping = 0; int max_waiting = 2;
		
		Customer c[] = new Customer[10]; //開一個Customer陣列
		BarberShop shop = new BarberShop(standing, waiting, sleeping, max_waiting);
		Random select= new Random();
		
		for(int k=0; k<3 ;k++)
		{
			new Barber(shop).start();
		}
		//Barber barber = new Barber(shop);
		//barber.start();
		for(int i=0; i<10 ;i++)
		{
			c[i] = new Customer(shop,500+select.nextInt(5500)); //初始化陣列
		}
		
		for(int j=0; j<10; j++)
		{
			c[j].start();
		}
		
		
	}
}

class Barber extends Thread 
{
	private int number;
	BarberShop shop;
	Barber(BarberShop shop)
	{
		this.shop = shop;
	}
	public void run()
	{
		while(true)
		{
			shop.mutex.lock();
			if(shop.standing > 0)
			{
				shop.standing--;
				//shop.cutNumber++;
				//number = shop.cutNumber;
				number = shop.cut_pointer;
				shop.cut_pointer++;
				shop.mutex.unlock();
				cut();
			}
			else if(shop.standing == 0 && shop.waiting > 0)
			{
				shop.waiting--;
				//shop.cutNumber++;
				//number = shop.cutNumber;
				number = shop.cut_pointer;
				shop.cut_pointer++;
				shop.mutex.unlock();
				cut();
			}
			else if(shop.standing == 0 && shop.waiting ==0)
			{
				shop.sleeping++;
				shop.mutex.unlock();
				break;
			}
		}
	}
	
	public void cut()
	{
		try
		{
			//System.out.print(number+"號客人進去剪頭髮");
			System.out.println(shop.array[number]+"號客人進去剪頭髮");// new added
			sleep(3000);
		}
		catch(InterruptedException e)
		{
		}
		//System.out.println(number+"號客人剪完了");
		System.out.println(shop.array[number]+"號客人剪完了"); // new added
	}
}

class Customer extends Thread
{
	private int number;
	int enterTime;
	BarberShop shop;
	private int max_waiting;
	public void run()
	{
		try
		{
			sleep(enterTime);
		}
		catch(Exception e)
		{
			
		}
		shop.mutex.lock();
		shop.enterNumber++;
		number = shop.enterNumber;
		
		if(shop.waiting >= max_waiting)
		{
			leave();
			shop.mutex.unlock();
		}
		else
		{
			System.out.println(number+"號客人進入理髮廳");
			if(shop.sleeping > 0 && shop.standing == 0)
			{
				shop.sleeping--;
				shop.standing++;
				shop.array[shop.enter_pointer] = number; //new added
				shop.enter_pointer++; // new added
				shop.mutex.unlock();
				Barber barber = new Barber(shop);
				barber.start();
				
			}
			else
			{
				shop.waiting++;
				shop.array[shop.enter_pointer] = number; //new added
				shop.enter_pointer++; // new added
				shop.mutex.unlock();
				System.out.println(number+"號客人坐椅子等待");
			}
		}
		
	}
	
	Customer(BarberShop shop,int time)
	{
		this.shop = shop;
		this.enterTime = time;
		this.max_waiting = shop.max_waiting;
	}
	
	public void leave()
	{
		System.out.println("客滿"+number+"號客人離開理髮廳");
	}
}


