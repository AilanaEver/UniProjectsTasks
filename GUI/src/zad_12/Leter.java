package zad_12;

public class Leter extends Thread {
    char c;
    boolean suspended;

    Leter(char c){
        this.c = c;
    }

    @Override
    public void run(){
        while(true){
            try{
                synchronized (this){
                    while (suspended) wait();
                }
                int time = (int) (Math.random() * 900 +100);
                System.out.print(c);
                Thread.sleep(time);

            }catch(InterruptedException inter){
                System.out.println("inter" + c);
                return;
            }
        }
    }

    synchronized void suspendThread(){
        suspended = true;
    }

    synchronized void resumeThread(){
        suspended = false;
        this.notify();
    }
}
