/**
 * Created by ASUS on 2016/12/25.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class BarberShop {
    public Lock _enterMutex=new ReentrantLock(true);
    public Lock _mutex = new ReentrantLock(true);
    private int maxWaiting;
    private int[] seat;
    private int waiting;
    private int sleepig;
    private int standing;
    private int enterCounting=0;
    private int waitingIndex=0;
    private int cuttingIndex=0;
    BarberShop(int waiting,int sleepig,int standing,int maxWaiting,int cusSize){
        this.waiting=waiting;
        this.sleepig=sleepig;
        this.standing=standing;
        this.maxWaiting=maxWaiting;
        this.seat=new int[cusSize];
    }
    public int getWaiting(){
        return waiting;
    }
    public void setWaiting(int newWaiting){
        waiting=newWaiting;
    }
    public int getSleepig(){
        return sleepig;
    }
    public void setSleepig(int newSleepig){
        sleepig=newSleepig;
    }
    public int getStanding(){
        return standing;
    }
    public void setStanding(int newStanding){
        standing=newStanding;
    }
    public int getEnterCounting(){return  enterCounting;}
    public void setEnterCounting(int newEnterCounting){enterCounting=newEnterCounting;}
    public int getMaxWaiting(){
        return maxWaiting;
    }
    public int getWaitingIndex(){return waitingIndex;}
    public void setWaitingIndex(int newWaitingIndex){waitingIndex=newWaitingIndex;}
    public int getCuttingIndex(){return cuttingIndex;}
    public void setCuttingIndex(int newCuttingIndex){cuttingIndex=newCuttingIndex;}
    public int[] getSeat(){return seat;}
}
