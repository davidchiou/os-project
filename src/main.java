import java.util.Random;
import java.util.Scanner;
/**
 * Created by ASUS on 2016/12/25.
 */
public class main {
    public static void main(String args[])
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入m：");
        int m=scanner.nextInt();
        System.out.println("請輸入n：");
        int n=scanner.nextInt();
        System.out.println("請輸入客人數量：");
        int cusSize=scanner.nextInt();
        BarberShop shop=new BarberShop(0,0,0,n,cusSize);
        Customer c[] = new Customer[cusSize];
        Random select= new Random();
        for(int i= 1;i<=cusSize;i++) //讓客人以不同時間進入理髮廳
            c[i-1] = new Customer(shop,500+select.nextInt(5500));

       for(int i=1;i<=m;i++){ //三位理髮師
            new Barber(shop,0).start();
       }

       for(int j=1;j<=cusSize;j++) { //開始執行Thread
           c[j-1].start();
       }
       new Customer(shop,15000).start();
    }
}
