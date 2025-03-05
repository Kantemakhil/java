package net.syscon.s4.inst.shiftlogs.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.shiftlogs.OidshlogRepository;
import net.syscon.s4.inst.shiftlogs.OidshlogService;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLog;
import net.syscon.s4.inst.shiftlogs.bean.OffendersShiftLogCommitBean;
import net.syscon.s4.pkgs.oidshlog.OidshlogPkgService;

/**
 * Class OidshlogServiceImpl
 */
@Service
public class OidshlogServiceImpl extends BaseBusiness implements OidshlogService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidshlogServiceImpl.class.getName());
	
	@Autowired
	private OidshlogRepository oidshlogRepo;
	@Autowired
	private OidshlogPkgService oidshlogService;

	/**
	 * Creates new OidshlogServiceImpl class Object
	 */
	public OidshlogServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public List<StaffMembers> agyShilPostQuery(final StaffMembers paramBean) {
		return  oidshlogRepo.agyShilPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public SystemProfiles agyShilPreUpdate(final SystemProfiles paramBean) {
		return oidshlogRepo.agyShilPreUpdate(paramBean);
	}


	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public AgencyInternalLocations cgfkchkAgyShilAgyShil(final AgencyInternalLocations paramBean) {
		return oidshlogRepo.cgfkchkAgyShilAgyShil(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public StaffMembers cgfkchkAgyShilAgyShilSta(final StaffMembers paramBean) {
		return oidshlogRepo.cgfkchkAgyShilAgyShilSta(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public List<AgencyInternalLocations> defaultAgency(final AgencyInternalLocations paramBean) {
		return oidshlogRepo.defaultAgency(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord <AgencyShiftLogs>
	 *
	 * @return List<AgencyShiftLogs>
	 */
	public List<AgencyShiftLogs> agyShilExecuteQuery(final AgencyShiftLogs searchRecord) {
		List<AgencyInternalLocations> lstLocations = new ArrayList<AgencyInternalLocations>();
		if (searchRecord != null) {
			lstLocations = oidshlogRepo.getLocationListPostQuery(null);
			if (searchRecord.getDspAgyLocId3() != null) {
				for (final AgencyInternalLocations objAgyInt : lstLocations) {
					if (searchRecord.getDspAgyLocId3().equals(objAgyInt.getCode())) {
						searchRecord.setInternalLocationId(new BigDecimal(objAgyInt.getInternalLocationId()));
						break;
					}
				}
			}
		}
		final List<AgencyShiftLogs> lstAgency = oidshlogRepo.agyShilExecuteQuery(searchRecord);
		if (lstAgency != null && lstAgency.size() > 0) {
			for (final AgencyShiftLogs objAgency : lstAgency) {
				
				if (objAgency.getInternalLocationId() != null) {
					objAgency.setDspAgyLocId4(oidshlogRepo.getLocationValue(objAgency.getInternalLocationId()));
					for (final AgencyInternalLocations objAgyInt : lstLocations) {
						if (objAgency.getInternalLocationId()!=null && objAgyInt.getInternalLocationId()!=null 
								&& objAgency.getInternalLocationId().toString()
								.equals(objAgyInt.getInternalLocationId().toString())) {
							objAgency.setDspAgyLocId3(objAgyInt.getCode());
							break;
						}
					}
				}
				final String ccObserText = objAgency.getObservationText() == null ? null : objAgency.getObservationText().toString();
				 if (ccObserText != null) {
				 try {
				 final String  obserDetails = ccObserText.substring(0, (int) ccObserText.length());
				 objAgency.setObservationDetails(obserDetails);
				 objAgency.setObservationText(null);
				 } catch (Exception e) {
						logger.error("In agyShilExecuteQuery method : ", e);
					}
				 }
			}
		}

		return lstAgency;

	}

	/**
	 * This method saves records to the database
	 *
	 * @param commitBean <AgencyShiftLogsCommitBean>
	 *
	 * @return List<AgencyShiftLogs>
	 */
	@Transactional
	public List<AgencyShiftLogs> agyShilCommit(final AgencyShiftLogsCommitBean commitBean) {
		List<AgencyShiftLogs> returnList=new ArrayList<>();
		AgencyShiftLogs returnObject=new AgencyShiftLogs();
		Integer liReturn = 0;
		List<AgencyShiftLogs> lstAgyShift = new ArrayList<AgencyShiftLogs>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (final AgencyShiftLogs objAgency : commitBean.getInsertList()) {
				lstAgyShift = new ArrayList<AgencyShiftLogs>();
				objAgency.setShiftLogSeq(oidshlogRepo.preInsert());
				/*
				 * objAgency.setInternalLocationId(
				 * oidshlogRepo.internalLocationId(objAgency.getDspAgyLocId3(),
				 * objAgency.getDspAgyLocId4()));
				 */
			objAgency.setCreateUserId(commitBean.getCreateUserId());
				lstAgyShift.add(objAgency);
				liReturn = oidshlogRepo.agyShilInsertAgencyShiftLogs(lstAgyShift);
				if(objAgency.getObservationDetails() != null) {
					 liReturn = oidshlogService.saveObservationText(objAgency,commitBean.getCreateUserId());
				}
			}
		}
		 if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			 for (final AgencyShiftLogs objAgency : commitBean.getUpdateList()) {
				 objAgency.setModifyUserId(commitBean.getCreateUserId());
				 lstAgyShift = new ArrayList<AgencyShiftLogs>();
				 if (objAgency.getDspAgyLocId3() != null && objAgency.getDspAgyLocId4() != null) {
					 objAgency.setInternalLocationId(
								oidshlogRepo.internalLocationId(objAgency.getDspAgyLocId3(), objAgency.getDspAgyLocId4()));
				 }
				 lstAgyShift.add(objAgency);
				 liReturn = oidshlogRepo.agyShilUpdateAgencyShiftLogs(lstAgyShift);
			 }
		 }
		 if (commitBean.getDeleteList() != null &&commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele -> ele.setModifyUserId(commitBean.getCreateUserId()));
				 liReturn = oidshlogRepo.agyShilDeleteAgencyShiftLogs(commitBean.getDeleteList());
		 }
		 returnObject.setListSeq(liReturn);
		 returnList.add(returnObject);
		return returnList;
	}
	
	/**
	 * Generates log sequence without saving records to the database
	 *
	 * @param commitBean <AgencyShiftLogsCommitBean>
	 *
	 * @return List<AgencyShiftLogs>
	 */
	public List<AgencyShiftLogs> agyShilWithoutLock(final AgencyShiftLogsCommitBean commitBean) {
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final AgencyShiftLogs objAgency : commitBean.getInsertList()) {
				objAgency.setShiftLogSeq(oidshlogRepo.preInsertWithoutLock());
		}
		}
		return commitBean.getInsertList();
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyLocations> cgfkAgyShilDspAgyLocId4RecordGroup(final String caseLoadId) {
		return oidshlogRepo.cgfkAgyShilDspAgyLocId4RecordGroup(caseLoadId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<StaffMembers> cgfkAgyShilStaffIdRecordGroup(final String caseLoadId) {
		return oidshlogRepo.cgfkAgyShilStaffIdRecordGroup(caseLoadId);
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyInternalLocations> cgfkAgyShilDspAgyLocId3RecordGroup(final String agyLocId) {
		List<AgencyInternalLocations> lovList = new ArrayList<AgencyInternalLocations>();
		List<AgencyInternalLocations> lstLocations = new ArrayList<AgencyInternalLocations>();
		lovList = oidshlogRepo.cgfkAgyShilDspAgyLocId3RecordGroup(agyLocId);
		lstLocations = oidshlogRepo.getLocationListPostQuery(agyLocId);
		for(AgencyInternalLocations obj : lstLocations) {
			if(lovList != null) {
				
				int count = lovList.stream().filter(e -> e.getInternalLocationId().equals(obj.getInternalLocationId())).collect(Collectors.toList()).size();
				if(count > 0) {
					obj.setCanDisplay(true);
				}else {
					obj.setCanDisplay(false);
				}
			}
		}
		return lstLocations;

	}
	
	

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<ReferenceCodes> cgfkAgyShilAgyActivityCodRecordGroup() {
		return oidshlogRepo.cgfkAgyShilAgyActivityCodRecordGroup();

	}

	/**
	 * This method is used to create new record instance
	 */
	public StaffMembers agyShilWhenNewRecordInstance(String user) {
		return oidshlogRepo.agyShilWhenNewRecordInstance(user);
	}
	
	/**
	 * This method will be called for the back dated hours
	 * @return String
	 */
	public String getBackDateHours() {
		return oidshlogRepo.getBackDateHours();
	}
	
	/**
	 * This method will be called for the back dated hours
	 * @return String
	 */
	public String checkBoxHideData() {
		return oidshlogRepo.checkBoxHideData();
	}
	
	/**
	 * This method will be called for the back dated hours
	 * @return Integer
	 */
	public Integer checkBoxShlogData(final String userid) {
		return oidshlogRepo.checkBoxShlogData(userid);
	}

	@Override
	public List<VHeaderBlock> relatedOffendersExcuteQuery( Integer internalLocationId) {
	List<VHeaderBlock> relatedOffenders = new ArrayList<VHeaderBlock>();

		 relatedOffenders =  oidshlogRepo.relatedOffendersExcuteQuery(internalLocationId);
		 
		 return relatedOffenders;
	}

	@Override
	public List<OffendersShiftLog> offShilCommit(OffendersShiftLogCommitBean commitBean) {
		OffendersShiftLog returnObject=new OffendersShiftLog();
		List<OffendersShiftLog> returnList=new ArrayList<>();
		Integer liReturn = 0;
		List<OffendersShiftLog> lstAgyShift = new ArrayList<OffendersShiftLog>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffendersShiftLog objAgency : commitBean.getInsertList()) {
				lstAgyShift = new ArrayList<OffendersShiftLog>();			
				objAgency.setCreateUserId(commitBean.getCreateUserId());
				lstAgyShift.add(objAgency);
				liReturn = oidshlogRepo.offShilInsertAgencyShiftLogs(lstAgyShift);	
			}
		}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (final OffendersShiftLog objAgency : commitBean.getUpdateList()) {
					lstAgyShift = new ArrayList<OffendersShiftLog>();				
					objAgency.setModifyUserId(commitBean.getCreateUserId());
					lstAgyShift.add(objAgency);
					liReturn = oidshlogRepo.offShilUpdateAgencyShiftLogs(lstAgyShift);				
				}
			}
			returnObject.setListSeq(liReturn);
			returnList.add(returnObject);
		
		 
		return returnList;
	}

	@Override
	public List<OffendersShiftLog> OffendersShiftLogExcuteQuery(BigDecimal shiftLogSeq , Long internalLocationId) {	
		List<OffendersShiftLog> offShiftLogExcuteQuery = new ArrayList<OffendersShiftLog>();		
	     offShiftLogExcuteQuery =  oidshlogRepo.OffendersShiftLogExcuteQuery(shiftLogSeq ,internalLocationId );		 
		 return offShiftLogExcuteQuery;
	}
	

}