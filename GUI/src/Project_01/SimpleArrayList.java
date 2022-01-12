package Project_01;
/*

import java.util.Arrays;

public class SimpleArrayList {
    private final static int INITIAL_CAPACITY = 5;
    private int cap = INITIAL_CAPACITY;
    private int size = 0;
    private int[] arr = new int[cap];

    public SimpleArrayList() {
        size = 0;
        cap = INITIAL_CAPACITY;
    }

    public SimpleArrayList(int s) {
        size += 1;
        arr[size - 1] = s;
    }

    public SimpleArrayList(int[] a) {
        int sz = arr.length;
        if (sz < cap) {
            for (int i = 0; i < arr.length; i++) {
                arr[sz - 1] = a[i];
            }
        } else {
            cap = 2 * cap;
        }
    }

    public SimpleArrayList(SimpleArrayList any) {
        this.size += any.size;
        this.cap += any.cap;

        int sz = this.arr.length;
        if (sz < this.cap)
            for (int i = 0; i < arr.length; i++) {
                this.arr[sz - 1] = any.arr[i];
            }
        else {
            this.cap = 2 * this.cap;
        }
    }

    public int size() {
        int[] a = new int[arr.length];
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            counter++;
        }
        this.size = counter;
        return this.size;
    }

    public void clear() {
        SimpleArrayList any = new SimpleArrayList();
    }

    public void trim() {
        this.cap = arr.length;
    }

    public SimpleArrayList insert(int ind, int[] a) {
        int sz = a.length;
        if (ind < 0 && ind > size)
            throw new IndexOutOfBoundsException("Error");
        else {
            if (cap < size) {
                System.arraycopy(a, 0, arr, ind, sz - 1);
            } else {
                int[] array = new int[2 * (sz + cap)];
                System.arraycopy(a, 0, array, ind, sz - 1);
                System.arraycopy(arr, 0, array, 0, arr.length - 1);
            }
        }
        return this;
    }

    public SimpleArrayList insert(int ind, int e){
        insert(ind, new int[]{e});
        return this;
    }

    public SimpleArrayList append(int e){
        insert(arr[arr.length-1], new int[]{e} );
        return this;
    }

    public SimpleArrayList append(int[] a){
        insert(0, a);
        return this;
    }

    public SimpleArrayList append(SimpleArrayList a){
        insert(0, a.getArr());
        return this;
    }

    private int[] getArr() {
    }

    public int get(int ind){
        if(ind > arr.length-1 || ind <0){
            throw new IndexOutOfBoundsException("Out of bound!!");
        }
        return arr[ind];
    }

    public SimpleArrayList set(int ind, int val){
        if(ind > arr.length-1 || ind <0){
            throw new IndexOutOfBoundsException("Out of bound!!");
        }
        arr[ind] = val;
        return this;
    }

    public String toString(){
        return "Cap=" + cap + ", size=" + size + ": " + Arrays.toString(arr);
    }


    public static void main(String[] args){
        SimpleArrayList a = new SimpleArrayList().append(new int[]{1,3}).insert(1,2).append(6).insert(3,new int[]{4,5});

        SimpleArrayList b = new SimpleArrayList(a);
        for (int i = 0; i < a.size();++i)
            a.set(i,a.get(i)+6);
        b.append(a).append(13).trim();
        System.out.println("a -> " + a);
        System.out.println("b -> " + b);
    }

}

 */