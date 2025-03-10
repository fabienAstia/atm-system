package objects.operations;

import objects.Card;
import objects.UserInfo;

import java.util.Objects;
import java.util.Scanner;

import static objects.operations.UtilsOperation.*;
import static objects.operations.UtilsOperation.count;

public class WithdrawOperation {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static final int AVAILABLE_CASH = 10000;
    private String amountChoice;
    private String customAmount;

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
        chooseAmount();
        if (amountChoice.equals("6")) {
            do {
                System.out.println("Combien souhaitez-vous retirer ? : ('X' pour quitter)");
                read();
            } while(isInvalid(userInfo));
        } else if (onlyDigits(amountChoice) && toInt(amountChoice) >= 1 && toInt(amountChoice) <= 5){
            customAmount = String.valueOf(Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue());
            withdrawIfPossible(userInfo);
        }
    }

    private boolean isInvalid(UserInfo userInfo) {
        return !customAmount.equals("X") && (!onlyDigits(customAmount) || !withdrawIfPossible(userInfo));
    }

    private Integer toInt(String string){
        return Integer.parseInt(string);
    }

    private void read(){
        customAmount = SCANNER.nextLine();
    }

    private boolean onlyDigits(String input){
        return  input.matches("[0-9]+");
    }

    private boolean withdrawIfPossible(UserInfo userInfo) {
        if (checkUserHasMoney(userInfo, toInt(customAmount))
                && checkAtmHasMoney(toInt(customAmount))
                && checkValidAmount(toInt(customAmount))) {
            System.out.println("Veuillez récupérer votre argent");
            userInfo.setBalance(userInfo.getBalance() - toInt(customAmount));
            return true;
        }
        return false;
    }

    private void displayAmounts() {
        System.out.println("Taper 1 - 20€");
        System.out.println("Taper 2 - 30€");
        System.out.println("Taper 3 - 40€");
        System.out.println("Taper 4 - 50€");
        System.out.println("Taper 5 - 100€");
        System.out.println("Taper 6 - autre montant");
        System.out.println("Taper X - quitter");
    }

    public void chooseAmount() {
        displayAmounts();
        amountChoice = SCANNER.nextLine();
    }

    private boolean checkUserHasMoney(UserInfo userInfo, int amount) {
        if (userInfo.getBalance() < amount) {
            System.out.println("Votre solde est insuffisant");
            return false;
        }
        return true;
    }

    private boolean checkAtmHasMoney(int amount) {
        if (AVAILABLE_CASH < amount) {
            System.out.println("L'ATM ne dispose pas des fonds suffisant. Veuillez repasser ultérieurement");
            return false;
        }
        return true;
    }

    private boolean checkValidAmount(int amount) {
        if (amount % 10 != 0) {
            System.out.println("Vous devez renseigner un multiple de 10");
            return false;
        }
        if (amount > 500) {
            System.out.println("Vous ne pouvez retirer plus de 500€");
            return false;
        }
        if (amount == 0) {
            System.out.println("Vous devez saisir un montant minimum de 10€.");
            return false;
        }
        return true;
    }
}