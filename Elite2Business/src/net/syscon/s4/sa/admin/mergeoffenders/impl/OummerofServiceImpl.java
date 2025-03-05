package net.syscon.s4.sa.admin.mergeoffenders.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreService;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestService;
import net.syscon.s4.sa.admin.mergeoffenders.OummerofRepository;
import net.syscon.s4.sa.admin.mergeoffenders.OummerofService;
import net.syscon.s4.sa.admin.mergeoffenders.OumtrnbkService;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;


/**
 * Class OummerofServiceImpl@Service
 */
@Service
public class OummerofServiceImpl extends BaseBusiness implements OummerofService {

	private static Logger logger = LogManager.getLogger(OummerofServiceImpl.class.getName());

	@Autowired
	private OummerofRepository oummerofRepository;

	@Autowired
	private MergeTransactionRequestService mergeTransactionRequestService;

	@Autowired
	private MergeOffenderCoreService mergeOffenderCoreService;
	
	@Autowired
	private OumtrnbkService oumtrnbkService;
	
	@Autowired
	private MergeLogService mergeLogService;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;
	
	/**
	 * Creates new OummerofServiceImpl class Object
	 */
	public OummerofServiceImpl() {
	}

	public List<OffenderBookings> offBkgOnCheckDeleteMaster(OffenderBookings paramBean) {
		return oummerofRepository.offBkg2OnCheckDeleteMaster(paramBean);
	}

	public String offBooksPostQuery(OffenderBookings paramBean) {
		return oummerofRepository.offBooksPostQuery(paramBean);
	}

	public List<OffenderBookings> offBkg2OnCheckDeleteMaster(OffenderBookings paramBean) {
		return oummerofRepository.offBkg2OnCheckDeleteMaster(paramBean);
	}

	public List<ReferenceCodes> offBooks2PostQuery(ReferenceCodes paramBean) {
		return oummerofRepository.offBooks2PostQuery(paramBean);
	}

	public List<OffenderBookings> offBooksExecuteQuery(OffenderBookings searchRecord) {
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		Integer havingCountTrust = 0;
		returnList = oummerofRepository.offBooksExecuteQuery(searchRecord);
		for (OffenderBookings offenderBookings : returnList) {
			havingCountTrust = oummerofRepository.getTrustAccoutFlag(searchRecord);
			if (havingCountTrust > 0) {
				offenderBookings.setTrustAccountFlag("Y");
			} else {
				offenderBookings.setTrustAccountFlag("N");
			}
			offenderBookings.setCgnbtBookingStatus(oummerofRepository.offBooksPostQuery(offenderBookings));
		}
		return returnList;

	}

	public List<OffenderBookings> offBooks2ExecuteQuery(OffenderBookings searchRecord) {
		return oummerofRepository.offBooksExecuteQuery(searchRecord);

	}

	public List<VHeaderBlock> vOffBkgExecuteQuery(final VHeaderBlock searchRecord) {
		List<VHeaderBlock> returnList=new ArrayList<VHeaderBlock>();
		List<VHeaderBlock> finalReturnList=new ArrayList<VHeaderBlock>();
		returnList=oummerofRepository.vOffBkgExecuteQuery(searchRecord);
		finalReturnList=removeSealOffGlobelHeader(returnList,searchRecord.getCreateuserId());
		return returnList;

	}

	@Override
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		return oummerofRepository.rgAssignmentReasonRecordGroup();

	}

	//@Transactional(rollbackFor = Exception.class)
	public String manualCreateRequest(final MergeTransactionBean bean) {
		Integer liReturn = 0;
		Boolean result = null;
		List<MergeTransactionBean> saveList = new ArrayList<>();
		bean.setRequestStatusCode("PENDING");
		logger.info(this.getClass().getName() + " getMergeTransactionID");
		bean.setpMergeTransactionId(oummerofRepository.getMergeTransactionID());
		bean.setTransactionSource("MANUAL");
		bean.setTransactionType("MERGE");
		bean.setCreateUserId(bean.getCreateUserId());
		saveList.add(bean);
		if (!saveList.isEmpty()) {
			logger.info(this.getClass().getName() + " insertMergeTransactionRecord");
			try {
				liReturn = oummerofRepository.insertMergeTransactionRecord(saveList);
			} catch (Exception e) {
				logger.error(
						"Exception occured in " + this.getClass().getName() + " in method insertMergeTransactionRecord",
						e);
			}
		}
		mergeTransactionRequestService.setStatusToInprogress(bean.getpMergeTransactionId().longValue(), "INPROGRESS");
		try {
					result = mergeOffenderCoreService.mergeOffenders(bean.getpMergeTransactionId().longValue(),
					bean.getpFromRootOffId().longValue(), bean.getpFromOffBookId(), bean.getpToRootOffId().longValue(),
					bean.getpToOffBookId(), "MANUAL", "MERGE", bean.getCreateUserId());
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method mergeOffenders", e);
			
		}
		if (result == true) {
			mergeTransactionRequestService.setStatusToCompleted(bean.getpMergeTransactionId().longValue(), "COMPLETED");
		} else {
			mergeLogService.error("manual_create_request: ERROR : Error Update Merge Transaction Request",
					bean.getpMergeTransactionId().longValue(), bean.getCreateUserId());
			mergeTransactionRequestService.setStatusToFailed(bean.getpMergeTransactionId().longValue(), "FAILED");
		}
		if (liReturn == 1 && result == true) {
			return "success";
		} else {
			return "fail";
		}

	}

	@Transactional(rollbackFor = Exception.class)
	public Integer chkOffendersForTransfer(final MergeTransactionBean bean) {
		return oumtrnbkService.chkOffendersForTransfer(bean);
	}

	@Override
	public Long getNewOffId(Long offBookId) {
		return oummerofRepository.getNewOffId(offBookId);
	}

	private List<VHeaderBlock> removeSealOffGlobelHeader(List<VHeaderBlock> updatedList, String userId) {
		List<VHeaderBlock> resultList = new ArrayList<VHeaderBlock>();
		Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			resultList = updatedList.stream().filter(object -> object.getSealFlag()!= null && !object.getSealFlag().equals("Y"))
					.collect(Collectors.toList());
		} else {
			resultList.addAll(updatedList);
			}
		return resultList;
	}
}