package net.syscon.s4.cf.offendertransactions.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentPlanReportBean {
	
	private String fOffName;
	
	private String fOffenderId;
	
	private String fCaseNumber;
	
	private Date fStartDate;
	
	private Date fEndDate;
	
	private String  fTotalDue;
	
	private BigDecimal fTotalDues;
	
	

	private String fGroupCode;
	
	
    List<PaymentPlanBean> list = new ArrayList<>();
    
    private String caseLoadDesc;
    private String profileDesc;
    private String module;
   
    private String userId;
    
    public BigDecimal getfTotalDues() {
		return fTotalDues;
	}

	public void setfTotalDues(BigDecimal fTotalDues) {
		this.fTotalDues = fTotalDues;
	}

    public String getfTotalDue() {
		return fTotalDue;
	}

	public void setfTotalDue(String fTotalDue) {
		this.fTotalDue = fTotalDue;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getfStartDate() {
		return fStartDate;
	}

	public void setfStartDate(Date fStartDate) {
		this.fStartDate = fStartDate;
	}

	public Date getfEndDate() {
		return fEndDate;
	}

	public void setfEndDate(Date fEndDate) {
		this.fEndDate = fEndDate;
	}

	public String getfGroupCode() {
		return fGroupCode;
	}

	public void setfGroupCode(String fGroupCode) {
		this.fGroupCode = fGroupCode;
	}

	public String getfOffenderId() {
		return fOffenderId;
	}

	public void setfOffenderId(String fOffenderId) {
		this.fOffenderId = fOffenderId;
	}

	public String getCaseLoadDesc() {
		return caseLoadDesc;
	}

	public void setCaseLoadDesc(String caseLoadDesc) {
		this.caseLoadDesc = caseLoadDesc;
	}

	public String getProfileDesc() {
		return profileDesc;
	}

	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}




public String getfOffName() {
	return fOffName;
}

public void setfOffName(String fOffName) {
	this.fOffName = fOffName;
}

public String getfCaseNumber() {
	return fCaseNumber;
}

public void setfCaseNumber(String fCaseNumber) {
	this.fCaseNumber = fCaseNumber;
}



public List<PaymentPlanBean> getList() {
	return list;
}

public void setList(List<PaymentPlanBean> list) {
	this.list = list;
}	
   
   
	
	
	
	
	
	
	
	

}
