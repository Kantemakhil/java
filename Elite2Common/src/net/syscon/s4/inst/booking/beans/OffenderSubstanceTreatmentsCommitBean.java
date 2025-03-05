package net.syscon.s4.inst.booking.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderSubstanceTreatmentsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class OffenderSubstanceTreatmentsCommitBean extends BaseModel implements Serializable{
	private List<OffenderSubstanceTreatments> insertList;
	private List<OffenderSubstanceTreatments> deleteList;
	private List<OffenderSubstanceTreatments> updateList;

	public void setInsertList(List<OffenderSubstanceTreatments> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSubstanceTreatments> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSubstanceTreatments> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderSubstanceTreatments> getInsertList(){
		return insertList;
	}

	public List<OffenderSubstanceTreatments> getUpdateList(){
		return updateList;
	}

	public List<OffenderSubstanceTreatments> getDeleteList(){
		return deleteList;
	}

}
