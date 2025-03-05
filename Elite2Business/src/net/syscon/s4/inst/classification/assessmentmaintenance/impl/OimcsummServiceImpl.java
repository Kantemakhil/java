package net.syscon.s4.inst.classification.assessmentmaintenance.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimcsummRepository;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimcsummService;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssOffNeedsCommitBean;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VAssTreatProtsCommitBean;
import net.syscon.s4.triggers.AonVrIudService;
/**
 * Class OimcsummServiceImpl */
@Service
public class OimcsummServiceImpl extends BaseBusiness implements OimcsummService{

@Autowired
private OimcsummRepository oimcsummRepository;
@Autowired
private AonVrIudService aonVrIudService;


/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<Assessments> assessmentsExecuteQuery(Assessments searchRecord)  {
		return oimcsummRepository.assessmentsExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstASSESSMENTS
 *
 * @throws SQLException
 */
@Transactional
public Integer assessmentsCommit(AssessmentsCommitBean commitBean)  {
		int liReturn = 0;
		return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<VAssOffNeeds> vAssOffNeedsExecuteQuery(VAssOffNeeds searchRecord)  {
		return oimcsummRepository.vAssOffNeedsExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstV_ASS_OFF_NEEDS
 *
 * @throws SQLException
 */
@Transactional
public Integer vAssOffNeedsCommit(VAssOffNeedsCommitBean commitBean)  {
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for(VAssOffNeeds data:commitBean.getInsertList()) {
					data.setCreateUserId(commitBean.getCreateUserId());
					net.syscon.s4.triggers.VAssOffNeeds offNeeds= new net.syscon.s4.triggers.VAssOffNeeds();
					BeanUtils.copyProperties(data, offNeeds);
					offNeeds.setCreateUserId(commitBean.getCreateUserId());
					liReturn = aonVrIudService.aonVrIud(offNeeds,ApplicationConstants.INSERTING);
					}
			}
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(e->e.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oimcsummRepository.vAssOffNeedsUpdateVAssOffNeeds(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = oimcsummRepository.vAssOffNeedsDeleteVAssOffNeeds(commitBean.getDeleteList());
			}
			return liReturn;
}

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<VAssTreatProts> vAssTreatProtsExecuteQuery(VAssTreatProts searchRecord)  {
	List<VAssTreatProts> returnList=new ArrayList<VAssTreatProts>();
		 returnList = oimcsummRepository.vAssTreatProtsExecuteQuery(searchRecord);
		 for (VAssTreatProts vAssTreatProts : returnList) {
			 if(vAssTreatProts.getProgramId()!=null) {			 
				 vAssTreatProts.setProgramDesc(String.valueOf(vAssTreatProts.getProgramId()));
			 }
		}
		return returnList;

}

/**Insert the records from database table
 *
 * @param lstV_ASS_TREAT_PROTS
 *
 * @throws SQLException
 */
@Transactional
public Integer vAssTreatProtsCommit(VAssTreatProtsCommitBean commitBean)  {
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for(VAssTreatProts obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oimcsummRepository.vAssTreatProtsInsertVAssTreatProts(commitBean.getInsertList());

			}
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for(VAssTreatProts obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oimcsummRepository.vAssTreatProtsUpdateVAssTreatProts(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = oimcsummRepository.vAssTreatProtsDeleteVAssTreatProts(commitBean.getDeleteList());
			}
			return liReturn;
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> rgCaseworkRecordGroup()  {
		return oimcsummRepository.rgCaseworkRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ProgramServices> rgProgramIdRecordGroup(String programCategory)  {
		return oimcsummRepository.rgProgramIdRecordGroup(programCategory);

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> rgCaseplanAssRecordGroup()  {
		return oimcsummRepository.rgCaseplanAssRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<Assessments> cgfkNextSectionRecordGroup()  {
		return oimcsummRepository.cgfkNextSectionRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkScoreTypeRecordGroup()  {
		return oimcsummRepository.cgfkScoreTypeRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> rgPrgCategoryRecordGroup()  {
		return oimcsummRepository.rgPrgCategoryRecordGroup();

}

@Override
public List<VAssOffNeeds> assessmentsOnCheckDeleteMaster(VAssOffNeeds paramBean) {
	return null;
}

@Override
public List<VAssTreatProts> vAssOffNeedsOnCheckDeleteMaster(VAssTreatProts paramBean) {
	return null;
}

@Override
public Long vAssOffNeedsPreInsert() {
	return oimcsummRepository.vAssOffNeedsPreInsert();
}

@Override
public Long vAssTreatProtsPreInsert() {
	return oimcsummRepository.vAssTreatProtsPreInsert();
}


public List<Assessments> cgfkSectionCodeRecordGroup(final Long assessmentId)  {
	return oimcsummRepository.cgfkSectionCodeRecordGroup(assessmentId);

}
}