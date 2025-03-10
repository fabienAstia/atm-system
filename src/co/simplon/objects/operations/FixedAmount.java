package co.simplon.objects.operations;

import co.simplon.objects.MessagePrinter;

public enum FixedAmount {
    VINGT_EUROS("1", 20),
    TRENTE_EUROS("2", 30),
    QUARANTE_EUROS("3", 40),
    CINQUANTE_EUROS("4", 50),
    CENT_EUROS("5", 100);

    private final String choice;
    private final Integer value;

    FixedAmount(String choice, Integer value) {
        this.choice = choice;
        this.value = value;
    }

    public String getChoice() {
        return choice;
    }

    public Integer getValue() {
        return value;
    }

    public static FixedAmount fromChoice(String choice) {
        for (FixedAmount a : FixedAmount.values()) {
            if (choice.equals(a.getChoice())) {
                return a;
            }
        }
        MessagePrinter.invalidAmountMsg();
        return null;
    }
}