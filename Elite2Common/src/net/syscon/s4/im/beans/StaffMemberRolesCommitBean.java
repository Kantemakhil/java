package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.StaffMemberRoles;
/**
 * Class StaffMemberRolesCommitBean extends BaseBean
 */
@XmlRootElement
public class StaffMemberRolesCommitBean extends BaseModel implements Serializable{
	private List<StaffMemberRoles> insertList;
	private List<StaffMemberRoles> deleteList;
	private List<StaffMemberRoles> updateList;

	public void setInsertList(final List<StaffMemberRoles> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StaffMemberRoles> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StaffMemberRoles> deleteList){
		this.deleteList = deleteList;
	}

	public List<StaffMemberRoles> getInsertList(){
		return insertList;
	}

	public List<StaffMemberRoles> getUpdateList(){
		return updateList;
	}

	public List<StaffMemberRoles> getDeleteList(){
		return deleteList;
	}

}
