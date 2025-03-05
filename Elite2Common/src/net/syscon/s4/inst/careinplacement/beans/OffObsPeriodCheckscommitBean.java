package net.syscon.s4.inst.careinplacement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class OffObsPeriodCheckscommitBean extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	private List<OffObsPeriodChecks> insertList;
	private List<OffObsPeriodChecks> deleteList;
	private List<OffObsPeriodChecks> updateList;
	
	public List<OffObsPeriodChecks> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffObsPeriodChecks> insertList) {
		this.insertList = insertList;
	}
	public List<OffObsPeriodChecks> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffObsPeriodChecks> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffObsPeriodChecks> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffObsPeriodChecks> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
