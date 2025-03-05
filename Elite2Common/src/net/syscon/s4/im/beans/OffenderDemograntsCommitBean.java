package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderDemograntsCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderDemograntsCommitBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderDemogrants> insertList;
	private List<OffenderDemogrants> deleteList;
	private List<OffenderDemogrants> updateList;

	public void setInsertList(final List<OffenderDemogrants> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderDemogrants> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderDemogrants> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderDemogrants> getInsertList(){
		return insertList;
	}

	public List<OffenderDemogrants> getUpdateList(){
		return updateList;
	}

	public List<OffenderDemogrants> getDeleteList(){
		return deleteList;
	}

}
