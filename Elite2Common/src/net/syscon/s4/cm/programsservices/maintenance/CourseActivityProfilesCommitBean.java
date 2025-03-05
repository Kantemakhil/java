package net.syscon.s4.cm.programsservices.maintenance;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class CourseActivityProfilesCommitBean extends BaseBean
 */
@XmlRootElement
public class CourseActivityProfilesCommitBean extends BaseModel{
	private List<CourseActivityProfiles> insertList;
	private List<CourseActivityProfiles> deleteList;
	private List<CourseActivityProfiles> updateList;
	private String sealFlag;

	public void setInsertList(List<CourseActivityProfiles> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<CourseActivityProfiles> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<CourseActivityProfiles> deleteList){
		this.deleteList = deleteList;
	}

	public List<CourseActivityProfiles> getInsertList(){
		return insertList;
	}

	public List<CourseActivityProfiles> getUpdateList(){
		return updateList;
	}

	public List<CourseActivityProfiles> getDeleteList(){
		return deleteList;
	}
	
	public String getSealFlag() {
		return this.sealFlag;
	}

	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

}
