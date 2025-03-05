package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class OffenceResultCodesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class OffenceResultCodesCommitBean extends BaseModel{
	private List<OffenceResultCodes> insertList;
	private List<OffenceResultCodes> deleteList;
	private List<OffenceResultCodes> updateList;

	public void setInsertList(List<OffenceResultCodes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<OffenceResultCodes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<OffenceResultCodes> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenceResultCodes> getInsertList(){
		return insertList;
	}

	public List<OffenceResultCodes> getUpdateList(){
		return updateList;
	}

	public List<OffenceResultCodes> getDeleteList(){
		return deleteList;
	}

}
