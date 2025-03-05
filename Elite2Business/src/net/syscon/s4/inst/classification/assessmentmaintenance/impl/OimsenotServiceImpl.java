package net.syscon.s4.inst.classification.assessmentmaintenance.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.AssessSectionNotificationsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimsenotRepository;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimsenotService;
import net.syscon.s4.inst.classification.beans.Assessments;
/**
 * Class OimsenotServiceImpl */
@Service
public class OimsenotServiceImpl extends BaseBusiness implements OimsenotService{

@Autowired
private OimsenotRepository oimsenotRepository;





/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public AssessSectionNotifications assessSectionNotificationsPreInsert(final AssessSectionNotifications paramBean)  {
		return  oimsenotRepository.assessSectionNotificationsPreInsert(paramBean);
		 
     
}


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public Assessments assessSectionNotificationsPostQuery(final AssessSectionNotifications paramBean)  {
		return oimsenotRepository.assessSectionNotificationsPostQuery(paramBean);
			
}


/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<Assessments> assessmentsExecuteQuery(final Assessments searchRecord)  {
		return oimsenotRepository.assessmentsExecuteQuery(searchRecord);

}
	
/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */

public List<AssessSectionNotifications> assessSectionNotificationsExecuteQuery(final AssessSectionNotifications searchRecord)  {

	 List<AssessSectionNotifications> list = new ArrayList<>();
		 final Assessments assessments = new Assessments();
		 assessments.setAssessmentId(searchRecord.getAssessmentId());
		  list= oimsenotRepository.assessSectionNotificationsExecuteQuery(searchRecord);
	
		  
 for(final AssessSectionNotifications notfifctns :list) {
	 
	 if(notfifctns.getNextAssessmentId()!= null) {
		  final Assessments  assmts = oimsenotRepository.assessSectionNotificationsPostQuery(notfifctns);
			 notfifctns.setNextAssCode(assmts.getAssessmentCode());
		  
	 }
	
		  }
			 return list;

}

/**Insert the records from database table
 *
 * @param lstASSESS_SECTION_NOTIFICATIONS
 *8
 * @throws SQLException
 */

@Transactional
public Integer assessSectionNotificationsCommit(final AssessSectionNotificationsCommitBean commitBean)  {
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& !commitBean.getInsertList().isEmpty()) {
	
				final List<AssessSectionNotifications> notficationList =  commitBean.getInsertList();
				for (AssessSectionNotifications obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				for(int i =0; i< notficationList.size();i++) {
					
					final AssessSectionNotifications notfication = notficationList.get(i);
					final AssessSectionNotifications notfnSeq  = oimsenotRepository. assessSectionNotificationsPreInsert(notfication);
					if(notfication.getScoreSeq()==0) {
						notfication.setScoreSeq(notfnSeq.getScoreSeq()+1);
					}
					notfication.setScoreSeq(notfnSeq.getScoreSeq()+i+1);
					if ("true".equals(notfication.getActiveFlag())) {
						notfication.setActiveFlag("Y");
					}
					else  if("false".equals(notfication.getActiveFlag())){
						notfication.setActiveFlag("N");
					}
					if("true".equals(notfication.getNextSectionFlag()) || "Y".equals(notfication.getNextSectionFlag()))  {
					
					
						notfication.setNextSectionFlag("Y");
					}
					else {
						notfication.setNextSectionFlag("N");
					}
				}
				liReturn = oimsenotRepository.assessSectionNotificationsInsertAssessSectionNotifications(commitBean.getInsertList());
               return liReturn;
			}
			if (commitBean.getUpdateList() != null	&& !commitBean.getUpdateList().isEmpty()) {
				
				for(final AssessSectionNotifications notfication :commitBean.getUpdateList()) {
					notfication.setModifyUserId(commitBean.getCreateUserId());
					if ("true".equals(notfication.getActiveFlag())) {
						notfication.setActiveFlag("Y");
					}
					else  if("false".equals(notfication.getActiveFlag())){
						notfication.setActiveFlag("N");
					}
					if("true".equals(notfication.getNextSectionFlag()) || "Y".equals(notfication.getNextSectionFlag()))  {					
						notfication.setNextSectionFlag("Y");
					}
					else {
						notfication.setNextSectionFlag("N");
					}
				}
				liReturn = oimsenotRepository.assessSectionNotificationsUpdateAssessSectionNotifications(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null	&& !commitBean.getDeleteList().isEmpty()) {
				for(final AssessSectionNotifications notfication :commitBean.getDeleteList()) {
					notfication.setModifyUserId(commitBean.getCreateUserId());
					if("true".equals(notfication.getActiveFlag()))  {
						notfication.setActiveFlag("Y");
					}
					else  if("false".equals(notfication.getActiveFlag())){
						notfication.setActiveFlag("N");
					}
				}
				liReturn = oimsenotRepository.assessSectionNotificationsDeleteAssessSectionNotifications(commitBean.getDeleteList());
			}
			return liReturn;
}


/**
 * This method is used to execute a record group
 *
 *@throws SQLException  
*/


public List<Assessments> cgfkNextSectionRecordGroup(final String parentField1)  {	
	 if(parentField1.contains("-")){
			final String[] returnArray = parentField1.split("-");
			return   oimsenotRepository.cgfkNextSectionRecordGroup(returnArray[0],returnArray[1]);
	 }
	 else{
		 return  new ArrayList<>();
}

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<Assessments> cgfkSectionCodeRecordGroup(final Long assessmentId)  {
		return oimsenotRepository.cgfkSectionCodeRecordGroup(assessmentId);
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkScoreTypeRecordGroup()  {
		return oimsenotRepository.cgfkScoreTypeRecordGroup();
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkNextSectionFlagRecordGroup()  {
	
	return  oimsenotRepository.cgfkNextSectionFlagRecordGroup();		

}
}