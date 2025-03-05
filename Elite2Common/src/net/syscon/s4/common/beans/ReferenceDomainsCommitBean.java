package net.syscon.s4.common.beans;
import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

import java.util.List;

@XmlRootElement
public class ReferenceDomainsCommitBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private List<ReferenceDomains> insertList;
	private List<ReferenceDomains> deleteList;
	private List<ReferenceDomains> updateList;

	public void setInsertList(List<ReferenceDomains> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ReferenceDomains> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ReferenceDomains> deleteList){
		this.deleteList = deleteList;
	}

	public List<ReferenceDomains> getInsertList(){
		return insertList;
	}

	public List<ReferenceDomains> getUpdateList(){
		return updateList;
	}

	public List<ReferenceDomains> getDeleteList(){
		return deleteList;
	}

}
