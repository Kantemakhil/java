package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderDemograntImpStatusCommitBean extends BaseBean
 */
@XmlRootElement
public class OffenderDemograntImpStatusCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderDemograntImpStatus> insertList;
	private List<OffenderDemograntImpStatus> deleteList;
	private List<OffenderDemograntImpStatus> updateList;

	public void setInsertList(final List<OffenderDemograntImpStatus> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderDemograntImpStatus> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderDemograntImpStatus> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderDemograntImpStatus> getInsertList(){
		return insertList;
	}

	public List<OffenderDemograntImpStatus> getUpdateList(){
		return updateList;
	}

	public List<OffenderDemograntImpStatus> getDeleteList(){
		return deleteList;
	}

}
