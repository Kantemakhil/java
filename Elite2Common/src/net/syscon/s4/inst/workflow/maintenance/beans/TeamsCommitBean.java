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
public class TeamsCommitBean extends BaseModel implements Serializable{
	private List<Teams> insertList;
	private List<Teams> deleteList;
	private List<Teams> updateList;

	public void setInsertList(List<Teams> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<Teams> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<Teams> deleteList){
		this.deleteList = deleteList;
	}

	public List<Teams> getInsertList(){
		return insertList;
	}

	public List<Teams> getUpdateList(){
		return updateList;
	}

	public List<Teams> getDeleteList(){
		return deleteList;
	}

}
