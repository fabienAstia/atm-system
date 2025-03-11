
package co.simplon.objects.entities;

public class UserAccount {
    private final Integer accountId;
    private final String bban;
    private final String pincode;
    private Integer balance;
    private Boolean isActivated;

    public UserAccount(int accountId, String bban, Integer balance, String pincode, Boolean isActivated) {
        this.accountId = accountId;
        this.bban = bban;
        this.balance = balance;
        this.pincode = pincode;
        this.isActivated = isActivated;
    }

    public int getAccountId() {
        return accountId;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public String getPincode() {
        return pincode;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean isActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getBban() {
        return bban;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "accountId=" + accountId +
                ", bban='" + bban +
                ", pincode=[PROTECTED]" +
                ", balance=[PROTECTED]" +
                ", isActivated=" + isActivated +
                '}';
    }
}
