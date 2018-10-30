package adapter;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @Joao Daniel Rufino
 * @Pedro Henrique Teixeira
 */
public class Adapter {

   
    public static void main(String[] args) {
        BalanceCalculatorA ba = new BalanceCalculatorA();
        Calendar c = Calendar.getInstance();
        ba.calculateBalance(12, c);
        
        BalanceCalculatorB bb = new BalanceCalculatorB();
        bb.balanceFor(new Account(12), new Date());
        
        BalanceCalculator bc = new BalanceCalculatorAdapter(bb);
        bc.calculateBalance(12, c);
    }
    
}
