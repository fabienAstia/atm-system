package objects.operations;

import objects.Card;
import objects.UserInfo;

import java.util.Objects;
import java.util.Scanner;

import static objects.operations.UtilsOperation.*;
import static objects.operations.UtilsOperation.count;

public class WithdrawOperation {

    private final Scanner scanner = new Scanner(System.in);
    private static final int AVAILABLE_CASH = 10000;
    private String amountChoice;
    private Integer amount = -1;

    public WithdrawOperation() {
    }

    public void doWithdraw(Scanner scanner, UserInfo userInfo, Card card) {
        while (count < 3) {
            if (!verified) {
                displayMessage();
                String input = scanner.nextLine();
                if (verifyPinCode(input, card, userInfo)) {
                    count = 0;
                    operation(scanner, userInfo);
                    return;
                }
                count++;
                if (count == 3) {
                    displayMessage();
                }
            } else {
                operation(scanner, userInfo);
                return;
            }
        }
    }

    private void operation(Scanner scanner, UserInfo userInfo) {
        System.out.println("Combien souhaitez-vous retirer ? :");
        chooseAmount();
        if (Integer.parseInt(amountChoice) >= 1 && Integer.parseInt(amountChoice) <= 5) {
            amount = Objects.requireNonNull(FixedAmount.fromChoice(amountChoice)).getValue();
            withdrawIfPossible(userInfo);
        } else if (amountChoice.equals("6")) {
            System.out.println("Entrez le montant à retirer :");
            amount = Integer.parseInt(scanner.nextLine());
            withdrawIfPossible(userInfo);
        }
    }

    private void withdrawIfPossible(UserInfo userInfo) {
        if (checkUserHasMoney(userInfo, amount) && checkAtmHasMoney(amount) && checkValidAmount(amount)) {
            System.out.println("Veuillez récupérer votre argent");
            userInfo.setBalance(userInfo.getBalance() - amount);
        }
    }

    public void displayAmounts() {
        System.out.println("Taper 1 - 20€");
        System.out.println("Taper 2 - 30€");
        System.out.println("Taper 3 - 40€");
        System.out.println("Taper 4 - 50€");
        System.out.println("Taper 5 - 100€");
        System.out.println("Taper 6 - autre montant");
    }

    public void chooseAmount() {
        displayAmounts();
        amountChoice = scanner.nextLine();
    }

    public boolean checkUserHasMoney(UserInfo userInfo, int amount) {
        if (userInfo.getBalance() < amount) {
            System.out.println("Votre solde est insuffisant");
            return false;
        }
        return true;
    }

    public boolean checkAtmHasMoney(int amount) {
        if (AVAILABLE_CASH < amount) {
            System.out.println("L'ATM ne dispose pas des fonds suffisant. Veuillez repasser ultérieurement");
            return false;
        }
        return true;
    }

    public boolean checkValidAmount(int amount) {
        if (amount % 10 != 0) {
            System.out.println("Vous devez renseigner un multiple de 10");
            return false;
        }
        if (amount > 500) {
            System.out.println("Vous ne pouvez retirer plus de 500€");
            return false;
        }
        return true;
    }
}