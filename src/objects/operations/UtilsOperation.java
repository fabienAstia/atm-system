package objects.operations;

import objects.Card;
import objects.UserInfo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static objects.MessagePrinter.unlockCardMsg;
import static objects.operations.Parser.activated;

public final class UtilsOperation {

    public static Integer count = 0;
    public static String choice;
    public static boolean verified = false;

    public static boolean verifyPinCode(String input, Card card, UserInfo userInfo){
        if(hash(input).equals(card.getHashPinCode())){
            if(!activated) {
                unlockCard(card, userInfo);
            }
            return verified = true;
        }
        return false;
    }

    private static void unlockCard(Card card, UserInfo userInfo) {
        if(!card.isUnlocked()){
            card.setUnlocked(true);
            userInfo.setIsActivated(true);
            unlockCardMsg();
        }
    }

    public static void lockCard(Card card, UserInfo userInfo) {
        card.setUnlocked(false);
        userInfo.setIsActivated(false);
    }

    public static String hash(String pinCode) {
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
}