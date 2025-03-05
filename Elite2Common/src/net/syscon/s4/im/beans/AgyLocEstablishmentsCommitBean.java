package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class AgyLocEstablishmentsCommitBean 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgyLocEstablishmentsCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AgyLocEstablishments> insertList;
	private List<AgyLocEstablishments> deleteList;
	private List<AgyLocEstablishments> updateList;

	public void setInsertList(List<AgyLocEstablishments> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<AgyLocEstablishments> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<AgyLocEstablishments> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgyLocEstablishments> getInsertList(){
		return insertList;
	}

	public List<AgyLocEstablishments> getUpdateList(){
		return updateList;
	}

	public List<AgyLocEstablishments> getDeleteList(){
		return deleteList;
	}

}
