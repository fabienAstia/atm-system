package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.UserInfo;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.displayBalanceMsg;
import static co.simplon.objects.utils.Printer.pincodeMessage;
import static co.simplon.objects.utils.Reader.read;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(UserInfo userInfo, Card card){
        while(count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, userInfo)) {
                    verified = true;
                    count = 0;
                    displayBalanceMsg(userInfo.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(userInfo);
                }
            } else {
                displayBalanceMsg(userInfo.getBalance());
                return;
            }
        }
    }
}