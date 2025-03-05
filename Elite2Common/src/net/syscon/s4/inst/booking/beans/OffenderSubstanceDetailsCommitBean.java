package net.syscon.s4.inst.booking.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenderSubstanceDetailsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class OffenderSubstanceDetailsCommitBean extends BaseModel implements Serializable{
	private List<OffenderSubstanceDetails> insertList;
	private List<OffenderSubstanceDetails> deleteList;
	private List<OffenderSubstanceDetails> updateList;

	public void setInsertList(List<OffenderSubstanceDetails> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenderSubstanceDetails> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenderSubstanceDetails> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderSubstanceDetails> getInsertList(){
		return insertList;
	}

	public List<OffenderSubstanceDetails> getUpdateList(){
		return updateList;
	}

	public List<OffenderSubstanceDetails> getDeleteList(){
		return deleteList;
	}

}
