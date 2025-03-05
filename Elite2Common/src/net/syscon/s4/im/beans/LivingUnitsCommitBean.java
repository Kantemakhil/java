package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LivingUnitsCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LivingUnits> insertList;
	private List<LivingUnits> deleteList;
	private List<LivingUnits> updateList;
	private Long housingLevel;

	public Long getHousingLevel() {
		return housingLevel;
	}

	public void setHousingLevel(Long housingLevel) {
		this.housingLevel = housingLevel;
	}

	public void setInsertList(final List<LivingUnits> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<LivingUnits> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<LivingUnits> deleteList) {
		this.deleteList = deleteList;
	}

	public List<LivingUnits> getInsertList() {
		return insertList;
	}

	public List<LivingUnits> getUpdateList() {
		return updateList;
	}

	public List<LivingUnits> getDeleteList() {
		return deleteList;
	}

}
