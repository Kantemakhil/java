package net.syscon.s4.inst.visitsmanagement.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ImagesCommitBean;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.OffenderContactPersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.OidviresRepository;
import net.syscon.s4.inst.visitsmanagement.OidviresService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictionsCommitBean;

@Service
public class OidviresServiceImpl extends BaseBusiness implements OidviresService{

@Autowired
private OidviresRepository oidviresRepository;
@Autowired
private EliteDateService dateService;
/**
 * Logger object used to print the log in the file
 */

/**
 *Creates new OidviresServiceImpl class Object 
 */
public OidviresServiceImpl(){
}

/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * 
*/
	public List<OffenderRestrictions> offBkgOnCheckDeleteMaster(final OffenderRestrictions paramBean) {
		
			List<OffenderRestrictions> offenderRestrictions = oidviresRepository.offBkgOnCheckDeleteMaster(paramBean);
		 
return offenderRestrictions;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * 
*/
	public List<TagImages> offAuthVisitorsOnCheckDeleteMaster(final TagImages paramBean) {
			List<TagImages> tagImagesList = oidviresRepository.offAuthVisitorsOnCheckDeleteMaster(paramBean);
return tagImagesList;
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * 
*/
	public List<Images> offAuthVisitOffOnCheckDeleteMaster(final Images paramBean) {
			List<Images> imagesList = oidviresRepository.offAuthVisitOffOnCheckDeleteMaster(paramBean);
return imagesList;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * 
 */
public List<OffenderRestrictions> offVisitRestExecuteQuery(final OffenderRestrictions searchRecord) {
		return oidviresRepository.offVisitRestExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_VISIT_REST
 *
 * 
 */
@Transactional
public Integer offVisitRestCommit(final OffenderRestrictionsCommitBean commitBean) {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for(OffenderRestrictions obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					obj.setUsername(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.offVisitRestInsertOffenderRestrictions(commitBean.getInsertList());
			}
			//updateRecords
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for(OffenderRestrictions obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.offVisitRestUpdateOffenderRestrictions(commitBean.getUpdateList());
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * 
 */
public List<OffenderContactPersons> offAuthVisitorsExecuteQuery(final OffenderContactPersons searchRecord) {
	List<OffenderContactPersons> resultData = oidviresRepository.offAuthVisitorsExecuteQuery(searchRecord);
		resultData.forEach(result -> {
			if(result.getBirthDate() != null) {
				result.setAge(Integer.valueOf(dateService.calculateAge(result.getBirthDate())+ ""));
			}
		});
		return resultData;

}

/**Insert the records from database table
 *
 * @param lstOFF_AUTH_VISITORS
 *
 * 
 */
@Transactional
public Integer offAuthVisitorsCommit(final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for(OffenderContactPersons obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.offAuthVisitorsInsertOffenderContactPersons(commitBean.getInsertList());
			}
			//updateRecords
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for(OffenderContactPersons obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.offAuthVisitorsUpdateOffenderContactPersons(commitBean.getUpdateList());
			}
			//deleteRecords
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidviresRepository.offAuthVisitorsDeleteOffenderContactPersons(commitBean.getDeleteList());
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * 
 */
public List<Images> imageVisitExecuteQuery(final Long imageObjectId, final String type,String userName) {
		return oidviresRepository.imageVisitExecuteQuery(imageObjectId,type,userName);

}

/**Insert the records from database table
 *
 * @param lstIMAGE_VISIT
 *
 * 
 */
@Transactional
public Integer imageVisitCommit(final ImagesCommitBean commitBean) {
		int liReturn = 0;
			//deleteRecords
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidviresRepository.imageVisitDeleteImages(commitBean.getDeleteList());
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * 
 */
public List<OffenderContactPersons> offAuthVisitOffExecuteQuery(final OffenderContactPersons searchRecord) {
		return oidviresRepository.offAuthVisitorsExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstOFF_AUTH_VISIT_OFF
 *
 * 
 */
@Transactional
public Integer offAuthVisitOffCommit(final OffenderContactPersonsCommitBean commitBean) {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for (OffenderContactPersons bean : commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.oidviresOffauthvisitoffInsertOffenderContactPersons(commitBean.getInsertList());
			}
			//updateRecords
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for (OffenderContactPersons bean : commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oidviresRepository.oidviresOffauthvisitoffUpdateOffenderContactPersons(commitBean.getUpdateList());
			}
			//deleteRecords
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidviresRepository.oidviresOffauthvisitoffDeleteOffenderContactPersons(commitBean.getDeleteList());
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * 
 */
public List<Images> imagesOffExecuteQuery(final Long imageObjectId, String type,String userName) {
		return oidviresRepository.imageVisitExecuteQuery(imageObjectId,type,userName);

}

/**Insert the records from database table
 *
 * @param lstIMAGES_OFF
 *
 * 
 */
@Transactional
public Integer imagesOffCommit(final ImagesCommitBean commitBean) {
		int liReturn = 0;
			//insertRecords
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				liReturn = oidviresRepository.imageVisitDeleteImages(commitBean.getInsertList());
			}
			//updateRecords
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				liReturn = oidviresRepository.imageVisitDeleteImages(commitBean.getUpdateList());
			}
			//deleteRecords
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				liReturn = oidviresRepository.imageVisitDeleteImages(commitBean.getDeleteList());
			}
			return liReturn;
}
/**
 * This method is used to execute a record group
 *
 *
*/
public List<ReferenceCodes> rgAuthPriRelationshipTypeRecordGroup(final String contactType) {
		return oidviresRepository.rgAuthPriRelationshipTypeRecordGroup(contactType);

}
/**
 * This method is used to execute a record group
 *
 *
*/
public List<ReferenceCodes> rgAuthVisRelationshipTypeRecordGroup() {
		return oidviresRepository.rgAuthVisRelationshipTypeRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *
*/
public List<ReferenceCodes> rgAuthVisContactTypeRecordGroup() {
		return oidviresRepository.rgAuthVisContactTypeRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *
*/
public List<StaffMembers> rgStaffIdRecordGroup(final String agyLocId) {
		List<StaffMembers> resultList = oidviresRepository.rgStaffIdRecordGroup(agyLocId);
		resultList.forEach(result -> {
//			if(result.getLastName().equals("N")) {
//				result.setCanDisplay(false);
//			}
		});
		return resultList;

}
/**
 * This method is used to execute a record group
 *
 *
*/
public List<ReferenceCodes> rgOffRestrictionTypeRecordGroup() {
		return oidviresRepository.rgOffRestrictionTypeRecordGroup();

}

@Override
public List<OffenderContactPersons> offVisitingExecuteQuery(final OffenderContactPersons objOffenderContactPersons)
		{
	return oidviresRepository.offVisitingExecuteQuery(objOffenderContactPersons);
}

@Override
public Integer oidviresFindTagVisitsGetStaffId(String userName) {
	return oidviresRepository.oidviresFindTagVisitsGetStaffId(userName);
}

@Override
public List<String> oidviresIsOffenderBanRestriction(final Long offenderBookId) {
	return oidviresRepository.oidviresIsOffenderBanRestriction(offenderBookId);
}

@Override
public List<ReferenceCodes> oidviresIsPersonBanRestriction(Long personId) {
	return oidviresRepository.oidviresIsPersonBanRestriction(personId);
}

	@Override
	public String chkNaBetweenOffenders(final Long glbOffBkgId, final Long visOffBkgId) {
		return oidviresRepository.chkNaBetweenOffenders(glbOffBkgId, visOffBkgId);
	}

}