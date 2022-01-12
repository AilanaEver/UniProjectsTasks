package Lesson_2.Problem_1;

import java.util.Comparator;

public class MyComp implements Comparator<Integer> {
    public static final int BY_VAL = 0, BY_VAL_REV = 1, BY_NUM_OF_DIVS = 2, BY_SUM_OF_DIGS = 3;

    int way;

    MyComp(int w) {
        way = w;
    }

    public int compare(Integer a, Integer b) {
        switch (way) {
            case BY_VAL:
                return a - b;
            case BY_VAL_REV:
                return b - a;
            case BY_NUM_OF_DIVS:
                int counta = 0;
                for (int i=1; i <= a ; i++) {
                    if (a % i == 0) ++counta;
                }
                int countb = 0;
                for (int i=1; i <= b ; i++) {
                    if (b % i == 0) ++countb;
                }
                if (counta != countb) return counta - countb;
                else return a - b;
            case BY_SUM_OF_DIGS:

                for (int i=1; i <= a ; i++) {
                    if (a % i == 0)  ++a;
                }
                for (int i=1; i <= b ; i++) {
                    if (b % i == 0) ++b;
                }
                if (a != b) return a+b;

            default:
                throw new IllegalArgumentException();
        }
    }
}