package net.syscon.s4.common.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OffenderEducationsCommitBean extends BaseModel{
	private List<OffenderEducations> insertList;
	private List<OffenderEducations> deleteList;
	private List<OffenderEducations> updateList;

	public void setInsertList(List<OffenderEducations> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderEducations> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderEducations> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderEducations> getInsertList(){
		return insertList;
	}

	public List<OffenderEducations> getUpdateList(){
		return updateList;
	}

	public List<OffenderEducations> getDeleteList(){
		return deleteList;
	}

}
