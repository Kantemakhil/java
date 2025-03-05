package net.syscon.s4.inst.schedules.bean;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VAddressUsagesCommitBean extends BaseBean
 */
@XmlRootElement
public class VAddressUsagesCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VAddressUsages> insertList;
	private List<VAddressUsages> deleteList;
	private List<VAddressUsages> updateList;

	public void setInsertList(List<VAddressUsages> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VAddressUsages> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VAddressUsages> deleteList){
		this.deleteList = deleteList;
	}

	public List<VAddressUsages> getInsertList(){
		return insertList;
	}

	public List<VAddressUsages> getUpdateList(){
		return updateList;
	}

	public List<VAddressUsages> getDeleteList(){
		return deleteList;
	}

}
