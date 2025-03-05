package net.syscon.s4.inst.workflow.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class WorkFunctionCommitBean 
 */
@XmlRootElement
public class WorkFunctionCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WorkFunction> insertList;
	private List<WorkFunction> deleteList;
	private List<WorkFunction> updateList;

	public void setInsertList(List<WorkFunction> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<WorkFunction> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<WorkFunction> deleteList){
		this.deleteList = deleteList;
	}

	public List<WorkFunction> getInsertList(){
		return insertList;
	}

	public List<WorkFunction> getUpdateList(){
		return updateList;
	}

	public List<WorkFunction> getDeleteList(){
		return deleteList;
	}

}
