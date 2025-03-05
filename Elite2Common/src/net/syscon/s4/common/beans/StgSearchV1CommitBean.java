package net.syscon.s4.common.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class StgSearchV1CommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<StgSearchV1> insertList;
	private List<StgSearchV1> deleteList;
	private List<StgSearchV1> updateList;

	public void setInsertList(final List<StgSearchV1> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<StgSearchV1> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<StgSearchV1> deleteList){
		this.deleteList = deleteList;
	}

	public List<StgSearchV1> getInsertList(){
		return insertList;
	}

	public List<StgSearchV1> getUpdateList(){
		return updateList;
	}

	public List<StgSearchV1> getDeleteList(){
		return deleteList;
	}

}
