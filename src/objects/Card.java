package objects;

import static objects.operations.UtilsOperation.hash;

public class Card {

    private final String hashPinCode;
    private boolean unlocked = false;

    public Card(UserInfo userInfo) {
        this.hashPinCode = hash(userInfo.getPincode());
        this.unlocked = userInfo.isActivated();
    }

    public String getHashPinCode() {
        return hashPinCode;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    @Override
    public String toString() {
        return "Card{" +
                "hashPinCode= [PROTECTED]" +
                ", unlocked= [PROTECTED]" +
                ", userinfo= [PROTECTED]" +
                '}';
    }
}