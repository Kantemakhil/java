package net.syscon.s4.sa.admin.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the ACCESSIBLE_FORM_TABLES database table.
 * 
 */
public class AccessibleFormTables implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("originatingForm")
	private String originatingForm;
	@JsonProperty("destinationForm")
	private String destinationForm;

	private String tableName;
	
	private Date createDatetime;

	private String createUserId;

	private Date modifyDatetime;

	private String modifyUserId;

	private String sealFlag;

	private BigDecimal listSeq;
	public AccessibleFormTables() {
	}



	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}
	
	public void setCreateDatetime(final Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	
	public Date getModifyDatetime() {
		return modifyDatetime;
	}
	
	public void setModifyDatetime(final Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(final String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(final String sealFlag) {
		this.sealFlag = sealFlag;
	}
	
	public String getOriginatingForm() {
		return this.originatingForm;
	}
	public void setOriginatingForm(final String originatingForm) {
		this.originatingForm = originatingForm;
	}
	public String getDestinationForm() {
		return this.destinationForm;
	}
	public void setDestinationForm(final String destinationForm) {
		this.destinationForm = destinationForm;
	}
	public String getTableName() {
		return this.tableName;
	}
	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}

	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		/*if (!(other instanceof AccessibleFormTablePK)) {
			return false;
		}
		AccessibleFormTablePK castOther = (AccessibleFormTablePK)other;
		return 
			this.originatingForm.equals(castOther.originatingForm)
			&& this.destinationForm.equals(castOther.destinationForm)
			&& this.tableName.equals(castOther.tableName);*/
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.originatingForm.hashCode();
		hash = hash * prime + this.destinationForm.hashCode();
		hash = hash * prime + this.tableName.hashCode();
		
		return hash;
	}



	public BigDecimal getListSeq() {
		return listSeq;
	}



	public void setListSeq(final BigDecimal listSeq) {
		this.listSeq = listSeq;
	}

}
