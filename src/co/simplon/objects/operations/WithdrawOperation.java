package co.simplon.objects.operations;

import co.simplon.objects.entities.Card;
import co.simplon.objects.entities.UserInfo;

import java.util.Objects;

import static co.simplon.objects.entities.AtmService.count;
import static co.simplon.objects.entities.AtmService.verified;
import static co.simplon.objects.utils.Printer.*;
import static co.simplon.objects.utils.Converter.onlyDigits;
import static co.simplon.objects.utils.Converter.toInt;
import static co.simplon.objects.utils.Reader.read;

public class WithdrawOperation {

    private static final int AVAILABLE_CASH = 10000;

    public WithdrawOperation() {}

    public void doWithdraw(UserInfo userInfo, Card card) {
        while (count < 3) {
            if (!verified) {
                pincodeMessage();
                String input = read();
                if (card.verifyPinCode(input, userInfo)) {
                    verified = true;
                    count = 0;
                    operation(userInfo);
                    return;
                }
                count++;
                if (count == 3) {
                    pincodeMessage();
                    card.lockCard(userInfo);
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