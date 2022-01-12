package zad_12;

import java.util.ArrayList;

public class MainThread extends Thread {
    String str;
    int count;
    int indexSuspend=0;
    ArrayList<Leter> list = new ArrayList<>();

    MainThread(String str){
        this.str=str;
        for (int i = 0; i < str.length(); i ++){
            Leter t = new Leter (str.charAt(i));
            list.add(t);
            if ( i == 0){
                t.suspendThread();
            }
            t.start();
        }
        this.count = 3 * list.size();
    }

    @Override
    public void run(){
        try {
            while (count >= 0) {
                Thread.sleep(2500);

                list.get(indexSuspend).resumeThread();
                System.out.print("\n Thread" + list.get(count).c + "resumed");
                indexSuspend = (indexSuspend + 1) % list.size();
                list.get(indexSuspend).suspendThread();
                System.out.println("Thread" + list.get(indexSuspend).c + "suspended  ");
                count--;
            }
            for (Leter t : list) {
                t.interrupt();
            }
        }catch (InterruptedException inter){
            return;
        }
    }
}
