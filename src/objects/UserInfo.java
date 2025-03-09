
package objects;

public class UserInfo {
    private String pincode;
    private Integer balance;
    private Boolean isActivated;

    public UserInfo(String pincode, Integer balance, Boolean isActivated) {
        this.pincode = pincode;
        this.balance = balance;
        this.isActivated = isActivated;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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
