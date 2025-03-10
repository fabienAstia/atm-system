package co.simplon.objects;

import static co.simplon.objects.operations.UtilsOperation.count;

public class MessagePrinter {

    public static void insertCardMsg(){
        System.out.println("Veuillez insérer votre carte (Taper 'X')");
    }

    public static void balanceMsg(Integer balance){
        System.out.println("Le solde de votre compte est : " + balance);
    }

    public static void chooseAmountMsg(){
        System.out.println("Combien souhaitez-vous retirer ? : ('X' pour quitter)");
    }

    public static void successWithdrawMsg(){
        System.out.println("Veuillez récupérer votre argent");
    }

    public static void displayAmounts() {
        System.out.println("Taper 1 - 20€");
        System.out.println("Taper 2 - 30€");
        System.out.println("Taper 3 - 40€");
        System.out.println("Taper 4 - 50€");
        System.out.println("Taper 5 - 100€");
        System.out.println("Taper 6 - autre montant");
        System.out.println("Taper X - quitter");
    }

    public static void notEnoughUserCashMsg() {
        System.out.println("Votre solde est insuffisant");
    }

    public static void notEnoughAtmCashMsg() {
        System.out.println("L'ATM ne dispose pas des fonds suffisant. Veuillez repasser ultérieurement");
    }

    public static void displayOperations() {
        System.out.println("Veuillez choisir une opération : ");
        System.out.println("Taper 1 - Consulter le solde de votre compte");
        System.out.println("Taper 2 - Effectuer un retrait");
        System.out.println("Taper X - Quitter le programme");
    }

    public static void quitMsg(){
        System.out.println("bye bye");
    }

    public static void unlockCardMsg(){
        System.out.println("Votre carte est activée.");
    }

    public static void displayMessage() {
        switch (count){
            case 0:
                System.out.println("(" + (count + 1) + "/" + "3" + ") "
                        + "Entrez votre code PIN :");
                break;

            case 1, 2:
                System.out.println("(" + (count + 1) + "/" + "3" + ") "
                        + "Erreur, veuillez saisir votre code PIN");
                break;

            case 3:
                System.out.println("Votre carte est avalée. Veuillez contacter votre agence bancaire.");
                break;
        }
    }

    public static void isTenMultipleMsg(){
        System.out.println("Vous devez renseigner un multiple de 10");
    }

    public static void maxAmountToWithdrawMsg(){
        System.out.println("Vous ne pouvez retirer plus de 500€");
    }

    public static void minAmountToWidthdrawMsg(){
        System.out.println("Vous devez saisir un montant minimum de 10€.");
    }

    public static void invalidAmountMsg(){
        System.out.println("Ce montant n'est pas disponible");
    }
}
