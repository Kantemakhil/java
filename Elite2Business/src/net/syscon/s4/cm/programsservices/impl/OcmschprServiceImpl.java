package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcmschprRepository;
import net.syscon.s4.cm.programsservices.OcmschprService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;
import net.syscon.s4.iwp.beans.OcmschprCommitBean;
import net.syscon.s4.iwp.beans.VAcpSchedules;
import net.syscon.s4.iwp.beans.VAcpSchedulesCommitBean;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.pkgs.tag_service_sched.TagServiceSchedService;

/**
 * Class OcmschprServiceImpl
 */
@Service
public class OcmschprServiceImpl extends BaseBusiness implements OcmschprService {
	
	private static Logger logger = LogManager.getLogger(OcmschprServiceImpl.class.getName());

	@Autowired
	private OcmschprRepository ocmschprRepository;
	
	@Autowired
	private TagServiceSchedService tagServiceSchedService;
	
	@Autowired
	private TagProgrammesService tagProgrammesService;
	
	@Autowired
	private TagServiceService tagServiceService;

	
	
	public List<CourseActivities> crsActExecuteQuery(CourseActivities searchRecord) {
		List<CourseActivities> crsActData = ocmschprRepository.crsActExecuteQuery(searchRecord);
		if(crsActData != null && crsActData.size()>0) {
			crsActData.forEach(data->{
				CourseActivities actData=ocmschprRepository.crsActPostQuery(data);
				if(actData != null) {
					data.setServiceDescription(actData.getServiceDescription());
					data.setCode(actData.getOfferingCode());
					data.setDescription(actData.getOfferingDescription());
					data.setOfferingStartDate(actData.getOfferingStartDate());
					data.setOfferingEndDate(actData.getOfferingEndDate());
					data.setServiceCategory(actData.getServiceCategory());
					data.setActiveFlag(actData.getActiveFlag());
					data.setActualSessions((ocmschprRepository.getActualSessions(data)));	
				}
				
				if(data.getCourseClass() != null  && data.getCourseClass().equals("CRS_PH")) {
					List<ProgramServices> ps=ocmschprRepository.getProgramServices(data);
					if(ps != null && ps.size() >0){
						data.setPhaseDescription(ps.get(0).getDescription());
						data.setPhaseStartDate(data.getScheduleStartDate());
						data.setModuleFlag(ps.get(0).getModuleFlag());
						data.setTotalSessions(data.getNoOfSessions());
						data.setPhaseInstanceId(data.getCrsActyId());
					}
				} else {
					data.setTotalSessions(ocmschprRepository.getTotalSessions(data));
					data.setPhaseInstanceId(null);
				}
				
				if(data.getParentCrsActyId() != null) {
					data.setProgramIstanceId(data.getParentCrsActyId());
				} else {
					data.setProgramIstanceId(data.getCrsActyId());
				}
				
				data.setCheckSum(new Long(tagServiceService.getCrsActyChecksum(data.getProgramIstanceId())));
			});
		}
		 
		 return crsActData;
	}
	  
	 
	
	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules searchRecord) {
		List<VAcpSchedules> list=null;
		list= ocmschprRepository.vAcpSchedulesExecuteQuery(searchRecord);
		if (list != null && list.size() > 0) {
			list.forEach(data -> {
			    switch (data.getScheduleDate().getDay()) {
							case 0:
								data.setWeekDay("Sunday");
								break;
							case 1:
								data.setWeekDay("Monday");
								break;
							case 2:
								data.setWeekDay("Tuesday");
								break;
							case 3:
								data.setWeekDay("Wednesday");
								break;
							case 4:
								data.setWeekDay("Thursday");
								break;
							case 5:
								data.setWeekDay("Friday");
								break;
							case 6:
								data.setWeekDay("Saturday");
								break;
						}
			});
		}
		return list;
	}
	  
	
	
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules searchRecord) {
		return ocmschprRepository.crsScheduleRulExecuteQuery(searchRecord);

	}

	@Override
	public VAcpSchedules vAcpSchedulesInsertChecking(Long crsActyId) {
		Map<String, Object> returnObject = null;
		final VAcpSchedules bean = new VAcpSchedules();
		returnObject=tagServiceSchedService.getCrsActyWithGaps(crsActyId);
		
		if (returnObject != null) {
			bean.setPhaseInstanceId(returnObject.get("P_PHASE_INSTANCE_ID") != null
					? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_INSTANCE_ID")))
					: null);
			bean.setPhaseInstanceDesc(returnObject.get("P_PHASE_INSTANCE_DESC") != null
					? String.valueOf(returnObject.get("P_PHASE_INSTANCE_DESC"))
					: null);
			bean.setPhaseListSeq(returnObject.get("P_PHASE_LIST_SEQ") != null
					? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_LIST_SEQ")))
					: null);
			bean.setPhaseSessionLength(returnObject.get("P_PHASE_SESSION_LENGTH") != null
					? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_SESSION_LENGTH")))
					: null);
			bean.setModuleInstanceId(returnObject.get("P_MODULE_INSTANCE_ID") != null
					? new BigDecimal(String.valueOf(returnObject.get("P_MODULE_INSTANCE_ID")))
					: null);
			bean.setModuleInstanceDesc(returnObject.get("P_MODULE_INSTANCE_DESC") != null
					? String.valueOf(returnObject.get("P_MODULE_INSTANCE_DESC"))
					: null);
			bean.setModuleListSeq(returnObject.get("P_MODULE_LIST_SEQ") != null
					? new BigDecimal(String.valueOf(returnObject.get("P_MODULE_LIST_SEQ")))
					: null);
			if(bean.getPhaseInstanceId() != null) {
				bean.setSessionNo(ocmschprRepository.getSessionNo(bean.getPhaseInstanceId()));
			}
		}		
		return bean;
	}

	@Override
	public String getWeekday(VAcpSchedules searchRecord) {
		return ocmschprRepository.getWeekday(searchRecord);
	}
	
	@Override
	public CourseActivities vAcpSchedulesValidate(final VAcpSchedules vacp) {
		Map<String, Object> returnObject = null;
		final CourseActivities bean = new CourseActivities();
		returnObject=tagServiceSchedService.getScheduleDateLimits(vacp.getProgramInstanceId(), vacp.getPhaseListSeq(), vacp.getSessionNo());
		if (returnObject != null) {
			bean.setOfferingStartDate(
					returnObject.get("P_PRIOR_DATE") != null ? (Date) returnObject.get("P_PRIOR_DATE") : null);
			bean.setPhaseStartDate(
					returnObject.get("P_NEXT_DATE") != null ? (Date) returnObject.get("P_NEXT_DATE") : null);
		}
		return bean;
	}

	@Override
	@Transactional()
	public CourseActivities ocsmchprCommit(OcmschprCommitBean searchBean) {
		CourseActivities crsAct=new CourseActivities();
		Long returnValue=0l;
		try {
			if(searchBean.getCrsActCommitBean() != null ) {
				searchBean.getCrsActCommitBean().setCreateUserId(searchBean.getCreateUserId());
				searchBean.setCreateUserId(searchBean.getCreateUserId());
			}
			
			if(searchBean.getVacpscheduleCommitBean() != null ) {
				searchBean.getVacpscheduleCommitBean().setCreateUserId(searchBean.getCreateUserId());
			}
			
			if(searchBean.getCrsschedulerulCommitBean() != null ) {
				searchBean.getCrsschedulerulCommitBean().setCreateUserId(searchBean.getCreateUserId());
			}
			
			crsActCommit(searchBean.getCrsActCommitBean());
			Object response=vAcpSchedulesCommit(searchBean.getVacpscheduleCommitBean());
			if(response instanceof CourseActivities) {
				return (CourseActivities) response;
			}
			crsScheduleRulCommit(searchBean.getCrsschedulerulCommitBean());
			returnValue=1l;
		} catch (Exception e) {
			logger.error(e);
		}
		crsAct.setCapacity(returnValue);
		return crsAct;
	}
	
	public Integer crsActCommit(CourseActivitiesCommitBean commitBean) {
		Integer retValue=0;
		
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			retValue=ocmschprRepository.updatecrsAct(commitBean.getUpdateList());
		}
	
	
	return retValue;
	}

	@Override
	public Object vAcpSchedulesCommit(VAcpSchedulesCommitBean commitBean) {
		Integer retValue = 0;

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				VAcpSchedules data = commitBean.getInsertList().get(i);
				if (data.getCatchUpSessionFlag() != null && data.getCatchUpSessionFlag().equals("true")) {
					data.setCatchUpSessionFlag("Y");
				} else {
					data.setCatchUpSessionFlag("N");
				}

				if (data.getScheduleStatus().equals("SCH") && data.getCatchUpSessionFlag().equals("N")) {
					CourseActivities ca = vAcpSchedulesValidate(data);
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String errorMsg = null;
					if (ca != null && ca.getOfferingStartDate() != null
							&& ca.getOfferingStartDate().compareTo(data.getStartTime()) > 0) {
						errorMsg = "The date and start time of this schedule must be after the prior ("
								+ formatter.format(ca.getOfferingStartDate()) + ") session for this occurrence";
					}

					else if (ca != null && ca.getPhaseStartDate() != null
							&& ca.getPhaseStartDate().compareTo(data.getStartTime()) < 0) {
						errorMsg = "The date and start time of this schedule must be before the next ("
								+ formatter.format(ca.getPhaseStartDate()) + ") session for this occurrence";
					}

					if (errorMsg != null) {
						CourseActivities response = new CourseActivities();
						response.setScheduleNotes(errorMsg);
						return response; 
					}
				}
				net.syscon.s4.cm.programsservices.VAcpSchedules obj=new net.syscon.s4.cm.programsservices.VAcpSchedules();
				BeanUtils.copyProperties(data, obj);
				obj.setModuleInstanceId(data.getModuleInstanceId()!=null?data.getModuleInstanceId().longValue():null);
				tagServiceSchedService.createCourseSchedule(obj, commitBean.getCreateUserId());

			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			for (int i = 0; i < commitBean.getUpdateList().size(); i++) {
				VAcpSchedules data = commitBean.getUpdateList().get(i);
				if (data.getCatchUpSessionFlag() != null && data.getCatchUpSessionFlag().equals("true")) {
					data.setCatchUpSessionFlag("Y");
				} else {
					data.setCatchUpSessionFlag("N");
				}

				if (data.getScheduleStatus().equals("SCH") && data.getCatchUpSessionFlag().equals("N")) {
					CourseActivities ca =vAcpSchedulesValidate(data);
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					String errorMsg = null;
					if (ca != null && ca.getOfferingStartDate() != null
							&& ca.getOfferingStartDate().compareTo(data.getStartTime()) > 0) {
						errorMsg = "The date and start time of this schedule must be after the prior ("
								+ formatter.format(ca.getOfferingStartDate()) + ") session for this occurrence";
					}

					if (ca != null && ca.getPhaseStartDate() != null
							&& ca.getPhaseStartDate().compareTo(data.getStartTime()) < 0) {
						errorMsg = "The date and start time of this schedule must be before the next ("
								+ formatter.format(ca.getPhaseStartDate()) + ") session for this occurrence";
					}

					if (errorMsg != null) {
						try {
							throw new IllegalArgumentException();
						} catch (Exception e) {
							CourseActivities response = new CourseActivities();
							response.setScheduleNotes(errorMsg);
							return response;
						}

					}

				}
				net.syscon.s4.cm.programsservices.VAcpSchedules obj=new net.syscon.s4.cm.programsservices.VAcpSchedules();
				BeanUtils.copyProperties(data, obj);
				tagServiceSchedService.changeCourseSchedule(obj, commitBean.getCreateUserId());
				if (data.getCatchUpSessionFlag().equals("N")) {

				} else {
					tagProgrammesService.updateCourseAttendanceDates(obj,  commitBean.getCreateUserId());
				}

				if (data.getScheduleStatus().equals("CANC") && data.getScheduleStatusTemp().equals("SCH")) {
					tagProgrammesService.cancelAttendForSchedule(data.getCrsSchId(), commitBean.getCreateUserId());
				}
			}
		}

		return retValue;
	}

	@Override
	public Integer crsScheduleRulCommit(CourseScheduleRulesCommitBean commitBean) {
		Integer retValue=0;
		if(commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(CourseScheduleRules cslddben: commitBean.getInsertList()) {
				cslddben.setCreateUserId(commitBean.getCreateUserId());
				cslddben.setCourseScheduleRuleId(ocmschprRepository.getCourseScheduleRuleId());
			}
			retValue=ocmschprRepository.crsScheduleRulInsertCourseScheduleRules(commitBean.getInsertList());
		}
		
		if(commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			retValue=ocmschprRepository.crsScheduleRulUpdateCourseScheduleRules(commitBean.getUpdateList());	
		}

		if(commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			retValue=ocmschprRepository.crsScheduleRulDeleteCourseScheduleRules(commitBean.getDeleteList());
		}
	
	
	return retValue;
	}
	
	@Override
	public Integer vAcpSchedulesDelete(VAcpSchedules searchBean) {
		Integer retVal=null;
		try {
			net.syscon.s4.cm.programsservices.VAcpSchedules obj=new net.syscon.s4.cm.programsservices.VAcpSchedules();
			BeanUtils.copyProperties(searchBean, obj);
			tagServiceSchedService.csAcpClearSessions(obj);
			ocmschprRepository.updateCrsActyChecksum(searchBean.getProgramInstanceId(),searchBean.getCreateUserId());
			retVal = 1;
			
		}catch(Exception e) {
			logger.error(e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Boolean chkAllocationExists(CourseActivities searchBean) {
		return ocmschprRepository.chkAllocationExists(searchBean);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CourseActivities buildSchedule(CourseActivities searchBean) throws Exception {
		CourseActivities retVal=new CourseActivities();
		Date lastDate=null;
		Date nextDate=null; 
		try {
			lastDate=tagServiceSchedService.getLastProgramScheduleDate(searchBean.getProgramIstanceId(), searchBean.getCourseClass().equals("CRS_PH")?searchBean.getListSeq():null);
			if(searchBean.getCourseClass().equals("CRS_PH")){
				nextDate=tagServiceSchedService.getNextPhaseScheduleDate(searchBean.getProgramIstanceId(),searchBean.getListSeq());
			}
			Map<String, Object> returnObject = null;
			try {
				returnObject=tagServiceSchedService.csBuildAcpSchedule(searchBean.getCrsActyId(), searchBean.getStartDate(),searchBean.getUptoListSeq() != null ?  searchBean.getUptoListSeq().longValue() : null, (searchBean.getWeeks() != null) ? searchBean.getWeeks() : 0,searchBean.getCreateUserId());
			}catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				if(e.getMessage().contains("No Build rules exist")) {
					retVal.setDescription("No Build rules exist");
					throw new IllegalArgumentException();
				}
			}
			if(returnObject != null) {
				retVal.setNoOfSessions(
						returnObject.get("P_NO_SCHEDULES") != null ? new Long(String.valueOf(returnObject.get("P_NO_SCHEDULES")))
								: null);
				retVal.setOfferingStartDate(
						returnObject.get("P_FROM_DATE") != null ? (Date)(returnObject.get("P_FROM_DATE")) : null);
				retVal.setOfferingEndDate(
						returnObject.get("P_TO_DATE") != null ? (Date)(returnObject.get("P_TO_DATE")) : null);
			}
			
			if(lastDate != null && (retVal.getOfferingStartDate() != null && retVal.getOfferingStartDate().compareTo(lastDate) < 0) ){
				retVal.setCode("warn");
				retVal.setDescription("The date and time of the first session generated ("+retVal.getOfferingStartDate()+") is before an existing session date for the occurence ("+lastDate+")");
					throw new IllegalArgumentException();
			}
			
			if(nextDate != null && (retVal.getOfferingEndDate () != null && retVal.getOfferingEndDate().compareTo(nextDate) > 0) ){
				retVal.setCode("warn");
				retVal.setDescription("The date and time of the first session generated ("+retVal.getOfferingEndDate()+") is after an existing session date for the occurence ("+nextDate+")");
					throw new IllegalArgumentException();
			}
			ocmschprRepository.updateCrsActyChecksum(searchBean.getProgramIstanceId(),searchBean.getCreateUserId());
			if(retVal != null && (retVal.getNoOfSessions() != null && retVal.getNoOfSessions()>0)) {
				retVal.setCode("success");
				retVal.setDescription(retVal.getNoOfSessions()+" schedule records created up to ("+retVal.getOfferingEndDate()+")");
			} else {
				retVal.setCode("info");
				retVal.setDescription("No schedule records required to be created.");
			}
		}catch(Exception e) {
			logger.error(e);
			throw new Exception(retVal.getDescription());
		}
		return retVal;
	}
	
	@Override
	@Transactional
	public CourseActivities reSchedule(CourseActivities searchBean) {
		Date priorScheduleDate=null;
		Date nextDate=null;
		CourseActivities retVal=null;
		try {
			priorScheduleDate=tagServiceSchedService.getPriorScheduleDate(searchBean);
			if(searchBean.getCourseClass().equals("CRS_PH")){
				nextDate=tagServiceSchedService.getNextPhaseScheduleDate(searchBean.getProgramIstanceId(),searchBean.getListSeq());
			}
			retVal=tagServiceSchedService.csAcpReSchedule(searchBean.getCrsActyId(), searchBean.getNbtCrsSchId(), searchBean.getNbtreScheduleDate(), searchBean.getCreateUserId());
			if(priorScheduleDate != null && (retVal.getOfferingStartDate().compareTo(priorScheduleDate) < 0) ){
				retVal.setCode("warn");
				retVal.setDescription("The date and time of the first session generated ("+retVal.getOfferingStartDate()+") is before an existing session date for the occurence ("+priorScheduleDate+")");
				try{
					throw new IllegalArgumentException();
				} catch(Exception e) {
					return retVal;
				}
			}
			
			if(nextDate != null && (retVal.getOfferingEndDate().compareTo(nextDate) > 0) ){
				retVal.setCode("warn");
				retVal.setDescription("The date and time of the first session generated ("+retVal.getOfferingEndDate()+") is after an existing session date for the occurence ("+nextDate+")");
				try{
					throw new IllegalArgumentException();
				} catch(Exception e) {
					return retVal;
				}
			}
			
			ocmschprRepository.updateCrsActyChecksum(searchBean.getProgramIstanceId(),searchBean.getCreateUserId());
			retVal.setCode("success");
			retVal.setDescription("Sessions have been Rescheduled");
		} catch(Exception e) {
			logger.error(e);
			retVal=null;
		}
		return retVal;	
	}
	
	@Override
	public CourseActivities defaultBuildParameters(CourseActivities searchBean) {
		CourseActivities data=null;
		Boolean retVal=false;
		try {
			data=tagServiceSchedService.lastCrsActyBuilt(searchBean.getCrsActyId());
			if(searchBean.getCourseClass().equals("CRS_PH")) {
				if(tagServiceSchedService.noSessionsDone(searchBean.getCrsActyId()) < (searchBean.getTotalSessions() !=null ?searchBean.getTotalSessions():0)) {
					retVal= true;
				}
			} else if(((searchBean.getTotalSessions()) !=null ?searchBean.getTotalSessions():0) >0 && ((data != null && data.getLastListSeq()!= null && data.getLastListSeq() == 0) || ocmschprRepository.chkAnyToBuild(searchBean.getCrsActyId(), data != null ?data.getLastListSeq():null))) {
				retVal= true;
			}
			if(retVal) {
				data.setActiveFlag("Y");
			} else{
				data.setActiveFlag("N");
			}
			
		} catch( Exception e){
			logger.error(e);
		}
		return data;
	}
	
	
	@Override
	public Integer updateCrsActyChecksum(Long programInstanceId,String userName) {
		return ocmschprRepository.updateCrsActyChecksum(programInstanceId,userName);
	}
	
	
	 
	
	public List<ReferenceCodes> rgRemainingRecordGroup(String searchBean) {
		Integer crsActyId = null;
		Integer lastListSeq = null;
		final String[] inputArray = searchBean.split("-");
		if (inputArray.length > 0) {
			if (inputArray[0] != null && !inputArray[0].equals(""))
				crsActyId = Integer.valueOf(inputArray[0]);
			if (inputArray.length > 1 && inputArray[1] != null && !inputArray[1].equals("null"))
				lastListSeq =  Integer.valueOf(inputArray[1]);
		}
		return ocmschprRepository.rgRemainingRecordGroup(crsActyId, lastListSeq);

	}
	


}