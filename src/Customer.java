/**
 * Created by ASUS on 2016/12/25.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Customer extends Thread {
    private BarberShop shop;
    private int startTime;
    private int enterCount;

    public void run(){
        try {
            sleep(startTime);
        } catch(InterruptedException e){}
        shop._enterMutex.lock();
        shop.setEnterCounting(shop.getEnterCounting()+1);
        enterCount=shop.getEnterCounting();
        System.out.println("第"+enterCount+"位客人進入店內");
        shop._enterMutex.unlock();
        shop._mutex.lock();
        //情況1：有理髮師在睡覺，把她叫醒
        if(shop.getSleepig()>0){
            shop.setSleepig(shop.getSleepig()-1);
            shop.setStanding(shop.getStanding()+1);
            System.out.println("客人叫醒理髮師，現在有"+shop.getWaiting()+"人在等待中，有"+shop.getSleepig()+"位理髮師在睡覺");
            shop._mutex.unlock();
            //理髮師起床活動
            new Barber(shop,enterCount).start();
        }
        //情況2：理髮師都在忙，且有空位可以坐，就去坐著等
        else if(shop.getWaiting()<shop.getMaxWaiting()){
            shop.setWaiting(shop.getWaiting()+1);
            System.out.println(enterCount+"號客人入座，現在有"+shop.getWaiting()+"人在等待中，有"+shop.getSleepig()+"位理髮師在睡覺");
            shop.getSeat()[shop.getWaitingIndex()]=enterCount;
            shop.setWaitingIndex(shop.getWaitingIndex()+1);
            shop._mutex.unlock();

        }
        //情況3：理髮師都在忙，且也沒有多的座位可以坐，直接離開不剪了
        else{
            System.out.println("座位已滿，第"+enterCount+"位客人直接離開，現在有"+shop.getWaiting()+"人在等待中，有"+shop.getSleepig()+"位理髮師在睡覺");
            shop._mutex.unlock();
        }
    }
    Customer(BarberShop barberShop,int startTime){
        this.shop=barberShop;
        this.startTime=startTime;
    }
}
