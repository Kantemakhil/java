package net.syscon.s4.sa.audit.maintenance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AllAuditPolicies
 */
public class AllAuditPolicies extends BaseModel implements Serializable {

	@JsonProperty("objectSchema")
	private String objectSchema;
	@JsonProperty("objectName")
	private String objectName;
	@JsonProperty("policyOwner")
	private String policyOwner;
	@JsonProperty("policyName")
	private String policyName;
	@JsonProperty("policyText")
	private String policyText;
	@JsonProperty("policyColumn")
	private String policyColumn;
	@JsonProperty("pfSchema")
	private String pfSchema;
	@JsonProperty("pfPackage")
	private String pfPackage;
	@JsonProperty("pfFunction")
	private String pfFunction;
	@JsonProperty("enabled")
	private String enabled;
	@JsonProperty("enableOrDisable")
	private int enableOrDisable;
	@JsonProperty("sel")
	private String sel;
	@JsonProperty("ins")
	private String ins;
	@JsonProperty("upd")
	private String upd;
	@JsonProperty("del")
	private String del;
	@JsonProperty("auditTrail")
	private String auditTrail;
	@JsonProperty("policyColumnOptions")
	private String policyColumnOptions;
	@JsonProperty("common")
	private String common;
	@JsonProperty("inherited")
	private String inherited;
	private boolean inserted;
	@JsonProperty("sealFlag")
	private String sealFlag;

	/**
	 * @param objectSchema
	 *            objectSchema to set
	 */
	public void setObjectSchema(String objectSchema) {
		this.objectSchema = objectSchema;
	}

	/**
	 * return theobjectSchema
	 */
	public String getObjectSchema() {
		return this.objectSchema;
	}

	/**
	 * @param objectName
	 *            objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * return theobjectName
	 */
	public String getObjectName() {
		return this.objectName;
	}

	/**
	 * @param policyOwner
	 *            policyOwner to set
	 */
	public void setPolicyOwner(String policyOwner) {
		this.policyOwner = policyOwner;
	}

	/**
	 * return thepolicyOwner
	 */
	public String getPolicyOwner() {
		return this.policyOwner;
	}

	/**
	 * @param policyName
	 *            policyName to set
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	/**
	 * return thepolicyName
	 */
	public String getPolicyName() {
		return this.policyName;
	}

	/**
	 * @param policyText
	 *            policyText to set
	 */
	public void setPolicyText(String policyText) {
		this.policyText = policyText;
	}

	/**
	 * return thepolicyText
	 */
	public String getPolicyText() {
		return this.policyText;
	}

	/**
	 * @param policyColumn
	 *            policyColumn to set
	 */
	public void setPolicyColumn(String policyColumn) {
		this.policyColumn = policyColumn;
	}

	/**
	 * return thepolicyColumn
	 */
	public String getPolicyColumn() {
		return this.policyColumn;
	}

	/**
	 * @param pfSchema
	 *            pfSchema to set
	 */
	public void setPfSchema(String pfSchema) {
		this.pfSchema = pfSchema;
	}

	/**
	 * return thepfSchema
	 */
	public String getPfSchema() {
		return this.pfSchema;
	}

	/**
	 * @param pfPackage
	 *            pfPackage to set
	 */
	public void setPfPackage(String pfPackage) {
		this.pfPackage = pfPackage;
	}

	/**
	 * return thepfPackage
	 */
	public String getPfPackage() {
		return this.pfPackage;
	}

	/**
	 * @param pfFunction
	 *            pfFunction to set
	 */
	public void setPfFunction(String pfFunction) {
		this.pfFunction = pfFunction;
	}

	/**
	 * return thepfFunction
	 */
	public String getPfFunction() {
		return this.pfFunction;
	}

	/**
	 * @param enabled
	 *            enabled to set
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * return theenabled
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * @param sel
	 *            sel to set
	 */
	public void setSel(String sel) {
		this.sel = sel;
	}

	/**
	 * return thesel
	 */
	public String getSel() {
		return this.sel;
	}

	/**
	 * @param ins
	 *            ins to set
	 */
	public void setIns(String ins) {
		this.ins = ins;
	}

	/**
	 * return theins
	 */
	public String getIns() {
		return this.ins;
	}

	/**
	 * @param upd
	 *            upd to set
	 */
	public void setUpd(String upd) {
		this.upd = upd;
	}

	/**
	 * return theupd
	 */
	public String getUpd() {
		return this.upd;
	}

	/**
	 * @param del
	 *            del to set
	 */
	public void setDel(String del) {
		this.del = del;
	}

	/**
	 * return thedel
	 */
	public String getDel() {
		return this.del;
	}

	/**
	 * @param auditTrail
	 *            auditTrail to set
	 */
	public void setAuditTrail(String auditTrail) {
		this.auditTrail = auditTrail;
	}

	/**
	 * return theauditTrail
	 */
	public String getAuditTrail() {
		return this.auditTrail;
	}

	/**
	 * @param policyColumnOptions
	 *            policyColumnOptions to set
	 */
	public void setPolicyColumnOptions(String policyColumnOptions) {
		this.policyColumnOptions = policyColumnOptions;
	}

	/**
	 * return thepolicyColumnOptions
	 */
	public String getPolicyColumnOptions() {
		return this.policyColumnOptions;
	}

	/**
	 * @param common
	 *            common to set
	 */
	public void setCommon(String common) {
		this.common = common;
	}

	/**
	 * return thecommon
	 */
	public String getCommon() {
		return this.common;
	}

	/**
	 * @param inherited
	 *            inherited to set
	 */
	public void setInherited(String inherited) {
		this.inherited = inherited;
	}

	/**
	 * return theinherited
	 */
	public String getInherited() {
		return this.inherited;
	}

	/**
	 * @return the inserted
	 */
	public boolean isInserted() {
		return inserted;
	}

	/**
	 * @param inserted
	 *            the inserted to set
	 */
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	
	public String getSealFlag() {
		return sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	public int getEnableOrDisable() {
		return enableOrDisable;
	}

	public void setEnableOrDisable(int enableOrDisable) {
		this.enableOrDisable = enableOrDisable;
	}
}