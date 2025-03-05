package net.syscon.s4.common.beans;
import java.util.List;

import net.syscon.s4.im.beans.TempLivingUnitProfiles;

/**
 * Class TempLivingUnitProfilesCommitBean extends BaseBean
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
public class TempLivingUnitProfilesCommitBean extends BaseModel{
	private List<TempLivingUnitProfiles> insertList;
	private List<TempLivingUnitProfiles> deleteList;
	private List<TempLivingUnitProfiles> updateList;

	public void setInsertList(List<TempLivingUnitProfiles> insertList){
		this.insertList = insertList;
	}

	public void setUpdateList(List<TempLivingUnitProfiles> updateList){
		this.updateList = updateList;
	}

	public void setDeleteList(List<TempLivingUnitProfiles> deleteList){
		this.deleteList = deleteList;
	}

	public List<TempLivingUnitProfiles> getInsertList(){
		return insertList;
	}

	public List<TempLivingUnitProfiles> getUpdateList(){
		return updateList;
	}

	public List<TempLivingUnitProfiles> getDeleteList(){
		return deleteList;
	}

}
