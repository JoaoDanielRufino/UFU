package adapter;

import java.util.Calendar;

public class BalanceCalculatorA implements BalanceCalculator {

    @Override
    public double calculateBalance(Integer account, Calendar date) {
        System.out.println("Metodo legado A.");
        return 12.0;
    }
    
}
