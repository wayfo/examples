package com.nchu.transaction.seata.twopc.bank2.service;

/**
 * Created by Administrator.
 */
public interface AccountInfoService {

    //李四增加金额
    public void updateAccountBalance(String accountNo, Double amount);
}
