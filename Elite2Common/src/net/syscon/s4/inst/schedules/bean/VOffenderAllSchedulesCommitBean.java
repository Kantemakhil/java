package net.syscon.s4.inst.schedules.bean;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VOffenderAllSchedulesCommitBean extends BaseModel
 */
@XmlRootElement
public class VOffenderAllSchedulesCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VOffenderAllSchedules> insertList;
	private List<VOffenderAllSchedules> deleteList;
	private List<VOffenderAllSchedules> updateList;
	private ScheduleSeries seriesInsertList;
	private String nonAssConflictMsg;

	public void setInsertList(List<VOffenderAllSchedules> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VOffenderAllSchedules> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VOffenderAllSchedules> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffenderAllSchedules> getInsertList(){
		return insertList;
	}

	public List<VOffenderAllSchedules> getUpdateList(){
		return updateList;
	}

	public List<VOffenderAllSchedules> getDeleteList(){
		return deleteList;
	}

	public ScheduleSeries getSeriesInsertList() {
		return seriesInsertList;
	}

	public void setSeriesInsertList(ScheduleSeries seriesInsertList) {
		this.seriesInsertList = seriesInsertList;
	}

	public String getNonAssConflictMsg() {
		return nonAssConflictMsg;
	}

	public void setNonAssConflictMsg(String nonAssConflictMsg) {
		this.nonAssConflictMsg = nonAssConflictMsg;
	}
	
	
	

}
