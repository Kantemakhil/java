/**
 * 
 */
package net.syscon.s4.inst.visitsmanagement.beans;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class ValidateVisitorBean
 *
 */
public class ValidateVisitorBean {

	@JsonProperty("visitorList")
	private List<VOffenderVisitVisitors> visitorList;

	@JsonProperty("visitOfflist")
	private List<OffenderVisitVisitors> visitOfflist;

	@JsonProperty("vPersonId")
	private Integer vPersonId;

	@JsonProperty("vOffenderBookId")
	private BigDecimal vOffenderBookId;

	/**
	 * 
	 */
	public ValidateVisitorBean() {
		// ValidateVisitorBean
	}

	/**
	 * @return the visitorList
	 */
	public List<VOffenderVisitVisitors> getVisitorList() {
		return visitorList;
	}

	/**
	 * @param visitorList
	 *            the visitorList to set
	 */
	public void setVisitorList(final List<VOffenderVisitVisitors> visitorList) {
		this.visitorList = visitorList;
	}

	/**
	 * @return the visitOfflist
	 */
	public List<OffenderVisitVisitors> getVisitOfflist() {
		return visitOfflist;
	}

	/**
	 * @param visitOfflist
	 *            the visitOfflist to set
	 */
	public void setVisitOfflist(final List<OffenderVisitVisitors> visitOfflist) {
		this.visitOfflist = visitOfflist;
	}

	/**
	 * @return the vPersonId
	 */
	public Integer getvPersonId() {
		return vPersonId;
	}

	/**
	 * @param vPersonId
	 *            the vPersonId to set
	 */
	public void setvPersonId(final Integer vPersonId) {
		this.vPersonId = vPersonId;
	}

	/**
	 * @return the vOffenderBookId
	 */
	public BigDecimal getvOffenderBookId() {
		return vOffenderBookId;
	}

	/**
	 * @param vOffenderBookId
	 *            the vOffenderBookId to set
	 */
	public void setvOffenderBookId(final BigDecimal vOffenderBookId) {
		this.vOffenderBookId = vOffenderBookId;
	}

}
