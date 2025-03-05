package net.syscon.s4.common.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.validators.GlobalValidation;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@GlobalValidation(message = "atleast.one.mandatory")
public class OdynfrmSubmitDataBean extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("offenderBookId")
	private Long offenderBookId;
	
	@JsonProperty("formName")
	private String formName;
	
	@JsonProperty("searchString")
	private String searchString;

	@JsonProperty("formInfoJson")
	private String formInfoJson;
	
	@JsonProperty("formInfoJsonBlob")
	private byte[] formInfoJsonBlob;
	
	@JsonProperty("formIdentifier")
	private String formIdentifier;

	@JsonProperty("createDatetime")
	private Date createDatetime;

	@JsonProperty("createUserId")
	private String createUserId;

	@JsonProperty("modifyDatetime")
	private Date modifyDatetime;

	@JsonProperty("modifyUserId")
	private String modifyUserId;
	
	@JsonProperty("isNotCamundaCall")
	private boolean isNotCamundaCall = true;
	
	@JsonProperty("calcReason")
	private String calcReason;	
	
	@JsonProperty("actionType")
	private String actionType;	
	
	@JsonProperty("custodyStatus")
	private String custodyStatus;
	
	@JsonProperty("displayNo")
	private String displayNo;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("chargeId")
	private String chargeId;
	
	@JsonProperty("offenceId")
	private Long offenceId;
	
	@JsonProperty("recordId")
	private String recordId;
	
	@JsonProperty("orderType")
	private String orderType;
	
	@JsonProperty("updateCustodyStatus")
	private boolean updateCustodyStatus;
	
	@JsonProperty("moduleName")
	private String moduleName;
	
	@JsonProperty("offenderIdDisplay")
	private String offenderIdDisplay;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("calcEngineInput")
	private byte[] calcEngineInput;
	
	@JsonProperty("orderOperations")
	private String orderOperations;
	
	@JsonProperty("orderCategory")
	private String orderCategory;
	
	@JsonProperty("orderUpdateRecords")
	private String orderUpdateRecords;
	
	@JsonProperty("orderNo")
	private Long orderNo;
	
	@JsonProperty("chargesOperations")
	private String chargesOperations;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormInfoJson() {
		return formInfoJson;
	}

	public void setFormInfoJson(String formInfoJson) {
		this.formInfoJson = formInfoJson;
	}

	public byte[] getFormInfoJsonBlob() {
		return formInfoJsonBlob;
	}

	public void setFormInfoJsonBlob(byte[] bs) {
		this.formInfoJsonBlob = bs;
	}

	public String getFormIdentifier() {
		return formIdentifier;
	}

	public void setFormIdentifier(String formIdentifier) {
		this.formIdentifier = formIdentifier;
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

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public boolean getIsNotCamundaCall() {
		return isNotCamundaCall;
	}

	public void setIsNotCamundaCall(boolean isNotCamundaCall) {
		this.isNotCamundaCall = isNotCamundaCall;
	}

	public String getCalcReason() {
		return calcReason;
	}

	public void setCalcReason(String calcReason) {
		this.calcReason = calcReason;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public String getCustodyStatus() {
		return custodyStatus;
	}

	public void setCustodyStatus(String custodyStatus) {
		this.custodyStatus = custodyStatus;
	}

	public Long  getOffenderBookId() {
		return offenderBookId;
	}

	public void setOffenderBookId(Long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	public String getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(String displayNo) {
		this.displayNo = displayNo;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getOffenceId() {
		return offenceId;
	}

	public void setOffenceId(Long offenceId) {
		this.offenceId = offenceId;
	}

	

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public boolean isUpdateCustodyStatus() {
		return updateCustodyStatus;
	}

	public void setUpdateCustodyStatus(boolean updateCustodyStatus) {
		this.updateCustodyStatus = updateCustodyStatus;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOffenderIdDisplay() {
		return offenderIdDisplay;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setOffenderIdDisplay(String offenderIdDisplay) {
		this.offenderIdDisplay = offenderIdDisplay;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte[] getCalcEngineInput() {
		return calcEngineInput;
	}

	public void setCalcEngineInput(byte[] calcEngineInput) {
		this.calcEngineInput = calcEngineInput;
	}

	public String getOrderOperations() {
		return orderOperations;
	}

	public void setOrderOperations(String orderOperations) {
		this.orderOperations = orderOperations;
	}

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getOrderUpdateRecords() {
		return orderUpdateRecords;
	}

	public void setOrderUpdateRecords(String orderUpdateRecords) {
		this.orderUpdateRecords = orderUpdateRecords;
	}

	public String getChargesOperations() {
		return chargesOperations;
	}

	public void setChargesOperations(String chargesOperations) {
		this.chargesOperations = chargesOperations;
	}

	
}
