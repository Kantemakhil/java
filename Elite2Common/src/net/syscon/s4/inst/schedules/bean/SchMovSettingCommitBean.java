package net.syscon.s4.inst.schedules.bean;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

public class SchMovSettingCommitBean  extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ScheduleMovementSetting> insertList;
	private List<ScheduleMovementSetting> updateList;
	public List<ScheduleMovementSetting> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<ScheduleMovementSetting> insertList) {
		this.insertList = insertList;
	}
	public List<ScheduleMovementSetting> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<ScheduleMovementSetting> updateList) {
		this.updateList = updateList;
	}

}
