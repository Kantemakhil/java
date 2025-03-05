package net.syscon.s4.inst.schedules.bean;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VPhonesCommitBean extends BaseModel
 */
@XmlRootElement
public class VPhonesCommitBean extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<VPhones> insertList;
	private List<VPhones> deleteList;
	private List<VPhones> updateList;

	public void setInsertList(List<VPhones> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VPhones> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VPhones> deleteList){
		this.deleteList = deleteList;
	}

	public List<VPhones> getInsertList(){
		return insertList;
	}

	public List<VPhones> getUpdateList(){
		return updateList;
	}

	public List<VPhones> getDeleteList(){
		return deleteList;
	}

}
