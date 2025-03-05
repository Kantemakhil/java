/**
 * 
 */
package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gettha T
 *
 */
public class VRouteLocations   {

	@JsonProperty("segmentLength")
	private Integer segmentLength;
	
	@JsonProperty("toSeq")
	private String toSeq;
	
	@JsonProperty("fromSeq")
	private String fromSeq;
	
	@JsonProperty("toAgyLocId")
	private String toAgyLocId;
	
	@JsonProperty("fromAgyLocId")
	private String fromAgyLocId;
	
	@JsonProperty("routeName")
	private String routeName;
	
	@JsonProperty("vacancy")
	private Integer vacancy;
	
	public Integer getVacancy() {
		return vacancy;
	}
	public void setVacancy(Integer vacancy) {
		this.vacancy = vacancy;
	}
	public Integer getSegmentLength() {
		return segmentLength;
	}
	public void setSegmentLength(Integer segmentLength) {
		this.segmentLength = segmentLength;
	}
	public String getToSeq() {
		return toSeq;
	}
	public void setToSeq(String toSeq) {
		this.toSeq = toSeq;
	}
	public String getFromSeq() {
		return fromSeq;
	}
	public void setFromSeq(String fromSeq) {
		this.fromSeq = fromSeq;
	}
	public String getToAgyLocId() {
		return toAgyLocId;
	}
	public void setToAgyLocId(String toAgyLocId) {
		this.toAgyLocId = toAgyLocId;
	}
	public String getFromAgyLocId() {
		return fromAgyLocId;
	}
	public void setFromAgyLocId(String fromAgyLocId) {
		this.fromAgyLocId = fromAgyLocId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
}
