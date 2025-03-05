package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class ProfileCodesCommitBean extends BaseBean
 */
@XmlRootElement
public class ProfileCodesCommitBean extends BaseModel{
	private List<ProfileCodes> insertList;
	private List<ProfileCodes> deleteList;
	private List<ProfileCodes> updateList;

	public void setInsertList(List<ProfileCodes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ProfileCodes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ProfileCodes> deleteList){
		this.deleteList = deleteList;
	}

	public List<ProfileCodes> getInsertList(){
		return insertList;
	}

	public List<ProfileCodes> getUpdateList(){
		return updateList;
	}

	public List<ProfileCodes> getDeleteList(){
		return deleteList;
	}

}
