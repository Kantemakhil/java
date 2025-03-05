package net.syscon.s4.inst.accreditedprograms.beans;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import net.syscon.s4.common.beans.BaseModel;
/**
 * Class VCoursePhaseOfferingsCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@XmlRootElement
public class VCoursePhaseOfferingsCommitBean extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VCoursePhaseOfferings> insertList;
	private List<VCoursePhaseOfferings> deleteList;
	private List<VCoursePhaseOfferings> updateList;

	public void setInsertList(List<VCoursePhaseOfferings> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<VCoursePhaseOfferings> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<VCoursePhaseOfferings> deleteList){
		this.deleteList = deleteList;
	}

	public List<VCoursePhaseOfferings> getInsertList(){
		return insertList;
	}

	public List<VCoursePhaseOfferings> getUpdateList(){
		return updateList;
	}

	public List<VCoursePhaseOfferings> getDeleteList(){
		return deleteList;
	}

}
