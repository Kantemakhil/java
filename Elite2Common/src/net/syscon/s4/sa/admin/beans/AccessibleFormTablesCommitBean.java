package net.syscon.s4.sa.admin.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AccessibleFormTablesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class AccessibleFormTablesCommitBean extends BaseModel implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	private List<AccessibleFormTables> insertList;
	private List<AccessibleFormTables> deleteList;
	private List<AccessibleFormTables> updateList;

	public void setInsertList(List<AccessibleFormTables> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AccessibleFormTables> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AccessibleFormTables> deleteList){
		this.deleteList = deleteList;
	}

	public List<AccessibleFormTables> getInsertList(){
		return insertList;
	}

	public List<AccessibleFormTables> getUpdateList(){
		return updateList;
	}

	public List<AccessibleFormTables> getDeleteList(){
		return deleteList;
	}

}
