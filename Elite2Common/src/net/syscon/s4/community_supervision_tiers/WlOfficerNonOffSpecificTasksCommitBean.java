package net.syscon.s4.community_supervision_tiers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class WlOfficerNonOffSpecificTasksCommitBean extends BaseModel implements Serializable {

	private List<WlOfficerNonOffSpecificTasks> insertList;
	private List<WlOfficerNonOffSpecificTasks> updateList;
	private List<WlOfficerNonOffSpecificTasks> deleteList;
	
	private BigDecimal availableWLUnits;	

	public List<WlOfficerNonOffSpecificTasks> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<WlOfficerNonOffSpecificTasks> insertList) {
		this.insertList = insertList;
	}

	public List<WlOfficerNonOffSpecificTasks> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<WlOfficerNonOffSpecificTasks> updateList) {
		this.updateList = updateList;
	}

	public List<WlOfficerNonOffSpecificTasks> getDeleteList() {
		return deleteList;
	}

	public void setDeleteList(List<WlOfficerNonOffSpecificTasks> deleteList) {
		this.deleteList = deleteList;
	}

	public BigDecimal getAvailableWLUnits() {
		return availableWLUnits;
	}

	public void setAvailableWLUnits(BigDecimal availableWLUnits) {
		this.availableWLUnits = availableWLUnits;
	}
}
