package net.syscon.s4.inst.incidentsoic.impl;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReportCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OidstfrpCommonCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipmentCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;
import net.syscon.s4.im.incidentsoic.beans.StaffForceCommitBean;
import net.syscon.s4.inst.incidentsoic.OidstfrpRepository;
import net.syscon.s4.inst.incidentsoic.OidstfrpService;

@Service
public class OidstfrpServiceImpl extends BaseBusiness implements OidstfrpService {

	@Autowired
	OidstfrpRepository oidstfrpRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OcdintakRepository ocdintakRepository;
	
	private static Logger logger = LogManager.getLogger(OidstfrpServiceImpl.class);

	public OidstfrpServiceImpl() {
		super();
	}

	@Transactional
	@Override
	public Integer staffReportCommitData(IncidentStaffReportCommitBean commitBean) {

		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (IncidentStaffReport obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					obj.setLockReferenceTime(eliteDateService.getDBTime());
					Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
					obj.setReporterStaffId(staffId);
				}
				liReturn = staffReportCommitDataInsert(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (IncidentStaffReport obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
					obj.setLockReferenceTime(eliteDateService.getDBTime());
					Integer staffId = ocdintakRepository.oldContactGetStaffId(commitBean.getCreateUserId());
					obj.setReporterStaffId(staffId);
				}
				liReturn = staffReportCommitDataUpdate(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = staffReportCommitDataDelete(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsCommit", e);
		}
		return liReturn;
	}

	@Transactional
	public Integer staffReportCommitDataInsert(List<IncidentStaffReport> commitBean) {
		Integer li = oidstfrpRepository.staffReportCommitDataInsert(commitBean);
		return li;
	}

	@Transactional
	public Integer staffReportCommitDataUpdate(List<IncidentStaffReport> commitBean) {
		Integer li = 0;
		for (IncidentStaffReport obj : commitBean) {
			try {
				String lockFlag = oidstfrpRepository.getLockFlag(obj.getIncidentReportId());
				if (obj.getLockFlag().equalsIgnoreCase("N") && lockFlag.equalsIgnoreCase("Y")) {
					obj.setLockReferenceTime(eliteDateService.getDBTime());
					oidstfrpRepository.updAgencyIncRepStaffReport(obj);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		li = oidstfrpRepository.staffReportCommitDataUpdate(commitBean);
		return li;
	}

	@Transactional

	public Integer staffReportCommitDataDelete(List<IncidentStaffReport> commitBean) {
		Integer li = oidstfrpRepository.staffReportCommitDataDelete(commitBean);
		return li;
	}

	@Transactional
	@Override
	public List<IncidentStaffReport> staffReportExecuteQuery(IncidentStaffReport commitBean) {
		List<IncidentStaffReport> Li = new ArrayList<>();
		try {
			Li = oidstfrpRepository.staffReportExecuteQuery(commitBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Li;
	}

	@Override
	public List<StaffForce> staffforceExecuteQuery(StaffForce objSearchDao) {
		List<StaffForce> serachList = new ArrayList<>();
		try {
			serachList = oidstfrpRepository.staffforceExecuteQuery(objSearchDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serachList;
	}

	@Override
	public List<StaffEquipment> staffEquipementExecuteQuery(StaffEquipment objSearchDao) {
		List<StaffEquipment> serachList = new ArrayList<>();
		try {
			serachList = oidstfrpRepository.staffEquipementExecuteQuery(objSearchDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serachList;
	}

	@Override
	public Integer staffforceCommitData(StaffForceCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				Integer staffCount = oidstfrpRepository
						.getIncidentStaffForecCount(commitBean.getInsertList().get(0).getIncidentReportId());
				if (staffCount == 0) {
					commitBean.getInsertList().get(0).setStaffSequnce(1);
				}
				for (StaffForce obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					if(obj.getStaffSequnce() == null) {
						obj.setStaffSequnce(staffCount+2);
					}
				}
				liReturn = StaffForceCommitBeanInsert(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (StaffForce obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}

				liReturn = StaffForceCommitBeanUpdate(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = StaffForceCommitBeanDelete(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}

	@Transactional
	public Integer StaffForceCommitBeanInsert(List<StaffForce> lInsert) {
		final Integer li = oidstfrpRepository.StaffForceCommitBeanInsert(lInsert);
		return li;
	}

	@Transactional
	public Integer StaffForceCommitBeanUpdate(List<StaffForce> lUpdate) {
		final Integer li = oidstfrpRepository.StaffForceCommitBeanUpdate(lUpdate);
		return li;
	}

	@Transactional
	public Integer StaffForceCommitBeanDelete(List<StaffForce> lDelete) {
		final Integer li = oidstfrpRepository.StaffForceCommitBeanDelete(lDelete);
		return li;
	}

	@Override
	public Integer staffEquipementCommitData(StaffEquipmentCommitBean commitBean) {
		Integer liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (StaffEquipment obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}

				liReturn = StaffEquipmentCommitBeanInsert(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (StaffEquipment obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = StaffEquipmentCommitBeanUpdate(commitBean.getUpdateList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = StaffEquipmentCommitBeanDelete(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}

	@Transactional
	public Integer StaffEquipmentCommitBeanInsert(List<StaffEquipment> lInsert) {
		final Integer li = oidstfrpRepository.StaffEquipmentCommitBeanInsert(lInsert);
		return li;
	}

	@Transactional
	public Integer StaffEquipmentCommitBeanUpdate(List<StaffEquipment> lUpdate) {
		final Integer li = oidstfrpRepository.StaffEquipmentCommitBeanUpdate(lUpdate);
		return li;
	}

	@Transactional
	public Integer StaffEquipmentCommitBeanDelete(List<StaffEquipment> lDelete) {
		final Integer li = oidstfrpRepository.StaffEquipmentCommitBeanDelete(lDelete);
		return li;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)

	public Integer staffReportCommonSave(OidstfrpCommonCommitBean commitBean) {
		Integer returnVal = 0;
		if (commitBean != null) {
			if (commitBean.getstaffReportCommitList() != null) {
				if ((commitBean.getstaffReportCommitList().getInsertList() != null
						&& commitBean.getstaffReportCommitList().getInsertList().size() > 0)
						|| (commitBean.getstaffReportCommitList().getUpdateList() != null
						&& commitBean.getstaffReportCommitList().getUpdateList().size() > 0)
						|| (commitBean.getstaffReportCommitList().getDeleteList() != null
						&& commitBean.getstaffReportCommitList().getDeleteList().size() > 0)) {
					commitBean.getstaffReportCommitList().setCreateUserId(commitBean.getCreateUserId());
					logger.info("Before Calling Staff Report CommitBean");
					returnVal = staffReportCommitData(commitBean.getstaffReportCommitList());
					logger.info("After bCalling Staff Report CommitBean");
				}

			}
			if (commitBean.getstaffForceCommitList() != null) {
				if ((commitBean.getstaffForceCommitList().getInsertList() != null
						&& commitBean.getstaffForceCommitList().getInsertList().size() > 0)
						|| (commitBean.getstaffForceCommitList().getUpdateList() != null
						&& commitBean.getstaffForceCommitList().getUpdateList().size() > 0)
						|| (commitBean.getstaffForceCommitList().getDeleteList() != null
						&& commitBean.getstaffForceCommitList().getDeleteList().size() > 0)) {
					commitBean.getstaffForceCommitList().setCreateUserId(commitBean.getCreateUserId());
					logger.info("Before Calling Staff Force CommitBean");
					returnVal = staffforceCommitData(commitBean.getstaffForceCommitList());
					logger.info("Before Calling Staff Force CommitBean");
				}
			}
			if (commitBean.getstaffEquipmentCommitList() != null) {
				if ((commitBean.getstaffEquipmentCommitList().getInsertList() != null
						&& commitBean.getstaffEquipmentCommitList().getInsertList().size() > 0)
						|| (commitBean.getstaffEquipmentCommitList().getUpdateList() != null
						&& commitBean.getstaffEquipmentCommitList().getUpdateList().size() > 0)
						|| (commitBean.getstaffEquipmentCommitList().getDeleteList() != null
						&& commitBean.getstaffEquipmentCommitList().getDeleteList().size() > 0)) {
					commitBean.getstaffEquipmentCommitList().setCreateUserId(commitBean.getCreateUserId());
					logger.info("Before Calling Staff Equipment CommitBean");
					returnVal = staffEquipementCommitData(commitBean.getstaffEquipmentCommitList());
					logger.info("Before Calling Staff Equipment CommitBean");
				}
			}
		}
		return returnVal;
	}

	@Override
	public ReferenceCodes getCountDown(AgencyIncidentParties agencyIncidentParties) {
		LocalDateTime today = null;
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			AgencyIncidentParties incidentObj = oidstfrpRepository.getLockReferenceTime(agencyIncidentParties);
			if(incidentObj.getReportType() != null) {
				LocalDateTime currentTime = LocalDateTime.parse( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(eliteDateService.getDBTime()) );
				currentTime = currentTime.withNano(0);
				LocalDateTime createDateTime = LocalDateTime.parse( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(incidentObj.getLockReferenceTime()) );
				returnObj = oidstfrpRepository.getCountDownTime(incidentObj.getReportType());
				if(returnObj != null && returnObj.getAutomaticFlag().equalsIgnoreCase("Y")) {
					today = LocalDateTime.parse( new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(incidentObj.getLockReferenceTime()) );
				}
				if(today != null) {
					if("MONTHS".equalsIgnoreCase(returnObj.getLengthUnit().trim())) {
						today = today.plusMonths(returnObj.getLength() != null ? returnObj.getLength() : 0 );
						long days = Duration.between(createDateTime, currentTime).toDays();
						today = today.minusDays(days);
					}else if("DAYS".equalsIgnoreCase(returnObj.getLengthUnit().trim())) {
						today = today.plusDays(returnObj.getLength() != null ? returnObj.getLength() : 0);
						long hrs = Duration.between(createDateTime, currentTime).toHours();
						today = today.minusHours(hrs);
					}else if("HOURS".equalsIgnoreCase(returnObj.getLengthUnit().trim())) {
						today = today.plusHours(returnObj.getLength() != null ? returnObj.getLength() : 0);
					}
					if(today.compareTo(currentTime) < 0) {
						today = currentTime;
					}
					returnObj.setCreateDatetime(Date.from(today.atZone(ZoneId.systemDefault()).toInstant()));
				}
			}
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"In getCountDown method",e);
		}
		return returnObj;
	}

	@Override
	public Integer updateSaveFlag(IncidentStaffReport incidentStaffReport) {
		return oidstfrpRepository.updateLockFlag(incidentStaffReport);
	}

}
