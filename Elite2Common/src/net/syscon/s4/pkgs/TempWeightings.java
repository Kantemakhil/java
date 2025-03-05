package net.syscon.s4.pkgs;

import java.io.Serializable;

import net.syscon.s4.common.beans.BaseModel;

public class TempWeightings extends BaseModel implements Serializable {
	private Long tmpWeiId;
	private Long bookId;
	private String orderCode;
	private String orderType;
	private String component;
	private Long weighting;
	private Long sessionId;
	private Long calculatedWeighting;
	private Long overriddenBy;
	private Long rowId;
	
	private String createUserId;

	public Long getTmpWeiId() {
		return tmpWeiId;
	}

	public void setTmpWeiId(Long tmpWeiId) {
		this.tmpWeiId = tmpWeiId;
	}

	public Long getBookId(){
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Long getWeighting() {
		return weighting;
	}

	public void setWeighting(Long weighting) {
		this.weighting = weighting;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getCalculatedWeighting() {
		return calculatedWeighting;
	}

	public void setCalculatedWeighting(Long calculatedWeighting) {
		this.calculatedWeighting = calculatedWeighting;
	}

	public Long getOverriddenBy() {
		return overriddenBy;
	}

	public void setOverriddenBy(Long overriddenBy) {
		this.overriddenBy = overriddenBy;
	}

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}
