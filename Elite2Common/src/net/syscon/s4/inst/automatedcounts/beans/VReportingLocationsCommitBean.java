package net.syscon.s4.inst.automatedcounts.beans;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VReportingLocationsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class VReportingLocationsCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<VReportingLocations> insertList;
	private List<VReportingLocations> deleteList;
	private List<VReportingLocations> updateList;

	public void setInsertList(final List<VReportingLocations> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<VReportingLocations> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<VReportingLocations> deleteList){
		this.deleteList = deleteList;
	}

	public List<VReportingLocations> getInsertList(){
		return insertList;
	}

	public List<VReportingLocations> getUpdateList(){
		return updateList;
	}

	public List<VReportingLocations> getDeleteList(){
		return deleteList;
	}

}
