package Project_02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

enum TransType {
    INIT_DEPOS {
        @Override
        public String toString() {
            return "Init depos ";
        }
    },
    DEPOSIT {
        @Override
        public String toString() {
            return "Deposit ";
        }
    },
    WITHDRAWAL {
        @Override
        public String toString() {
            return "Withdrawal ";
        }
    },
    TRANS_FROM {
        @Override
        public String toString () {
            return "Trans. from";
        }
    },

    TRANS_TO{
        @Override
        public String toString () {
            return "Trans. to ";
        }
    }
}

    class Person {
    private String firstName, lastName;

    Person(String fn, String ln) {firstName = fn; lastName=ln;}

    @Override
    public String toString(){ return  firstName + " " + lastName; }
}

    class Account {
    private String id;
    private Person owner;
    private int balance;
    private List<Transaction> list;

    Account(String id, Person owner, int balance){
             this.id=id;
             this.owner=owner;
             this.balance=balance;
             list = new ArrayList<>();
             list.add(new Transaction("+" + balance + " : " + TransType.INIT_DEPOS.ordinal() +"\n"));
         }

          void transTo(Account a, int amount){
             long trans_id = Transaction.getNextId();
             String inList_my = -amount + " : " + TransType.TRANS_FROM.ordinal() + "this account to " + a.getPerson() + " (" +  a.getId() + ")\n";
             String inList_foreign = "+" + amount + " : " + TransType.TRANS_TO.ordinal() + "this account from " + getPerson() + " (" +  getId() + ")\n";
             list.add(new Transaction(inList_my, trans_id));
             a.list.add(new Transaction(inList_foreign, trans_id));
             a.balance+=amount;
             this.balance-=amount;
         }

          void incDec(int amount){
             balance+=amount;
             if(amount>0)
                 list.add(new Transaction("+" + amount + " : " + TransType.DEPOSIT.ordinal() + "\n"));
             if(amount<0)list.
                     add(new Transaction(amount + " : " + TransType.WITHDRAWAL.ordinal() + "\n"));

         }

          Person getPerson(){
             return owner;
         }

          String getId() {
             return id;
         }

          int getBalance() {
             return balance;
         }

         @Override
         public String toString(){
             StringBuilder out = new StringBuilder("*** Acc " + id + "(" + owner + ") " + ". Balance: "+ balance + ". Transactions:  \n");
             for (Transaction t: list
             ) {
                 out.append(String.format( t.toString(), "%-10d"));
             }
             return out.toString();
         }

     }
    class Transaction{
    private long id;
    private String details;
    private static long counter;

    Transaction(String details){
        id = counter;
        counter++;
        this.details = details;
    }

    Transaction(String details, long id){
        this.id = id;
        this.details = details;

    }

     static long getNextId(){
        counter++;
        long ret = counter;
        counter++;
        return ret;
    }

    @Override
    public String toString(){
        return "trans_num " + id + " | "+ details;
    }
}

public class Bank {
    public static void main (String[] args) {
        String fIn = "Bank.dat";
        String fErr = "Bank.err";
        Map<String,Account> accs = readData(fIn,fErr);
        for (Map.Entry<String,Account> e : accs.entrySet())
            System.out.print(e.getValue());
        try {
            String errLog = new String(
                    Files.readAllBytes(Paths.get(fErr)), UTF_8);
            System.out.println("\nContent of " +
                    "\"Bank.err\" follows:\n");
            System.out.println(errLog);
        } catch(IOException e) {
            System.out.println("Problems with error log");
            return;
        }
    }

    public static Map<String, Account> readData(String a, String b){

        FileWriter fw = null;
        BufferedReader br;
        String currentLine;
        Map<String ,Account> ac = new HashMap<>();
        try{
            br = new BufferedReader(new FileReader(a));
            File error_f = new File(b);
            error_f.createNewFile();
            fw = new FileWriter(b, true);

            Pattern p1 = Pattern.compile("^[A-Z][a-z]{1,20}([A-Z])\\s[A-Z][a-z]{1,20}\\s\\1[a-z]{1,20}\\s[1-9]\\d*$");
            Pattern p2_1 = Pattern.compile("^[A-Z][a-z]{1,20}[A-Z]\\s-[1-9]\\d*$");
            Pattern p2_2 = Pattern.compile("^[A-Z][a-z]{1,20}[A-Z]\\s[1-9]\\d*$");
            Pattern p3 = Pattern.compile("[A-Z][a-z]{1,20}[A-Z]\\s[A-Z][a-z]{1,20}[A-Z]\\s\\d*$");

            int count = 1;
            while((currentLine = br.readLine()) != null){
                Matcher m1 = p1.matcher(currentLine);
                Matcher m2_1 = p2_1.matcher(currentLine);
                Matcher m2_2 = p2_2.matcher(currentLine);
                Matcher m3 = p3.matcher(currentLine);

                String[] arr_tmp = currentLine.split(" ");


                if(arr_tmp.length==4 && m1.matches()) {
                    if (ac.containsKey(arr_tmp[0])) {
                        String error1 ="Line \t" +  count + currentLine + "\n \tError: Unique ID error\n";
                        fw.append(error1);
                    } else {
                        ac.put(arr_tmp[0], new Account(arr_tmp[0], new Person(arr_tmp[1], arr_tmp[2]), Integer.parseInt(arr_tmp[3])));
                    }
                }
                else if(arr_tmp.length==2 && m2_1.matches()){
                    if(ac.get(arr_tmp[0]).getBalance()<Math.abs(Integer.parseInt(arr_tmp[1]))){
                        String error2 = "Line \t" + count + " : " + currentLine + "\n \tError: Wrong amount\n";
                        fw.append(error2);
                    } else{
                        int amount = Integer.parseInt(arr_tmp[1]);
                        ac.get(arr_tmp[0]).incDec(amount);
                    }
                }
                else if(arr_tmp.length==2 && m2_2.matches()){

                    int amount = Integer.parseInt(arr_tmp[1]);
                    ac.get(arr_tmp[0]).incDec(amount);
                }
                else if(arr_tmp.length==3 && m3.matches()){
                    if (!ac.containsKey(arr_tmp[0]) || !ac.containsKey(arr_tmp[1])){
                        String error3 = "Line \t" + count + " : " + currentLine + "\n \tError: Account does not exist\n";
                        fw.append(error3);


                    }else {
                        if ((ac.get(arr_tmp[0]).getBalance()<Integer.parseInt(arr_tmp[2]))){
                            String error3 = "Line \t" + count + " : " + currentLine + "\n \tError: Insufficient funds\n";
                            fw.append(error3);
                        } else
                            ac.get(arr_tmp[0]).transTo(ac.get(arr_tmp[1]), Integer.parseInt(arr_tmp[2]));
                    }
                }
                else{
                    if (ac.containsKey(arr_tmp[0])) {
                        System.out.println();
                        String error1 = "Line \t" + count + " : " + currentLine + "\n \tError: Unique ID error\n";
                        fw.append(error1);
                    } else {
                        String error_final = "Line \t" + count  + " : " + currentLine + "\n \tError: Entry inappropriate format \n";
                        fw.append(error_final);
                    }
                }
                count++;
            }
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ac;

    }

}

