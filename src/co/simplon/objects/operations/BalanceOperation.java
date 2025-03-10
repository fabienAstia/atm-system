package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.UserAccount;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.displayBalanceMsg;
import static co.simplon.objects.utils.Printer.pincodeMessage;
import static co.simplon.objects.utils.Reader.read;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(UserAccount userAccount, Card card){
        while(count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, userAccount)) {
                    verified = true;
                    count = 0;
                    displayBalanceMsg(userAccount.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(userAccount);
                }
            } else {
                displayBalanceMsg(userAccount.getBalance());
                return;
            }
        }
    }
}