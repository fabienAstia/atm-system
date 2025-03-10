package co.simplon.objects.entities;

import co.simplon.objects.utils.Parser;
import co.simplon.objects.utils.Printer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Card {

    private final String hashPinCode;
    private boolean unlocked;

    public Card(UserInfo userInfo) {
        this.hashPinCode = hash(userInfo.getPincode());
        this.unlocked = userInfo.isActivated();
    }

    public boolean verifyPinCode(String input, UserInfo userInfo){
        if(hash(input).equals(this.getHashPinCode())){
            if(!Parser.activated) {
                unlockCard(userInfo);
            }
            return true;
        }
        return false;
    }

    private void unlockCard(UserInfo userInfo) {
        this.unlocked = true;
        userInfo.setIsActivated(true);
        Printer.unlockCardMsg();
    }

    public void lockCard(UserInfo userInfo) {
        this.unlocked = false;
        userInfo.setIsActivated(false);
    }

    public String hash(String pinCode) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        final byte[] hashBytes = digest.digest(pinCode.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
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