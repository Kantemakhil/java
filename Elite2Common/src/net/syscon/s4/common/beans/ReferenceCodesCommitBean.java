package net.syscon.s4.common.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReferenceCodesCommitBean extends BaseModel{
	private List<ReferenceCodes> insertList;
	private List<ReferenceCodes> deleteList;
	private List<ReferenceCodes> updateList;

	public void setInsertList(List<ReferenceCodes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<ReferenceCodes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<ReferenceCodes> deleteList){
		this.deleteList = deleteList;
	}

	public List<ReferenceCodes> getInsertList(){
		return insertList;
	}

	public List<ReferenceCodes> getUpdateList(){
		return updateList;
	}

	public List<ReferenceCodes> getDeleteList(){
		return deleteList;
	}

}
