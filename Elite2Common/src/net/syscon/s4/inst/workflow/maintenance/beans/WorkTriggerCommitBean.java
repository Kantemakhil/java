package net.syscon.s4.inst.workflow.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class WorkTriggerCommitBean 
 */
@XmlRootElement
public class WorkTriggerCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WorkTrigger> insertList;
	private List<WorkTrigger> deleteList;
	private List<WorkTrigger> updateList;

	public void setInsertList(List<WorkTrigger> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<WorkTrigger> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<WorkTrigger> deleteList){
		this.deleteList = deleteList;
	}

	public List<WorkTrigger> getInsertList(){
		return insertList;
	}

	public List<WorkTrigger> getUpdateList(){
		return updateList;
	}

	public List<WorkTrigger> getDeleteList(){
		return deleteList;
	}

}
