package net.syscon.s4.legalorders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

public class PotWeightings extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("potWgtId")
	private int potWgtId;
	@JsonProperty("orderType")
	private String orderType;
	@JsonProperty("orderCode")
	private String orderCode;
	@JsonProperty("component")
	private String component;
	@JsonProperty("offenderDangerRating")
	private String offenderDangerRating;
	@JsonProperty("rangeCode")
	private String rangeCode;
	@JsonProperty("weighting")
	private BigDecimal weighting;
	@JsonProperty("active")
	private String active;
	@JsonProperty("courtType")
	private String courtType;
	@JsonProperty("expiryDate")
	private Date expiryDate;
	@JsonProperty("orderTypeDom")
	private String orderTypeDom;
	@JsonProperty("componentDom")
	private String componentDom;
	@JsonProperty("ratingDom")
	private String ratingDom;
	@JsonProperty("courtTypeDom")
	private String courtTypeDom;
	@JsonProperty("cgnbtOrderType2")
	private String cgnbtOrderType2;
	@JsonProperty("cgnbtOrderCode3")
	private String cgnbtOrderCode3;
	@JsonProperty("cgnbtOrderCode2")
	private String cgnbtOrderCode2;
	@JsonProperty("cgnbtOrderType")
	private String cgnbtOrderType;
	@JsonProperty("cgnbtOrderCode")
	private String cgnbtOrderCode;

	public int getPotWgtId() {
		return potWgtId;
	}

	public void setPotWgtId(int potWgtId) {
		this.potWgtId = potWgtId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getOffenderDangerRating() {
		return offenderDangerRating;
	}

	public void setOffenderDangerRating(String offenderDangerRating) {
		this.offenderDangerRating = offenderDangerRating;
	}

	public String getRangeCode() {
		return rangeCode;
	}

	public void setRangeCode(String rangeCode) {
		this.rangeCode = rangeCode;
	}

	public BigDecimal getWeighting() {
		return weighting;
	}

	public void setWeighting(BigDecimal weighting) {
		this.weighting = weighting;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOrderTypeDom() {
		return orderTypeDom;
	}

	public void setOrderTypeDom(String orderTypeDom) {
		this.orderTypeDom = orderTypeDom;
	}

	public String getComponentDom() {
		return componentDom;
	}

	public void setComponentDom(String componentDom) {
		this.componentDom = componentDom;
	}

	public String getRatingDom() {
		return ratingDom;
	}

	public void setRatingDom(String ratingDom) {
		this.ratingDom = ratingDom;
	}

	public String getCourtTypeDom() {
		return courtTypeDom;
	}

	public void setCourtTypeDom(String courtTypeDom) {
		this.courtTypeDom = courtTypeDom;
	}

	public String getCgnbtOrderType2() {
		return cgnbtOrderType2;
	}

	public void setCgnbtOrderType2(String cgnbtOrderType2) {
		this.cgnbtOrderType2 = cgnbtOrderType2;
	}
	
	public String getCgnbtOrderCode3() {
		return cgnbtOrderCode3;
	}

	public void setCgnbtOrderCode3(String cgnbtOrderCode3) {
		this.cgnbtOrderCode3 = cgnbtOrderCode3;
	}

	public String getCgnbtOrderCode2() {
		return cgnbtOrderCode2;
	}

	public void setCgnbtOrderCode2(String cgnbtOrderCode2) {
		this.cgnbtOrderCode2 = cgnbtOrderCode2;
	}

	public String getCgnbtOrderType() {
		return cgnbtOrderType;
	}

	public void setCgnbtOrderType(String cgnbtOrderType) {
		this.cgnbtOrderType = cgnbtOrderType;
	}

	public String getCgnbtOrderCode() {
		return cgnbtOrderCode;
	}

	public void setCgnbtOrderCode(String cgnbtOrderCode) {
		this.cgnbtOrderCode = cgnbtOrderCode;
	}

	@Override
	public String toString() {
		return "PotWeightings [potWgtId=" + potWgtId + ", orderType=" + orderType + ", orderCode=" + orderCode
				+ ", component=" + component + ", offenderDangerRating=" + offenderDangerRating + ", rangeCode="
				+ rangeCode + ", weighting=" + weighting + ", active=" + active + ", courtType=" + courtType
				+ ", expiryDate=" + expiryDate + ", orderTypeDom=" + orderTypeDom + ", componentDom=" + componentDom
				+ ", ratingDom=" + ratingDom + ", courtTypeDom=" + courtTypeDom + "]";
	}

}
