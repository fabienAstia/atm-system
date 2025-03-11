package co.simplon.objects.entities;

import co.simplon.objects.utils.Printer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static co.simplon.objects.utils.Builder.activated;

public class Card {

    private final String bban;
    private final String hashPinCode;
    private boolean unlocked;

    public Card(Account account) {
        this.bban = account.getBban();
        this.hashPinCode = hash(account.getPincode());
        this.unlocked = account.isActivated();
    }

    public boolean verifyPinCode(String input, Account account){
        if(hash(input).equals(this.getHashPinCode())){
            if(!activated) {
                unlockCard(account);
            }
            return true;
        }
        return false;
    }

    private void unlockCard(Account account) {
        this.unlocked = true;
    }

    public void lockCard(Account account) {
        this.unlocked = false;
        account.setIsActivated(false);
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

    @Override
    public String toString() {
        return "Card{" +
                "bban=[PROTECTED]" +
                ", hashPinCode=[PROTECTED]" +
                ", unlocked=" + unlocked +
                '}';
    }
}