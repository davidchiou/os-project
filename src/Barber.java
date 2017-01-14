/**
 * Created by ASUS on 2016/12/25.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Barber extends Thread{
    private BarberShop shop;
    private int count;
    private int cusNum;
    public void run(){
        //剪完任何人的頭髮他都會回來重新確認有沒有人叫他、有沒有人在等，只有睡眠會離開迴圈
        while(true){
            shop._mutex.lock();
            //情況1：這個理髮師是被叫醒的，叫他的人在他旁邊，所以他直接幫這個叫他的人剪頭髮
           if(shop.getStanding()>0){
               shop.setStanding(shop.getStanding()-1);
               shop._mutex.unlock();
               cutting();

           }
           //情況2：這個理髮師沒人叫，而是他理完上一個客人的頭之後看到有客人在等，所以他幫坐著等的客人理髮
           else if(shop.getWaiting()>0){
                shop.setWaiting(shop.getWaiting()-1);
                cusNum=shop.getSeat()[shop.getCuttingIndex()];
                shop.setCuttingIndex(shop.getCuttingIndex()+1);
                System.out.println("理髮師去叫等待的"+cusNum+"號客人理髮，現在有"+shop.getWaiting()+"人在等待中，有"+shop.getSleepig()+"位理髮師在睡覺");
                shop._mutex.unlock();
                cutting();
            }
            //情況3：這個理髮師沒人叫，看一看也沒有人在等，所以他決定去睡覺，並結束thread
            else{
                shop.setSleepig(shop.getSleepig()+1);
                System.out.println("沒人在等啦，理髮師睡覺，現在有"+shop.getWaiting()+"人在等待中，有"+shop.getSleepig()+"位理髮師在睡覺");
                shop._mutex.unlock();
                break;
            }
        }
    }
    Barber(BarberShop barberShop,int cusNum){
        this.shop=barberShop;
        this.cusNum=cusNum;
    }
    public void cutting(){
        System.out.println("第"+cusNum+"位客人開始理髮");
        try {
            //剪三秒完成剪頭髮的動作
            sleep(3000);
        } catch(InterruptedException e){}
        System.out.println("第"+cusNum+"位客人理髮完畢");
    }
}
