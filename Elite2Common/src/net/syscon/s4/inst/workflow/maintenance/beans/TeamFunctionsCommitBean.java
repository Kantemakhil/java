package net.syscon.s4.inst.workflow.maintenance.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.Teams;
/**
 * Class AgyLocTeamFunctionsCommitBean 
 */
@XmlRootElement
public class TeamFunctionsCommitBean extends BaseModel implements Serializable{
	private List<TeamFunctions> insertList;
	private List<TeamFunctions> deleteList;
	private List<TeamFunctions> updateList;

	public void setInsertList(List<TeamFunctions> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<TeamFunctions> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<TeamFunctions> deleteList){
		this.deleteList = deleteList;
	}

	public List<TeamFunctions> getInsertList(){
		return insertList;
	}

	public List<TeamFunctions> getUpdateList(){
		return updateList;
	}

	public List<TeamFunctions> getDeleteList(){
		return deleteList;
	}

}
