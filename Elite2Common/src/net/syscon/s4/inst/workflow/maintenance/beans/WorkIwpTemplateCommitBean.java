package net.syscon.s4.inst.workflow.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class WorkIwpTemplateCommitBean 
 */
@XmlRootElement
public class WorkIwpTemplateCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WorkIwpTemplate> insertList;
	private List<WorkIwpTemplate> deleteList;
	private List<WorkIwpTemplate> updateList;

	public void setInsertList(List<WorkIwpTemplate> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<WorkIwpTemplate> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<WorkIwpTemplate> deleteList){
		this.deleteList = deleteList;
	}

	public List<WorkIwpTemplate> getInsertList(){
		return insertList;
	}

	public List<WorkIwpTemplate> getUpdateList(){
		return updateList;
	}

	public List<WorkIwpTemplate> getDeleteList(){
		return deleteList;
	}

}
