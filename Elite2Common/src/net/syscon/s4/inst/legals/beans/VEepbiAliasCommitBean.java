package net.syscon.s4.inst.legals.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inst.legals.legalorders.VEepbiAlias;

@XmlRootElement
public class VEepbiAliasCommitBean extends BaseModel implements Serializable{
	private List<VEepbiAlias> insertList;
	private List<VEepbiAlias> deleteList;
	private List<VEepbiAlias> updateList;
	public List<VEepbiAlias> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<VEepbiAlias> insertList) {
		this.insertList = insertList;
	}
	public List<VEepbiAlias> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<VEepbiAlias> deleteList) {
		this.deleteList = deleteList;
	}
	public List<VEepbiAlias> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<VEepbiAlias> updateList) {
		this.updateList = updateList;
	}

}
