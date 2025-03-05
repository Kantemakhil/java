package net.syscon.s4.im.beans;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;


@XmlRootElement
public class OffenderBookingAgyLocsCommitBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OffenderBookingAgyLocs> insertList;
	private List<OffenderBookingAgyLocs> deleteList;
	private List<OffenderBookingAgyLocs> updateList;

	public void setInsertList(final List<OffenderBookingAgyLocs> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(final List<OffenderBookingAgyLocs> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(final List<OffenderBookingAgyLocs> deleteList){
		this.deleteList = deleteList;
	}

	public List<OffenderBookingAgyLocs> getInsertList(){
		return insertList;
	}

	public List<OffenderBookingAgyLocs> getUpdateList(){
		return updateList;
	}

	public List<OffenderBookingAgyLocs> getDeleteList(){
		return deleteList;
	}

}
