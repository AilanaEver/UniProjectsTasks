package zad_6;

import java.util.Iterator;
import java.util.NoSuchElementException;



class Roll implements Iterable<Integer>, Iterator<Integer> {
    private int a, b;
    private int last1, last2, last3;

    Roll(int a, int b, int last1, int last2, int last3){
        this.a=a;
        this.b=b;
        this.last1=last1;
        this.last2=last2;
        this.last3=last3;
    }

    Roll(){
        a = 6;
        b= 1;

    }
    public boolean hasNext(){
        return  (last1 + last2 + last3) != 11;
    }

    public Iterator<Integer> iterator() {
        return this;
    }

    public Integer next() {
        if(hasNext()){
            int val = (int) ((Math.random()*a)+1);
            if (last1==0 || last2==0 || last3==0){
                last1 = val;
                last2 = val;
                last3 = val;
            }else{
                last1 = last2;
                last2 = last3;
                last3 = val;
            }
            return val;
        }else{
            throw new NoSuchElementException();
        }
    }

}

class RollDice {
    public static void main(String[] args) {
        for(int turn =0;turn<10;++turn){
            for(Integer i :new Roll())
                System.out.print(i+" ");
            System.out.println();
        }
    }
}