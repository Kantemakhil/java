package net.syscon.s4.inst.visitsmanagement.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VOffContactPersonsCommitBean extends BaseBean
 */
@XmlRootElement
public class VOffContactPersonsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VOffContactPersons> insertList;
	private List<VOffContactPersons> deleteList;
	private List<VOffContactPersons> updateList;

	public void setInsertList(final List<VOffContactPersons> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VOffContactPersons> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VOffContactPersons> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffContactPersons> getInsertList(){
		return insertList;
	}

	public List<VOffContactPersons> getUpdateList(){
		return updateList;
	}

	public List<VOffContactPersons> getDeleteList(){
		return deleteList;
	}

}
