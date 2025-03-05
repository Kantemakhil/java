package net.syscon.s4.report;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OmsReportAsset extends BaseModel{
    
    private static final Long serialVersionUID = 1L;

    private Long assetId;
    private String assetCode;
    private byte[] assetBody;
    private String assetsFilename;
    private Date createDatetime;
    private String createUserId;
    private Date modifyDatetime;
    private String modifyUserId;

    public Long getAssetId() {
        return assetId;
    }
    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }
    public String getAssetCode() {
        return assetCode;
    }
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
    public byte[] getAssetBody() {
        return assetBody;
    }
    public void setAssetBody(byte[] assetBody) {
        this.assetBody = assetBody;
    }
    public String getAssetsFilename() {
        return assetsFilename;
    }
    public void setAssetsFilename(String assetsFilename) {
        this.assetsFilename = assetsFilename;
    }
    public Date getCreateDatetime() {
        return createDatetime;
    }
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    public String getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    public Date getModifyDatetime() {
        return modifyDatetime;
    }
    public void setModifyDatetime(Date modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }
    public String getModifyUserId() {
        return modifyUserId;
    }
    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    
    

}
