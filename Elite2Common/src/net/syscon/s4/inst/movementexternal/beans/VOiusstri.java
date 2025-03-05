package net.syscon.s4.inst.movementexternal.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_OIUSSTRI database table.
 * 
 */
public class VOiusstri implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object actDepartureTime;

	private Object departureDate;

	private String description;

	private Object estCompTime;

	private Object estDepartureTime;

	private String routeName;

	private BigDecimal scheduledTripId;

	private String status;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Date fMDate;
	
	private Date fMTime;

	public Date getfMDate() {
		return fMDate;
	}

	public void setfMDate(Date fMDate) {
		this.fMDate = fMDate;
	}

	public Date getfMTime() {
		return fMTime;
	}

	public void setfMTime(Date fMTime) {
		this.fMTime = fMTime;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public VOiusstri() {
	}

	public Object getActDepartureTime() {
		return this.actDepartureTime;
	}

	public void setActDepartureTime(Object actDepartureTime) {
		this.actDepartureTime = actDepartureTime;
	}

	public Object getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(Object departureDate) {
		this.departureDate = departureDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getEstCompTime() {
		return this.estCompTime;
	}

	public void setEstCompTime(Object estCompTime) {
		this.estCompTime = estCompTime;
	}

	public Object getEstDepartureTime() {
		return this.estDepartureTime;
	}

	public void setEstDepartureTime(Object estDepartureTime) {
		this.estDepartureTime = estDepartureTime;
	}

	public String getRouteName() {
		return this.routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public BigDecimal getScheduledTripId() {
		return this.scheduledTripId;
	}

	public void setScheduledTripId(BigDecimal scheduledTripId) {
		this.scheduledTripId = scheduledTripId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
