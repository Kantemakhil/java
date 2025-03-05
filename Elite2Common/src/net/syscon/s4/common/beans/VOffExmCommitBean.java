package net.syscon.s4.common.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.org.arkin.beans.VOffExmBean;
/**
 * Class VOffExmCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class VOffExmCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("insertList")
	private List<VOffExm> insertList;
	
	@JsonProperty("deleteList")
	private List<VOffExm> deleteList;
	
	@JsonProperty("updateList")
	private List<VOffExm> updateList;

	public void setInsertList(List<VOffExm> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VOffExm> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VOffExm> deleteList){
		this.deleteList = deleteList;
	}

	public List<VOffExm> getInsertList(){
		return insertList;
	}

	public List<VOffExm> getUpdateList(){
		return updateList;
	}

	public List<VOffExm> getDeleteList(){
		return deleteList;
	}

}
