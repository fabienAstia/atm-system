package co.simplon.objects.operations;

import co.simplon.objects.Card;
import co.simplon.objects.UserInfo;

import java.util.Objects;
import java.util.Scanner;

import static co.simplon.objects.MessagePrinter.*;
import static co.simplon.objects.operations.Converter.onlyDigits;
import static co.simplon.objects.operations.Converter.toInt;
import static co.simplon.objects.operations.InputReader.read;
import static co.simplon.objects.operations.UtilsOperation.*;
import static co.simplon.objects.operations.UtilsOperation.count;

public class WithdrawOperation {

    private static final int AVAILABLE_CASH = 10000;

    public WithdrawOperation() {}

    public void doWithdraw(Scanner scanner, UserInfo userInfo, Card card) {
        while (count < 3) {
            if (!verified) {
                displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    operation(userInfo);
                    return;
                }
                count++;
                if (count == 3) {
                    displayMessage();
                    lockCard(card, userInfo);
                }
            } else {
                operation(userInfo);
                return;
            }
        }
    }

    private void operation(UserInfo userInfo) {
        String amountChoice = chooseAmount();
        String customAmount;
        if (amountChoice.equals("6")) {
            do {
                chooseAmountMsg();
                customAmount = read();
            } while(isInvalid(userInfo, customAmount));
        } else if (onlyDigits(amountChoice) && toInt(amountChoice) >= 1 && toInt(amountChoice) <= 5){
            customAmount = String.valueOf(Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue());
            withdrawIfPossible(userInfo, customAmount);
        }
    }

    private boolean isInvalid(UserInfo userInfo, String customAmount) {
        return !customAmount.equals("X")
                && (!onlyDigits(customAmount) || !withdrawIfPossible(userInfo, customAmount));
    }

    private boolean withdrawIfPossible(UserInfo userInfo, String customAmount) {
        if (checkUserHasMoney(userInfo, toInt(customAmount))
                && checkAtmHasMoney(toInt(customAmount))
                && checkValidAmount(toInt(customAmount))) {
            successWithdrawMsg();
            userInfo.setBalance(userInfo.getBalance() - toInt(customAmount));
            return true;
        }
        return false;
    }

    public String chooseAmount() {
        displayAmounts();
        return read();
    }

    private boolean checkUserHasMoney(UserInfo userInfo, int amount) {
        if (userInfo.getBalance() < amount) {
            notEnoughUserCashMsg();
            return false;
        }
        return true;
    }

    private boolean checkAtmHasMoney(int amount) {
        if (AVAILABLE_CASH < amount) {
            notEnoughAtmCashMsg();
            return false;
        }
        return true;
    }

    private boolean checkValidAmount(int amount) {
        if (amount % 10 != 0) {
            isTenMultipleMsg();
            return false;
        }
        if (amount > 500) {
            maxAmountToWithdrawMsg();
            return false;
        }
        if (amount == 0) {
            minAmountToWidthdrawMsg();
            return false;
        }
        return true;
    }
}