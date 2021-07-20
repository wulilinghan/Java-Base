package thread.threadlocaltest;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserInfo {
    private Integer userId;
    private Integer roleId;
    private String accountName;

    public UserInfo(Integer userId, Integer roleId, String accountName) {
        this.userId = userId;
        this.roleId = roleId;
        this.accountName = accountName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("roleId", roleId)
                .append("accountName", accountName)
                .toString();
    }
}
