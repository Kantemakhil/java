/**
 * 
 */
package net.syscon.s4.inst.transportation.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;


/**
 * @author Gettha T
 *
 */
@XmlRootElement
public class VLocalTripOffendersCommitBean extends BaseModel implements Serializable {
	private List<VLocalTripOffenders> insertList;
	private List<VLocalTripOffenders> deleteList;
	private List<VLocalTripOffenders> updateList;

	public void setInsertList(List<VLocalTripOffenders> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VLocalTripOffenders> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VLocalTripOffenders> deleteList){
		this.deleteList = deleteList;
	}

	public List<VLocalTripOffenders> getInsertList(){
		return insertList;
	}

	public List<VLocalTripOffenders> getUpdateList(){
		return updateList;
	}

	public List<VLocalTripOffenders> getDeleteList(){
		return deleteList;
	}

}


