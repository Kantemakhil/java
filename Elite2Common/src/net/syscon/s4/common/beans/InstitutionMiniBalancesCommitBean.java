package net.syscon.s4.common.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class InstitutionMiniBalancesCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<InstitutionMiniBalances> insertList;
	private List<InstitutionMiniBalances> deleteList;
	private List<InstitutionMiniBalances> updateList;

	public void setInsertList(List<InstitutionMiniBalances> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<InstitutionMiniBalances> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<InstitutionMiniBalances> deleteList){
		this.deleteList = deleteList;
	}

	public List<InstitutionMiniBalances> getInsertList(){
		return insertList;
	}

	public List<InstitutionMiniBalances> getUpdateList(){
		return updateList;
	}

	public List<InstitutionMiniBalances> getDeleteList(){
		return deleteList;
	}

}
