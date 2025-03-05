package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.SecurityThreatGroups;

@XmlRootElement
public class SecurityThreatGroupsCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SecurityThreatGroups> insertList;
	private List<SecurityThreatGroups> deleteList;
	private List<SecurityThreatGroups> updateList;

	public void setInsertList(final List<SecurityThreatGroups> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SecurityThreatGroups> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SecurityThreatGroups> deleteList){
		this.deleteList = deleteList;
	}

	public List<SecurityThreatGroups> getInsertList(){
		return insertList;
	}

	public List<SecurityThreatGroups> getUpdateList(){
		return updateList;
	}

	public List<SecurityThreatGroups> getDeleteList(){
		return deleteList;
	}

}
