package adapter;

import java.util.Date;

public interface BalanceResolver {
    
    public double balanceFor(Account account, Date date);
    
}
