package net.syscon.s4.common.beans;
import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

import java.util.List;

@XmlRootElement
public class OmsRequestsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<OmsRequests> insertList;
	private List<OmsRequests> deleteList;
	private List<OmsRequests> updateList;

	public void setInsertList(List<OmsRequests> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OmsRequests> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OmsRequests> deleteList){
		this.deleteList = deleteList;
	}

	public List<OmsRequests> getInsertList(){
		return insertList;
	}

	public List<OmsRequests> getUpdateList(){
		return updateList;
	}

	public List<OmsRequests> getDeleteList(){
		return deleteList;
	}

}
