package net.syscon.s4.inst.transportation.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class AgencySegmentLengthsCommitBean extends BaseBean
 * @author Vrnda Software Technologies 
 * @version 1.0 
 */
public class AgencySegmentLengthsCommitBean extends BaseModel implements Serializable{
	
	private List<AgencySegmentLengths> insertList;
	private List<AgencySegmentLengths> deleteList;
	private List<AgencySegmentLengths> updateList;

	public void setInsertList(List<AgencySegmentLengths> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgencySegmentLengths> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgencySegmentLengths> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgencySegmentLengths> getInsertList(){
		return insertList;
	}

	public List<AgencySegmentLengths> getUpdateList(){
		return updateList;
	}

	public List<AgencySegmentLengths> getDeleteList(){
		return deleteList;
	}

}
