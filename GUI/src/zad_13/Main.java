package zad_13;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters)
            System.out.println(t.getName() + " starting");
        letters.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        letters.stop();
        System.out.println("\nProgram completed.");
    }
}

class Letters extends Thread implements Iterable<Thread>{
    List<Thread> lett;

    Letters(String letter){
        lett = new ArrayList<>();
        for (int i = 0; i < letter.length(); i++) {
            char tmp = letter.charAt(i);
            lett.add(new Thread(() -> {
                while(this.isAlive()) {
                    System.out.print(tmp);
                    try{
                        sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }, "Thread " + tmp));
        }
    }



    @Override
    public void run(){
        for (int i = 0; i < lett.size(); i++) {
            lett.get(i).start();
        }
        while(true);
    }


    @Override
    public Iterator<Thread> iterator() {
        return this.lett.iterator();
    }
}