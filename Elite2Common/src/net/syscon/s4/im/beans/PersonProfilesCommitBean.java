package net.syscon.s4.im.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.syscon.s4.common.beans.BaseModel;

/**
 * Class PersonProfilesCommitBean extends BaseBean
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonProfilesCommitBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PersonProfiles> insertList;
	private List<PersonProfiles> deleteList;
	private List<PersonProfiles> updateList;

	public void setInsertList(final List<PersonProfiles> insertList) {
		this.insertList = insertList;
	}

	public void setUpdateList(final List<PersonProfiles> updateList) {
		this.updateList = updateList;
	}

	public void setDeleteList(final List<PersonProfiles> deleteList) {
		this.deleteList = deleteList;
	}

	public List<PersonProfiles> getInsertList() {
		return insertList;
	}

	public List<PersonProfiles> getUpdateList() {
		return updateList;
	}

	public List<PersonProfiles> getDeleteList() {
		return deleteList;
	}

}
