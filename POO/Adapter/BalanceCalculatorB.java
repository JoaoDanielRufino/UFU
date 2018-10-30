/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.util.Date;

/**
 *
 * @author a11621BCC033
 */
public class BalanceCalculatorB implements BalanceResolver {

    @Override
    public double balanceFor(Account account, Date date) {
        System.out.println("Metodo legado B.");
        return 15.0;
    }
    
}
