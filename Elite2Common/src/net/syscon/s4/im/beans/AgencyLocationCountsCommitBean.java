package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AgencyLocationCountsCommitBean extends BaseModel
 */
@XmlRootElement
public class AgencyLocationCountsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AgencyLocationCounts> insertList;
	private List<AgencyLocationCounts> deleteList;
	private List<AgencyLocationCounts> updateList;

	public void setInsertList(final List<AgencyLocationCounts> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyLocationCounts> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyLocationCounts> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgencyLocationCounts> getInsertList(){
		return insertList;
	}

	public List<AgencyLocationCounts> getUpdateList(){
		return updateList;
	}

	public List<AgencyLocationCounts> getDeleteList(){
		return deleteList;
	}

}
