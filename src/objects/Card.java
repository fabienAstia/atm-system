package objects;

import static objects.operations.UtilsOperation.hash;

public class Card {

    private String hashPinCode;
    private boolean unlocked = false;

    public boolean isUnlocked() {
        return unlocked;
    }

    public Card(String pinCode, boolean unlocked) {
        this.hashPinCode = hash(pinCode);
        this.unlocked = unlocked;
    }

    public Card(String pinCode) {
        this.hashPinCode = hash(pinCode);
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
}