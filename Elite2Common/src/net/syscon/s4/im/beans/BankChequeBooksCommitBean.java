package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;

@XmlRootElement
public class BankChequeBooksCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BankChequeBooks> insertList;
	private List<BankChequeBooks> deleteList;
	private List<BankChequeBooks> updateList;

	public void setInsertList(final List<BankChequeBooks> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankChequeBooks> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankChequeBooks> deleteList){
		this.deleteList = deleteList;
	}

	public List<BankChequeBooks> getInsertList(){
		return insertList;
	}

	public List<BankChequeBooks> getUpdateList(){
		return updateList;
	}

	public List<BankChequeBooks> getDeleteList(){
		return deleteList;
	}

}
