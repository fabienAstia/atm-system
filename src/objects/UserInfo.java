
package objects;

public class UserInfo {
    private String pinCode;
    private Integer balance;
    private Boolean isActivated;

    public UserInfo(String pinCode, Integer balance, Boolean isActivated) {
        this.pinCode = pinCode;
        this.balance = balance;
        this.isActivated = isActivated;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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
        return "UserInfo{" +
                "pinCode=[PROTECTED]" + '\'' +
                ", balance=[PROTECTED]" +
                ", isActivated=[PROTECTED]" +
                '}';
    }
}
