package net.syscon.s4.inst.legals.legalorders;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class EepbiFixedData extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("fileSeq")
	private long fileSeq	;
	@JsonProperty("lineNo")
	private long lineNo	;
	@JsonProperty("cni")
	private long cni	;
	@JsonProperty("minNum1")
	private long minNum1;
	@JsonProperty("minNum2")
	private long minNum2;
	@JsonProperty("minNum3")
	private long minNum3;
	@JsonProperty("minNum4")
	private long minNum4;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("dob")
	private Date dob	;
	@JsonProperty("sex")
	private String sex	;
	@JsonProperty("address")
	private String address;
	@JsonProperty("suburb")
	private String suburb	;
	@JsonProperty("postcode")
	private long postcode	;
	@JsonProperty("al1LastName")
	private String al1LastName;
	@JsonProperty("al1FirstName")
	private String al1FirstName;
	@JsonProperty("al1MiddleName")
	private String al1MiddleName;
	@JsonProperty("al1Dob")
	private Date al1Dob	;
	@JsonProperty("al1Sex")
	private String al1Sex	;
	@JsonProperty("al2LastName")
	private String al2LastName;
	@JsonProperty("al2FirstNam")
	private String al2FirstName;
	@JsonProperty("al2MiddleName")
	private String al2MiddleName;
	@JsonProperty("al2Dob")
	private Date al2Dob	;
	@JsonProperty("al2Sex")
	private String al2Sex	;
	@JsonProperty("al3LastName")
	private String al3LastName;
	@JsonProperty("al3FirstName")
	private String al3FirstName;
	@JsonProperty("al3MiddleName")
	private String al3MiddleName;
	@JsonProperty("al3Dob")
	private Date al3Dob	;
	@JsonProperty("al3Sex")
	private String al3Sex	;
	@JsonProperty("al4LastName")
	private String al4LastName;
	@JsonProperty("al4FirstName")
	private String al4FirstName;
	@JsonProperty("al4MiddleName")
	private String al4MiddleName;
	@JsonProperty("al4Dob")
	private Date al4Dob	;
	@JsonProperty("al4Sex")
	private String al4Sex	;
	@JsonProperty("al5LastName")
	private String al5LastName;
	@JsonProperty("al5FirstName")
	private String al5FirstName;
	@JsonProperty("al5MiddleName")
	private String al5MiddleName;
	@JsonProperty("al5Dob")
	private Date al5Dob	;
	@JsonProperty("al5Sex")
	private String al5Sex	;
	@JsonProperty("jurisLevel")
	private long jurisLevel	;
	@JsonProperty("courtCode")
	private long courtCode	;
	@JsonProperty("outcomeDate")
	private Date outcomeDate;
	@JsonProperty("glcCaseNo")
	private String glcCaseNo;
	@JsonProperty("chargeRefNo")
	private long chargeRefNo	;
	@JsonProperty("offSeqNo")
	private long offSeqNo	;
	@JsonProperty("offRefNo")
	private long offRefNo	;
	@JsonProperty("lawPartNo")
	private long lawPartNo;
	@JsonProperty("lawPartShortDesc")
	private String lawPartShortDesc	;
	@JsonProperty("identityMatchedFlag")
	private String identityMatchedFlag;
	@JsonProperty("chargeMatchedFlag")
	private String chargeMatchedFlag	;
	public long getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(long fileSeq) {
		this.fileSeq = fileSeq;
	}
	public long getLineNo() {
		return lineNo;
	}
	public void setLineNo(long lineNo) {
		this.lineNo = lineNo;
	}
	public long getCni() {
		return cni;
	}
	public void setCni(long cni) {
		this.cni = cni;
	}
	public long getMinNum1() {
		return minNum1;
	}
	public void setMinNum1(long minNum1) {
		this.minNum1 = minNum1;
	}
	public long getMinNum2() {
		return minNum2;
	}
	public void setMinNum2(long minNum2) {
		this.minNum2 = minNum2;
	}
	public long getMinNum3() {
		return minNum3;
	}
	public void setMinNum3(long minNum3) {
		this.minNum3 = minNum3;
	}
	public long getMinNum4() {
		return minNum4;
	}
	public void setMinNum4(long minNum4) {
		this.minNum4 = minNum4;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public long getPostcode() {
		return postcode;
	}
	public void setPostcode(long postcode) {
		this.postcode = postcode;
	}
	public String getAl1LastName() {
		return al1LastName;
	}
	public void setAl1LastName(String al1LastName) {
		this.al1LastName = al1LastName;
	}
	public String getAl1FirstName() {
		return al1FirstName;
	}
	public void setAl1FirstName(String al1FirstName) {
		this.al1FirstName = al1FirstName;
	}
	public String getAl1MiddleName() {
		return al1MiddleName;
	}
	public void setAl1MiddleName(String al1MiddleName) {
		this.al1MiddleName = al1MiddleName;
	}
	public Date getAl1Dob() {
		return al1Dob;
	}
	public void setAl1Dob(Date al1Dob) {
		this.al1Dob = al1Dob;
	}
	public String getAl1Sex() {
		return al1Sex;
	}
	public void setAl1Sex(String al1Sex) {
		this.al1Sex = al1Sex;
	}
	public String getAl2LastName() {
		return al2LastName;
	}
	public void setAl2LastName(String al2LastName) {
		this.al2LastName = al2LastName;
	}
	public String getAl2FirstName() {
		return al2FirstName;
	}
	public void setAl2FirstName(String al2FirstName) {
		this.al2FirstName = al2FirstName;
	}
	public String getAl2MiddleName() {
		return al2MiddleName;
	}
	public void setAl2MiddleName(String al2MiddleName) {
		this.al2MiddleName = al2MiddleName;
	}
	public Date getAl2Dob() {
		return al2Dob;
	}
	public void setAl2Dob(Date al2Dob) {
		this.al2Dob = al2Dob;
	}
	public String getAl2Sex() {
		return al2Sex;
	}
	public void setAl2Sex(String al2Sex) {
		this.al2Sex = al2Sex;
	}
	public String getAl3LastName() {
		return al3LastName;
	}
	public void setAl3LastName(String al3LastName) {
		this.al3LastName = al3LastName;
	}
	public String getAl3FirstName() {
		return al3FirstName;
	}
	public void setAl3FirstName(String al3FirstName) {
		this.al3FirstName = al3FirstName;
	}
	public String getAl3MiddleName() {
		return al3MiddleName;
	}
	public void setAl3MiddleName(String al3MiddleName) {
		this.al3MiddleName = al3MiddleName;
	}
	public Date getAl3Dob() {
		return al3Dob;
	}
	public void setAl3Dob(Date al3Dob) {
		this.al3Dob = al3Dob;
	}
	public String getAl3Sex() {
		return al3Sex;
	}
	public void setAl3Sex(String al3Sex) {
		this.al3Sex = al3Sex;
	}
	public String getAl4LastName() {
		return al4LastName;
	}
	public void setAl4LastName(String al4LastName) {
		this.al4LastName = al4LastName;
	}
	public String getAl4FirstName() {
		return al4FirstName;
	}
	public void setAl4FirstName(String al4FirstName) {
		this.al4FirstName = al4FirstName;
	}
	public String getAl4MiddleName() {
		return al4MiddleName;
	}
	public void setAl4MiddleName(String al4MiddleName) {
		this.al4MiddleName = al4MiddleName;
	}
	public Date getAl4Dob() {
		return al4Dob;
	}
	public void setAl4Dob(Date al4Dob) {
		this.al4Dob = al4Dob;
	}
	public String getAl4Sex() {
		return al4Sex;
	}
	public void setAl4Sex(String al4Sex) {
		this.al4Sex = al4Sex;
	}
	public String getAl5LastName() {
		return al5LastName;
	}
	public void setAl5LastName(String al5LastName) {
		this.al5LastName = al5LastName;
	}
	public String getAl5FirstName() {
		return al5FirstName;
	}
	public void setAl5FirstName(String al5FirstName) {
		this.al5FirstName = al5FirstName;
	}
	public String getAl5MiddleName() {
		return al5MiddleName;
	}
	public void setAl5MiddleName(String al5MiddleName) {
		this.al5MiddleName = al5MiddleName;
	}
	public Date getAl5Dob() {
		return al5Dob;
	}
	public void setAl5Dob(Date al5Dob) {
		this.al5Dob = al5Dob;
	}
	public String getAl5Sex() {
		return al5Sex;
	}
	public void setAl5Sex(String al5Sex) {
		this.al5Sex = al5Sex;
	}
	public long getJurisLevel() {
		return jurisLevel;
	}
	public void setJurisLevel(long jurisLevel) {
		this.jurisLevel = jurisLevel;
	}
	public long getCourtCode() {
		return courtCode;
	}
	public void setCourtCode(long courtCode) {
		this.courtCode = courtCode;
	}
	public Date getOutcomeDate() {
		return outcomeDate;
	}
	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}
	public String getGlcCaseNo() {
		return glcCaseNo;
	}
	public void setGlcCaseNo(String glcCaseNo) {
		this.glcCaseNo = glcCaseNo;
	}
	public long getChargeRefNo() {
		return chargeRefNo;
	}
	public void setChargeRefNo(long chargeRefNo) {
		this.chargeRefNo = chargeRefNo;
	}
	public long getOffSeqNo() {
		return offSeqNo;
	}
	public void setOffSeqNo(long offSeqNo) {
		this.offSeqNo = offSeqNo;
	}
	public long getOffRefNo() {
		return offRefNo;
	}
	public void setOffRefNo(long offRefNo) {
		this.offRefNo = offRefNo;
	}
	public long getLawPartNo() {
		return lawPartNo;
	}
	public void setLawPartNo(long lawPartNo) {
		this.lawPartNo = lawPartNo;
	}
	public String getLawPartShortDesc() {
		return lawPartShortDesc;
	}
	public void setLawPartShortDesc(String lawPartShortDesc) {
		this.lawPartShortDesc = lawPartShortDesc;
	}
	public String getIdentityMatchedFlag() {
		return identityMatchedFlag;
	}
	public void setIdentityMatchedFlag(String identityMatchedFlag) {
		this.identityMatchedFlag = identityMatchedFlag;
	}
	public String getChargeMatchedFlag() {
		return chargeMatchedFlag;
	}
	public void setChargeMatchedFlag(String chargeMatchedFlag) {
		this.chargeMatchedFlag = chargeMatchedFlag;
	}
	

}
