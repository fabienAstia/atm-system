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

    public Card(UserAccount userAccount) {
        this.bban = userAccount.getBban();
        this.hashPinCode = hash(userAccount.getPincode());
        this.unlocked = userAccount.isActivated();
    }

    public boolean verifyPinCode(String input, UserAccount userAccount){
        if(hash(input).equals(this.getHashPinCode())){
            if(!activated) {
                unlockCard(userAccount);
            }
            return true;
        }
        return false;
    }

    private void unlockCard(UserAccount userAccount) {
        this.unlocked = true;
        userAccount.setIsActivated(true);
        Printer.unlockCardMsg();
    }

    public void lockCard(UserAccount userAccount) {
        this.unlocked = false;
        userAccount.setIsActivated(false);
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
                "hashPinCode= [PROTECTED]" +
                ", unlocked= [PROTECTED]" +
                ", userinfo= [PROTECTED]" +
                '}';
    }
}