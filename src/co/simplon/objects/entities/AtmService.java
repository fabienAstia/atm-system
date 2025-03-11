package co.simplon.objects.entities;

import co.simplon.objects.operations.*;

import java.util.List;

import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Reader.*;

public class AtmService {

    public static boolean verified = false;
    public static Integer count = 0;

    public AtmService() {}

    public void doOperations() {
        Bank bank = new Bank();
        List<Account> accounts = bank.getAccounts();
        Account account = accounts.get(1);
        Card card = new Card(account);

        String input;
        do{
            insertCardMsg();
            input = read();
        } while (!input.equals("X"));
        String choice;
        do {
            choice = chooseOperation();
            if (choice.equals("1")) {
                getBalance(account, card);
            }
            if (choice.equals("2")) {
                withdraw(account, card);
            }
        } while (!choice.equals("X"));
        quitMsg();
        Bank.updateBankAccount(account);
    }

    private static void withdraw(Account account, Card card) {
        WithdrawOperation withdrawOperation = new WithdrawOperation();
        withdrawOperation.doWithdraw(account, card);
    }

    private static void getBalance(Account account, Card card) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.getBalance(account, card);
    }

    public static String chooseOperation(){
        displayOperations();
        return read();
    }

}