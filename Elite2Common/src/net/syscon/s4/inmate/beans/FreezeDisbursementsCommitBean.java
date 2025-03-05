package net.syscon.s4.inmate.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class FreezeDisbursementsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class FreezeDisbursementsCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FreezeDisbursements> insertList;
	private List<FreezeDisbursements> deleteList;
	private List<FreezeDisbursements> updateList;

	public void setInsertList(final List<FreezeDisbursements> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<FreezeDisbursements> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<FreezeDisbursements> deleteList){
		this.deleteList = deleteList;
	}

	public List<FreezeDisbursements> getInsertList(){
		return insertList;
	}

	public List<FreezeDisbursements> getUpdateList(){
		return updateList;
	}

	public List<FreezeDisbursements> getDeleteList(){
		return deleteList;
	}

}
