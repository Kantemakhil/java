package net.syscon.s4.inst.careinplacement.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement 
public class OffObsPrdCharacteristicscommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	private List<OffObsPrdCharacteristics> insertList;
	private List<OffObsPrdCharacteristics> deleteList;
	private List<OffObsPrdCharacteristics> updateList;
	
	public List<OffObsPrdCharacteristics> getInsertList() {
		return insertList;
	}
	public void setInsertList(final List<OffObsPrdCharacteristics> insertList) {
		this.insertList = insertList;
	}
	public List<OffObsPrdCharacteristics> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(final List<OffObsPrdCharacteristics> deleteList) {
		this.deleteList = deleteList;
	}
	public List<OffObsPrdCharacteristics> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(final List<OffObsPrdCharacteristics> updateList) {
		this.updateList = updateList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
