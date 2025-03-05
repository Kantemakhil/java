package net.syscon.s4.inst.careinplacement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffObservationPeriodscommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;

	private List<OffObservationPeriods> insertList;
	private List<OffObservationPeriods> deleteList;
	private List<OffObservationPeriods> updateList;
	
	public List<OffObservationPeriods> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffObservationPeriods> insertList) {
		this.insertList = insertList;
	}
	public List<OffObservationPeriods> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffObservationPeriods> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffObservationPeriods> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffObservationPeriods> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
