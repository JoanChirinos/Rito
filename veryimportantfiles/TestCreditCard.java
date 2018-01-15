import jutils.*;

public class TestCreditCard {

     public static void main(String[] args) {
          CreditCard test = new CreditCard(123412341234l,1532l);

          CreditCard test1 = new CreditCard(123431341234l,5555l);

          CreditCard tes = new CreditCard();

          CSVRW check = new CSVRW("Numbers.csv");
          System.out.println(check.size());




     }//end main

}//end class
