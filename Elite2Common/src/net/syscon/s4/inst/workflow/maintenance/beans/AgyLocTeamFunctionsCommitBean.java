package net.syscon.s4.inst.workflow.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AgyLocTeamFunctionsCommitBean 
 */
@XmlRootElement
public class AgyLocTeamFunctionsCommitBean extends BaseModel implements Serializable{
	private List<AgyLocTeamFunctions> insertList;
	private List<AgyLocTeamFunctions> deleteList;
	private List<AgyLocTeamFunctions> updateList;

	public void setInsertList(List<AgyLocTeamFunctions> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgyLocTeamFunctions> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgyLocTeamFunctions> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgyLocTeamFunctions> getInsertList(){
		return insertList;
	}

	public List<AgyLocTeamFunctions> getUpdateList(){
		return updateList;
	}

	public List<AgyLocTeamFunctions> getDeleteList(){
		return deleteList;
	}

}
