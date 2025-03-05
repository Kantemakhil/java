/**
 * 
 */
package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class BankChequeRegistersCommitBean
 *
 */
@XmlRootElement
public class BankChequeRegistersCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("insertList")
	private List<BankChequeRegisters> insertList;

	@JsonProperty("deleteList")
	private List<BankChequeRegisters> deleteList;

	@JsonProperty("updateList")
	private List<BankChequeRegisters> updateList;

	/**
	 * Creates new OicHearingsCommitBean class Object
	 */
	public BankChequeRegistersCommitBean() {
		// BankChequeRegistersCommitBean
	}

	public void setInsertList(final List<BankChequeRegisters> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankChequeRegisters> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankChequeRegisters> deleteList) {
		this.deleteList = deleteList;
	}

	public List<BankChequeRegisters> getInsertList() {
		return insertList;
	}

	public List<BankChequeRegisters> getUpdateList() {
		return updateList;
	}

	public List<BankChequeRegisters> getDeleteList() {
		return deleteList;
	}

}
