package com.cgy.dynamicds.entity;

/**
 * <p>
 * ORG_USER（群组用户表）
 * </p>
 *
 * @author chenguoyou
 * @since 2021-04-13
 */
public class OrgUserEntity {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "ou_id", type = IdType.AUTO)
    private Long ouId;

    /**
     * 群组ID
     */
    private Long orgId;

    /**
     * 群组编码
     */
    private String orgCode;

    private String orgName;

    private String userNameCn;

    private String userName;

    private Long depId;

    private String isUsable;

    private Long comId;


    public Long getOuId() {
        return ouId;
    }

    public void setOuId(Long ouId) {
        this.ouId = ouId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public String getIsUsable() {
        return isUsable;
    }

    public void setIsUsable(String isUsable) {
        this.isUsable = isUsable;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }


    @Override
    public String toString() {
        return "OrgUserEntity{" +
        "ouId=" + ouId +
        ", orgId=" + orgId +
        ", orgCode=" + orgCode +
        ", orgName=" + orgName +
        ", userNameCn=" + userNameCn +
        ", userName=" + userName +
        ", depId=" + depId +
        ", isUsable=" + isUsable +
        ", comId=" + comId +
        "}";
    }
}
