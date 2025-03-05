package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.inmate.beans.BankChequeRegisters;

@XmlRootElement
public class BankChequeRegistersCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BankChequeRegisters> insertList;
	private List<BankChequeRegisters> deleteList;
	private List<BankChequeRegisters> updateList;

	public void setInsertList(final List<BankChequeRegisters> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankChequeRegisters> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankChequeRegisters> deleteList){
		this.deleteList = deleteList;
	}

	public List<BankChequeRegisters> getInsertList(){
		return insertList;
	}

	public List<BankChequeRegisters> getUpdateList(){
		return updateList;
	}

	public List<BankChequeRegisters> getDeleteList(){
		return deleteList;
	}

}
