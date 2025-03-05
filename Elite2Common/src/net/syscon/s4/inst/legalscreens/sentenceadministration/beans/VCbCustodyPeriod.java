package net.syscon.s4.inst.legalscreens.sentenceadministration.beans;

import java.io.Serializable;
import java.util.Date;

import net.syscon.s4.common.beans.BaseModel;

public class VCbCustodyPeriod extends BaseModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int offenderBookId;
	private int offenderId;
	private int custodyPeriodSeq;
	private Date admissionDate;
	private Date releaseDate;
	private int daysAfterEffDate;
	
	public int getOffenderBookId() {
		return offenderBookId;
	}
	public void setOffenderBookId(int offenderBookId) {
		this.offenderBookId = offenderBookId;
	}
	public int getOffenderId() {
		return offenderId;
	}
	public void setOffenderId(int offenderId) {
		this.offenderId = offenderId;
	}
	public int getCustodyPeriodSeq() {
		return custodyPeriodSeq;
	}
	public void setCustodyPeriodSeq(int custodyPeriodSeq) {
		this.custodyPeriodSeq = custodyPeriodSeq;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDaysAfterEffDate() {
		return daysAfterEffDate;
	}
	public void setDaysAfterEffDate(int daysAfterEffDate) {
		this.daysAfterEffDate = daysAfterEffDate;
	}

}
