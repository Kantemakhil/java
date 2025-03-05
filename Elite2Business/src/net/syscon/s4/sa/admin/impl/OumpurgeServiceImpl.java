package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.pkgs.oumpurge.OumpurgePkgService;
import net.syscon.s4.pkgs.tag_main.TagMainService;
import net.syscon.s4.sa.admin.OumpurgeRepository;
import net.syscon.s4.sa.admin.OumpurgeService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OumpurgeServiceImpl
 */
@Service
public class OumpurgeServiceImpl extends BaseBusiness implements OumpurgeService {

	@Autowired
	private OumpurgeRepository oumpurgeRepository;
	
	@Autowired
	private OumpurgePkgService oumpurgePkgService;
	@Autowired
	private TagMainService tagMainService;
	
	private static Logger logger = LogManager.getLogger(OumpurgeServiceImpl.class.getName());
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	
	private final String FOUR="4";
	private final String FIVE="5";

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VHeaderBlock> offExecuteQuery(final VHeaderBlock searchRecord) {
		List<VHeaderBlock> returnList = oumpurgeRepository.offExecuteQuery(searchRecord,tagMainService.defBkgWhere(null));
		returnList.forEach(ele -> {
			if (ele.getNbtOffenderBookId().compareTo(BigDecimal.ZERO) > 0) {
				ele.setLocationCode("OPEN");
			} else {
				ele.setLocationCode("CLOSED");
			}
		});
		return returnList;

	}

