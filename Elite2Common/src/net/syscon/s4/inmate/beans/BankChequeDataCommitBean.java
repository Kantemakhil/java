package net.syscon.s4.inmate.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.validators.GlobalValidation;
import net.syscon.s4.im.beans.BankChequeData;

@JsonIgnoreProperties(ignoreUnknown = true)
@GlobalValidation(message = "atleast.one.mandatory")
public class BankChequeDataCommitBean extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("insertList")
	private List<BankChequeData> insertList;

	@JsonProperty("deleteList")
	private List<BankChequeData> deleteList;

	@JsonProperty("updateList")
	private List<BankChequeData> updateList;

	/**
	 * Creates new BankChequeBooksCommitBean class Object
	 */
	public BankChequeDataCommitBean() {
		// BankChequeBooksCommitBean
	}

	public void setInsertList(final List<BankChequeData> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<BankChequeData> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<BankChequeData> deleteList) {
		this.deleteList = deleteList;
	}

	public List<BankChequeData> getInsertList() {
		return insertList;
	}

	public List<BankChequeData> getUpdateList() {
		return updateList;
	}

	public List<BankChequeData> getDeleteList() {
		return deleteList;
	}

}
