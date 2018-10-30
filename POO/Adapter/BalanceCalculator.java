package adapter;

import java.util.Calendar;

public interface BalanceCalculator {
    public double calculateBalance(Integer account, Calendar date);
}
