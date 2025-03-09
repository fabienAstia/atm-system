package objects;

import static objects.operations.UtilsOperation.hash;

public class Card {

    private String hashPinCode;
    private boolean unlocked = false;

    public Card(String pinCode) {
        this.hashPinCode = hash(pinCode);
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public String getHashPinCode() {
        return hashPinCode;
    }

    public void setHashPinCode(String pinCode) {
        this.hashPinCode = hash(pinCode);
    }

    @Override
    public String toString() {
        return "Card{" +
                "hashPinCode= [PROTECTED]" + '\'' +
                ", unlocked=" + unlocked +
                '}';
    }
}