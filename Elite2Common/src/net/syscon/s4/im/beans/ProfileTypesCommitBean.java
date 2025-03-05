package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import net.syscon.s4.common.beans.BaseModel;
/**
 * Class ProfileTypesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class ProfileTypesCommitBean extends BaseModel{
	private List<ProfileTypes> insertList;
	private List<ProfileTypes> deleteList;
	private List<ProfileTypes> updateList;

	public void setInsertList(List<ProfileTypes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ProfileTypes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ProfileTypes> deleteList){
		this.deleteList = deleteList;
	}

	public List<ProfileTypes> getInsertList(){
		return insertList;
	}

	public List<ProfileTypes> getUpdateList(){
		return updateList;
	}

	public List<ProfileTypes> getDeleteList(){
		return deleteList;
	}

}
