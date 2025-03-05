package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.ProfileCategory;
/**
 * Class ProfileCategoriesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class ProfileCategoriesCommitBean extends BaseModel{
	private List<ProfileCategory> insertList;
	private List<ProfileCategory> deleteList;
	private List<ProfileCategory> updateList;

	public void setInsertList(List<ProfileCategory> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ProfileCategory> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ProfileCategory> deleteList){
		this.deleteList = deleteList;
	}

	public List<ProfileCategory> getInsertList(){
		return insertList;
	}

	public List<ProfileCategory> getUpdateList(){
		return updateList;
	}

	public List<ProfileCategory> getDeleteList(){
		return deleteList;
	}

}
