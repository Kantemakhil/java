package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.legalorders.EepbiFixedData;

@XmlRootElement
public class EepbiFixedDataCommitBean extends BaseModel implements Serializable{
	private List<EepbiFixedData> insertList;
	private List<EepbiFixedData> deleteList;
	private List<EepbiFixedData> updateList;
	public List<EepbiFixedData> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<EepbiFixedData> insertList) {
		this.insertList = insertList;
	}
	public List<EepbiFixedData> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<EepbiFixedData> deleteList) {
		this.deleteList = deleteList;
	}
	public List<EepbiFixedData> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<EepbiFixedData> updateList) {
		this.updateList = updateList;
	}

}
