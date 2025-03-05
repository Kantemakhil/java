package net.syscon.s4.inst.schedules.bean;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VOffExtMovementsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class VOffExtMovementsCommitBean  extends BaseModel implements Serializable{
	private List<VOffExtMovements> insertList;
	private List<VOffExtMovements> deleteList;
	private List<VOffExtMovements> updateList;

	public void setInsertList(List<VOffExtMovements> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VOffExtMovements> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VOffExtMovements> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffExtMovements> getInsertList(){
		return insertList;
	}

	public List<VOffExtMovements> getUpdateList(){
		return updateList;
	}

	public List<VOffExtMovements> getDeleteList(){
		return deleteList;
	}

}
