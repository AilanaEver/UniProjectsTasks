package Lesson_1;

public class Dates {
    public static void main(String[] args) {
        int period = pack(2000, 2, 3, 2127, 11, 29);
        showPeriod(period);
    }

    private static int pack(int fromy, int fromm, int fromd, int toy, int tom, int tod) {
        int period = fromy - 2000;
        period= period << 4;
        period = period | fromm;
        period = period << 5;
        period = period | fromd;
        period = period << 7;
        period = period | toy;
        period = period << 4;
        period = period | tom;
        period = period << 5;
        period = period | tod;
        return period;
    }
    private static void showPeriod(int period) {
        int fday = period & 31;
        period = period >> 5;
        int fmonth  = period & 15;
        period = period >> 4;
        int fyear = period & 127 + 2000;
        period = period >> 7;
        int tday = period & 31;
        period = period >> 5;
        int tmonth  = period & 15;
        period = period >> 4;
        int tyear = (period & 127) + 2000;




        System.out.println(fyear + "/" + fmonth + "/" + fday + "-" + tyear + "/" + tmonth + "/" + tday );
    }
}
