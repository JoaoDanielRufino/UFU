package adapter;

import java.util.Calendar;

public class BalanceCalculatorAdapter implements BalanceCalculator {
    private BalanceCalculatorB b;
    
    public BalanceCalculatorAdapter(BalanceCalculatorB b){
        this.b = b;
    }
    
    @Override
    public double calculateBalance(Integer account, Calendar date) {
        return this.b.balanceFor(new Account(account), date.getTime());
    }
    
}
