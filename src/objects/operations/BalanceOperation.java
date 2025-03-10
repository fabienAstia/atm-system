package objects.operations;

import objects.Card;
import objects.UserInfo;

import java.util.Scanner;

import static objects.MessagePrinter.balanceMsg;
import static objects.MessagePrinter.displayMessage;
import static objects.operations.UtilsOperation.*;

public class BalanceOperation {

    public BalanceOperation() {}

    public void getBalance(Scanner scanner, UserInfo userInfo, Card card){
        while(count < 3) {
            if (!verified) {
                displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    balanceMsg(userInfo.getBalance());
                    return;
                }
                count++;
                if (count == 3) {
                    displayMessage();
                    lockCard(card, userInfo);
                }
            } else {
                balanceMsg(userInfo.getBalance());
                return;
            }
        }
    }
}