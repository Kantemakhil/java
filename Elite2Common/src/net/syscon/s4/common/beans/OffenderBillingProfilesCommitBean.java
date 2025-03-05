package net.syscon.s4.common.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OffenderBillingProfilesCommitBean {
	private List<OffenderBillingProfiles> insertList;
	private List<OffenderBillingProfiles> deleteList;
	private List<OffenderBillingProfiles> updateList;

	public void setInsertList(List<OffenderBillingProfiles> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderBillingProfiles> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderBillingProfiles> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderBillingProfiles> getInsertList(){
		return insertList;
	}

	public List<OffenderBillingProfiles> getUpdateList(){
		return updateList;
	}

	public List<OffenderBillingProfiles> getDeleteList(){
		return deleteList;
	}


}
