package net.syscon.s4.im.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class SanctionNoticesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class SanctionNoticesCommitBean extends BaseModel implements Serializable {
	private List<SanctionNotices> insertList;
	private List<SanctionNotices> deleteList;
	private List<SanctionNotices> updateList;

	public void setInsertList(final List<SanctionNotices> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<SanctionNotices> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<SanctionNotices> deleteList){
		this.deleteList = deleteList;
	}

	public List<SanctionNotices> getInsertList(){
		return insertList;
	}

	public List<SanctionNotices> getUpdateList(){
		return updateList;
	}

	public List<SanctionNotices> getDeleteList(){
		return deleteList;
	}

}
