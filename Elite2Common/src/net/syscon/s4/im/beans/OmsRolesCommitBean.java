package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OmsRolesCommitBean extends BaseBean
 */
@XmlRootElement
public class OmsRolesCommitBean extends BaseModel implements Serializable{
	private List<OmsRoles> insertList;
	private List<OmsRoles> deleteList;
	private List<OmsRoles> updateList;

	public void setInsertList(List<OmsRoles> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OmsRoles> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OmsRoles> deleteList){
		this.deleteList = deleteList;
	}

	public List<OmsRoles> getInsertList(){
		return insertList;
	}

	public List<OmsRoles> getUpdateList(){
		return updateList;
	}

	public List<OmsRoles> getDeleteList(){
		return deleteList;
	}

}
