package co.simplon.objects.operations;

import co.simplon.objects.entities.Bank;
import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.Account;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Reader.read;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(Account account, Card card, Bank bank){
        while(count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, account)) {
                    if(!account.isActivated()){
                        bank.activateAccount(account);
                        unlockCardMsg();
                    }
                    verified = true;
                    count = 0;
                    displayBalanceMsg(account.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(account);
                }
            } else {
                displayBalanceMsg(account.getBalance());
                return;
            }
        }
    }
}