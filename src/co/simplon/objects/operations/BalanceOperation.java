package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.Account;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.displayBalanceMsg;
import static co.simplon.objects.utils.Printer.pincodeMessage;
import static co.simplon.objects.utils.Reader.read;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(Account account, Card card){
        while(count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, account)) {
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