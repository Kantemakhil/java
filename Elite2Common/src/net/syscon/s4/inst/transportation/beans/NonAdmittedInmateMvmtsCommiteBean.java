package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class NonAdmittedInmateMvmtsCommiteBean extends BaseBean
 * 
 * @author Vrnda Software Technologies
 * @version 1.0
 */

public class NonAdmittedInmateMvmtsCommiteBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<NonAdmittedInmateMvmts> insertList;
	private List<NonAdmittedInmateMvmts> deleteList;
	private List<NonAdmittedInmateMvmts> updateList;

	public void setInsertList(List<NonAdmittedInmateMvmts> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(List<NonAdmittedInmateMvmts> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(List<NonAdmittedInmateMvmts> deleteList) {
		this.deleteList = deleteList;
	}

	public List<NonAdmittedInmateMvmts> getInsertList() {
		return insertList;
	}

	public List<NonAdmittedInmateMvmts> getUpdateList() {
		return updateList;
	}

	public List<NonAdmittedInmateMvmts> getDeleteList() {
		return deleteList;
	}

}
