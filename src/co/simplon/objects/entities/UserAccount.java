
package co.simplon.objects.entities;

public class UserAccount {
    private final Integer accountId;
    private final String pincode;
    private Integer balance;
    private Boolean isActivated;

    public UserAccount(int accountId, Integer balance, String pincode, Boolean isActivated) {
        this.accountId = accountId;
        this.pincode = pincode;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "UserAccount{" +
                "pincode=[PROTECTED]" + '\'' +
                ", balance=[PROTECTED]" +
                ", isActivated=[PROTECTED]" +
                '}';
    }
}
