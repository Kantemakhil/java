package net.syscon.s4.inst.automatedcounts.beans;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
/**
 * Class AgencyCountTypesCommitBean extends BaseBean
 */

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.im.beans.AgencyReportingLocs;
@XmlRootElement
public class AgencyCountTypesCommitBean extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<AgencyCountTypes> insertList;
	private List<AgencyCountTypes> deleteList;
	private List<AgencyCountTypes> updateList;
	@JsonProperty("reportInsertList")
	private List<AgencyReportingLocs> reportInsertList;

	public void setInsertList(final List<AgencyCountTypes> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<AgencyCountTypes> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<AgencyCountTypes> deleteList){
		this.deleteList = deleteList;
	}

	public List<AgencyCountTypes> getInsertList(){
		return insertList;
	}

	public List<AgencyCountTypes> getUpdateList(){
		return updateList;
	}

	public List<AgencyCountTypes> getDeleteList(){
		return deleteList;
	}

	public List<AgencyReportingLocs> getReportInsertList() {
		return reportInsertList;
	}

	public void setReportInsertList(List<AgencyReportingLocs> reportInsertList) {
		this.reportInsertList = reportInsertList;
	}

}