	@Transactional
	public Integer offCommit(final VHeaderBlockCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oumpurgeRepository.offUpdateVHeaderBlock(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderBookings> offBkgExecuteQuery(final OffenderBookings searchRecord) {
		List<OffenderBookings> returnList = oumpurgeRepository.offBkgExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			if (ele.getBookingStatus() != null) {
				String data = oumpurgeRepository.BookingsOne(ele.getBookingStatus());
				if (data != null) {
					ele.setInOutStatus(data);
				}
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BKG
	 *
	 */
	@Transactional
	public Integer offBkgCommit(final OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->offenderBookingsT2Service.offenderBookingsT2(bean, oumpurgeRepository.getOldData(bean.getOffenderBookId())));
			liReturn = oumpurgeRepository.offBkgUpdateOffenderBookings(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(bean->{
				bean.setCreateUserId(commitBean.getCreateUserId());
				offenderBookingsT7Service.offenderBookingsT7Trigger(bean);
				offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(bean, ApplicationConstants.UPDATING);
				offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(bean, oumpurgeRepository.getOldData(bean.getOffenderBookId()), "UPDATING");
			});
		}
		return liReturn;
	}


	@Override
	public VHeaderBlock purgeOffenderCommit(final VHeaderBlock object) {
		String globalPurge = "N";
		Long returnvalue = 0L;
		final VHeaderBlock returnOutput = new VHeaderBlock();
		if ("OFFENDER".equals(object.getStatusReason()) || "SEALOFFENDER".equals(object.getStatusReason())) {
			final String returnData = showCaseloadAcct(object);
			if (returnData != null  && !returnData.equals("")) {
				globalPurge = "N";
				returnOutput.setSealFlag(returnData);
				returnOutput.setOffenderId(BigDecimal.valueOf(2));
				return returnOutput;
			} else {
				globalPurge = "Y";
			}
			object.setOfficer(globalPurge);

			final Integer chkACtBkg = checkActiveBooking(object);
			if (chkACtBkg > 0) {
				returnOutput.setOffenderId(BigDecimal.valueOf(3));
				return returnOutput;
			}
		}
		if (object.getRootOffenderId() == null) {
			returnOutput.setOffenderId(BigDecimal.valueOf(4));
			return returnOutput;
		}
		object.setpDelType(object.getStatusReason());
		if ("OFFENDER".equals(object.getStatusReason())) {
			object.setpDelType(object.getStatusReason());
			returnvalue = processRecordData(object);
			if (returnvalue > 1) {
				returnOutput.setOffenderId(BigDecimal.valueOf(returnvalue));
				return returnOutput;
			}
		} else if ("BOOKING".equals(object.getStatusReason())) {
			if (object.getOffenderBookId() == null) {
				returnOutput.setOffenderId(BigDecimal.valueOf(18));
				return returnOutput;
			} else {
				Integer getBookingCounts = oumpurgeRepository.getLvCountSealBookings(object.getRootOffenderId());
				if (getBookingCounts > 1) {
					returnvalue = processRecordData(object);
					if (returnvalue > 1) {
						returnOutput.setOffenderId(BigDecimal.valueOf(returnvalue));
						return returnOutput;
					}
				} else {
					if ("Y".equals(object.getSealFlag())) {
						returnvalue = processRecordData(object);
						if (returnvalue > 1) {
							returnOutput.setOffenderId(BigDecimal.valueOf(returnvalue));
							return returnOutput;
						}
					} else {
						returnOutput.setOffenderId(BigDecimal.valueOf(19));
						return returnOutput;
					}
				}
			}
		}

		if ("SEALOFFENDER".equals(object.getStatusReason())) {
			final Long data = processSealingRecord(object);
			if (data > 0) {
				returnOutput.setOffenderId(BigDecimal.valueOf(data));
				return returnOutput;
			}
		} else if ("SEALBOOKING".equals(object.getStatusReason())) {
			final Long data = processSealingRecord(object);
			if (data > 0) {
				returnOutput.setOffenderId(BigDecimal.valueOf(data));
				return returnOutput;
			}
		}
		returnOutput.setSealFlag("success");
		returnOutput.setOffenderId(BigDecimal.valueOf(20));
		return returnOutput;
	}

	public Long processRecordData(VHeaderBlock object) {
		String whereClause = null;
		Long valmsg = 0L;
		String retValue = null;
		final String profileVal = oumpurgeRepository.getProfileValue();
//		if (profileVal != null && "ONTARIO".equals(profileVal)) {
			if ("OFFENDER".equals(object.getpDelType())) {
				whereClause = "offender_book_id IN ( SELECT offender_book_id FROM offender_bookings WHERE root_offender_id = to_char(lv_root_off_id) )";
				whereClause.replace("lv_root_off_id", object.getRootOffenderId().toString());
				object.setStatus1(whereClause);
				retValue = oumpurgeRepository.chkValueExists(object);
			} else if ("BOOKING".equals(object.getpDelType())) {
				whereClause = "offender_book_id = to_char(lv_offender_book_id)";
				whereClause.replace("lv_offender_book_id", object.getOffenderBookId().toString());
				object.setStatus1(whereClause);
				retValue = oumpurgeRepository.chkValueExists(object);
			}
			if ("ERROR".equals(retValue)) {
				valmsg = 5L;
				return valmsg;
			} else {
				if ("OFFENDER".equals(object.getpDelType())) {
					if ("N".equals(object.getOfficer())) {
						valmsg = 6L;
					}
					Integer obj = oumpurgeRepository.getBeneficiaryAcc(object.getOffenderBookId());
					if (obj > 0) {
						valmsg = 7L;
						return valmsg;
					}
//					valmsg = 8L;
//					return valmsg;
				} else if ("BOOKING".equals(object.getpDelType())) {
					Integer getRecord = oumpurgeRepository.processRecord(object);
					if (getRecord == 1) {
						if ("N".equals(object.getOfficer())) {
							valmsg = 9L;
							return valmsg;
						}
						Integer obj = oumpurgeRepository.getBeneficiaryAcc(object.getOffenderBookId());
						if (obj > 0) {
							valmsg = 10L;
							return valmsg;
						}
					}
					valmsg = 11L;
					return valmsg;
				}
			}
//		}
		return valmsg;
	}

	public Long processSealingRecord(VHeaderBlock object) {
		Long valmsg = 0L;
		if ("SEALOFFENDER".equals(object.getpDelType())) {
			if ("N".equals(object.getOfficer())) {
				valmsg = 12L;
				return valmsg;
			}
			Integer obj = oumpurgeRepository.getBeneficiaryAcc(object.getOffenderBookId());
			if (obj > 0) {
				valmsg = 13L;
				return valmsg;
			}
			if (object.getSealFlag() == null || "N".equals(object.getSealFlag())) {
				valmsg = 14L;
				return valmsg;
			} else {
				valmsg = 15L;
				return valmsg;
			}
		} else if ("SEALBOOKING".equals(object.getpDelType())) {
			if (object.getSealFlag() == null || "N".equals(object.getSealFlag())) {
				valmsg = 16L;
				return valmsg;
			} else {
				valmsg = 17L;
				return valmsg;
			}
		}
		return valmsg;
	}

	@Override
	public String showCaseloadAcct(VHeaderBlock object) {
		String lvCaseloadStr = "";
		final List<OffenderTrustAccounts> returnList = oumpurgeRepository.showCaseloadAcct(object);
		for (OffenderTrustAccounts obj : returnList) {
			if (lvCaseloadStr != null) {
				lvCaseloadStr = lvCaseloadStr + "," + obj.getCaseloadId();
			} else {
				lvCaseloadStr = lvCaseloadStr + obj.getCaseloadId();
			}
			
		}
		final String subStringValue = oumpurgeRepository.getCaseloadSubStr(lvCaseloadStr);
		if (subStringValue != null) {
			return subStringValue;
		}
		return subStringValue;
	}

	public Integer checkActiveBooking(VHeaderBlock object) {
		return oumpurgeRepository.checkActiveBooking(object);

	}

	public Integer processRecord(VHeaderBlock object) {

		return oumpurgeRepository.processRecord(object);

	}

	public VHeaderBlock whenTimerExpired(VHeaderBlock object,String userName) {
		final VHeaderBlock returnData = new VHeaderBlock();
		Map<String, Object> statusReason = new HashMap<String, Object>();
		if ("OFFENDER".equals(object.getStatusReason()) || "BOOKING".equals(object.getStatusReason())) {
			if ("OFFENDER".equals(object.getStatusReason())) {
				//statusReason = oumpurgeRepository.getPurgeOffenders(object.getRootOffenderId(),
					//	object.getStatusReason(), null);
				String puge=oumpurgePkgService.purgeOffenders(object.getRootOffenderId()!=null?object.getRootOffenderId().longValue():null, object.getOffenderId()!=null?object.getOffenderId().longValue():null,object.getStatusReason(),userName);
				statusReason.put("P_STATUS", puge);
			} else if ("BOOKING".equals(object.getStatusReason())) {
//			statusReason = oumpurgeRepository.getPurgeOffenders(null, object.getStatusReason(),
//					object.getOffenderBookId());
			String oumPurge =null;	
			oumPurge = oumpurgePkgService.purgeOffenders(object.getRootOffenderId()!=null?object.getRootOffenderId().longValue():null,object.getOffenderBookId()!=null ?object.getOffenderBookId().longValue():null ,object.getStatusReason(),userName);
			statusReason.put("P_STATUS", oumPurge);
			}
			if (statusReason != null && statusReason.get("P_STATUS") != null && statusReason.get("P_STATUS") != "") {
				String data ="Error : " + statusReason.get("P_STATUS").toString();
				if (data.contains("constraint")) {
					data = data.substring(data.indexOf("constraint")
							, data.indexOf("\" on"))
							.replaceFirst("constraint", "").trim();
					data = data.substring(1).toUpperCase();
				String getStatusReason = "Error While Purging ORA-02292: integrity constraint (OMS_OWNER."+ data + ") violated - child record found";
				returnData.setSealFlag(getStatusReason);
				}
				returnData.setOffenderId(BigDecimal.valueOf(1));
				return returnData;
			} else {
				returnData.setOffenderId(BigDecimal.valueOf(2));
				return returnData;
			}
		} else {
			try {
			if ("SEALOFFENDER".equals(object.getStatusReason())) {
//				statusReason = oumpurgeRepository.getSealOffenders(object.getOffenderId(), "OFFENDER", null,
//						object.getSealFlag());
				String sealOff = oumpurgePkgService.sealingOffenders(object.getOffenderId()!=null?object.getOffenderId().longValue():null, object.getOffenderBookId()!=null?object.getOffenderBookId().longValue():null, "OFFENDER", object.getSealFlag(),userName);
				statusReason.put("P_STATUS", sealOff);
			} else if ("SEALBOOKING".equals(object.getStatusReason())) {
//				statusReason = oumpurgeRepository.getSealOffenders(null, "BOOKING", object.getOffenderBookId(),
//						object.getSealFlag());
				String sealBook = oumpurgePkgService.sealingOffenders(object.getOffenderId()!=null?object.getOffenderId().longValue():null, object.getOffenderBookId()!=null?object.getOffenderBookId().longValue():null, "BOOKING", object.getSealFlag(),userName);
				statusReason.put("P_STATUS", sealBook);
				
			}
			}catch (Exception e) {
				logger.error("whenTimerExpired",e);
				
			}
			if (statusReason != null && statusReason.get("P_STATUS") != null && !statusReason.get("P_STATUS").toString().isEmpty() && !statusReason.get("P_STATUS").toString().equals(FOUR) && !statusReason.get("P_STATUS").toString().equals(FIVE)) {
				String data = statusReason.get("P_STATUS").toString();
				if (data.contains("Cannot establish a savepoint in auto-commit mode.")) {
					data = "ORA-20998: Could not seal specified record. Error: ORA-20111: Offender record has been used by another session! Try again later.";
				}
				String getStatusReason = oumpurgeRepository.getSubString("Error While Sealing ", data);
				returnData.setSealFlag(getStatusReason);
				returnData.setOffenderId(BigDecimal.valueOf(3));
				return returnData;
			} else {
				if ("SEALBOOKING".equals(object.getStatusReason())) {
					if ((object.getSealFlag() != null || "N".equals(object.getSealFlag())) && statusReason.get("P_STATUS").toString().equals(FOUR)) {
						returnData.setOffenderId(BigDecimal.valueOf(4));
						return returnData;
					} else {
						returnData.setOffenderId(BigDecimal.valueOf(5));
						return returnData;
					}
				} else {
					if ((object.getSealFlag() != null || "N".equals(object.getSealFlag())) && statusReason.get("P_STATUS").toString().equals(FOUR)) {
						returnData.setOffenderId(BigDecimal.valueOf(4));
						return returnData;
					} else {
						returnData.setOffenderId(BigDecimal.valueOf(5));
						return returnData;
					}
				}
			}
		}
	}

	@Override
	public Integer getSealButtonAccessPermission(String userName) {
		Integer returnValue=0;
		List<SystemProfiles> systemProfileConfData=new ArrayList<SystemProfiles>();
		SystemProfiles searchRecord=new SystemProfiles();
		searchRecord.setProfileType("CLIENT");
		searchRecord.setProfileCode("SEAL_RECORDS");	
		systemProfileConfData=oumpurgeRepository.sysPflSearchSystemProfiles(searchRecord);
		if(!systemProfileConfData.isEmpty()) {
			if(systemProfileConfData.get(0).getProfileValue()!=null) {
				Integer accessStaffValue=oumpurgeRepository.getAccessStaffCount(Integer.valueOf(systemProfileConfData.get(0).getProfileValue()),userName);
				if(accessStaffValue>0) {
					returnValue=1;
				} else {
					returnValue = 0;
				}
			} else {
				returnValue = 0;
			}
			
		} else {
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public Integer getLvCountSealBookings(String rootOffenderId) {
		Integer recordCount = oumpurgeRepository.getLvCountSealBookings(rootOffenderId);
		if(recordCount > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}