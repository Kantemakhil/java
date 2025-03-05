package net.syscon.s4.report;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EliteReport implements Serializable {

    private Date createDatetime;
    private String createUserId;
    private Date modifyDatetime;
    private String modifyUserId;
    private Long reportId;
    private String moduleName;
    private byte[] reportBody;
    private String reportFileName;
    private Long reportVersion;
    private String description;

    public Long getReportId() {
        return reportId;
    }
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public byte[] getReportBody() {
        return reportBody;
    }
    public void setReportBody(byte[] reportBody) {
        this.reportBody = reportBody;
    }
    public String getReportFileName() {
        return reportFileName;
    }
    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }
    public Long getReportVersion() {
        return reportVersion;
    }
    public void setReportVersion(Long reportVersion) {
        this.reportVersion = reportVersion;
    }
    @Override
    public int hashCode() {
        return Objects.hash(moduleName);
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EliteReport other = (EliteReport) obj;
        return Objects.equals(moduleName, other.moduleName);
    }
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
