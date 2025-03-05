package net.syscon.s4.inst.visitsmanagement.beans;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;
import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VOffenderAuthorisedVisitorsCommitBean extends BaseModel
 */
@XmlRootElement
public class VOffenderAuthorisedVisitorsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<VOffenderAuthorisedVisitors> insertList;
	private List<VOffenderAuthorisedVisitors> deleteList;
	private List<VOffenderAuthorisedVisitors> updateList;

	public void setInsertList(final List<VOffenderAuthorisedVisitors> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VOffenderAuthorisedVisitors> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VOffenderAuthorisedVisitors> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffenderAuthorisedVisitors> getInsertList(){
		return insertList;
	}

	public List<VOffenderAuthorisedVisitors> getUpdateList(){
		return updateList;
	}

	public List<VOffenderAuthorisedVisitors> getDeleteList(){
		return deleteList;
	}

}
