
package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.movements.proposedmovements.OidpeximRepository;
import net.syscon.s4.inst.movements.proposedmovements.OidpeximService;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmnts;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedMvmntsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.OffenderMovementDetails;
import net.syscon.s4.pkgs.tag_transport.TagTransportService;

/**
 * Class OidpeximServiceImpl
 */
@Service
public class OidpeximServiceImpl implements OidpeximService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidpeximServiceImpl.class.getName());

	@Autowired
	OidpeximRepository oidpeximRepository;

	@Autowired
	TagTransportService tagTransportService;
	
	

	/**
	 * Fetch the records from database table
	 *
	 * @param objOffenderProposedMvmnts
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderProposedMvmnts> propMoveExecuteQuery(OffenderProposedMvmnts Offpropmvts) {

		List<OffenderProposedMvmnts> returnList = oidpeximRepository.propMoveExecuteQuery(Offpropmvts);
	
		
		return returnList;

	}

	public Integer postinsertProposedMvmnts(List<OffenderProposedMvmnts>  insertList) {
		Integer liReturn = 0;
		for (OffenderProposedMvmnts bean : insertList) {
			OffenderMovementDetails insrtObj = new OffenderMovementDetails();
			insrtObj.setOffenderBookId(bean.getOffenderBookId()!=null ? bean.getOffenderBookId().longValue():null);
			insrtObj.setMovementSeq(bean.getMovementSeq()!=null ? bean.getMovementSeq().longValue():null);
			insrtObj.setTxnStatus(ApplicationConstants.NEW);
			insrtObj.setStatusCode(ApplicationConstants.NEW);
			insrtObj.setRecordedBy(bean.getCreateUserId());
			insrtObj.setRecordedDate(bean.getCreateDatetime());
			insrtObj.setCreateDatetime(bean.getCreateDatetime());
			insrtObj.setCreateUserId(bean.getCreateUserId());
			insrtObj.setAppRsn(ApplicationConstants.DOT);
			insrtObj.setTxnRsn(ApplicationConstants.DOT);
			liReturn = tagTransportService.insertOffMovDtls(insrtObj);
		}
		return liReturn;

	}
	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Integer proposedMvmntsCommite(OffenderProposedMvmntsCommitBean commitBean) {
		
		int listReturn = 0;
		List<OffenderProposedMvmnts> listObj= new ArrayList<>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(propmvmts -> {
				propmvmts.setCreateUserId(commitBean.getCreateUserId());
			});
			for (OffenderProposedMvmnts bean : commitBean.getInsertList()) {
				int movSeqId = tagTransportService.getMovementSeq(bean.getOffenderBookId());
				bean.setMovementSeq(movSeqId);
				listObj = new ArrayList<>();
				Date eventDateTime = bean.getEventDate();
				if(bean.getEventTime()!=null) {
					eventDateTime.setHours(bean.getEventTime().getHours());
					eventDateTime.setMinutes(bean.getEventTime().getMinutes());
					bean.setEventDate(eventDateTime);
				}
				listObj.add(bean);
				listReturn = oidpeximRepository.proposedMvmntsInsert(listObj);
			}
			if (listReturn == 1) {
				postinsertProposedMvmnts(commitBean.getInsertList());
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> {
				Date eventDateTime = bean.getEventDate();
				if (bean.getEventTime() != null) {
					eventDateTime.setHours(bean.getEventTime().getHours());
					eventDateTime.setMinutes(bean.getEventTime().getMinutes());
					bean.setEventDate(eventDateTime);
				}
			});
			listReturn = oidpeximRepository.proposedMvmntsUpdate(commitBean.getUpdateList());
		}
		return listReturn;
		
	
	}
	
	@Override
	public List<ReferenceCodes> fromAgyRecordGroup() {
		return oidpeximRepository.fromAgyRecordGroup();
	}
	@Override
	public List<ReferenceCodes> rgMoveReasonRecordGroup(final String movementType) {
		return oidpeximRepository.rgMoveReasonRecordGroup(movementType);
	}

	@Override
	public List<ReferenceCodes> rgMoveTypeRecordGroup() {
		return oidpeximRepository.rgMoveTypeRecordGroup();
	}

	@Override
	public List<ReferenceCodes> rgAgyRecordGroup() {
		return oidpeximRepository.rgAgyRecordGroup();
	}

	@Override
	public OffenderMovementDetails getStatuses(OffenderProposedMvmnts searchBean) {
		
		OffenderMovementDetails returnObj = new OffenderMovementDetails();
		List<OffenderMovementDetails> appObj = tagTransportService.latestStatuses(ApplicationConstants.APP, searchBean.getOffenderBookId().intValue(), searchBean.getMovementSeq().intValue());
		
		if (appObj.size() > 0) {
			returnObj.setStatusCode(appObj.get(0).getStatusCode());
			if (!"ApplicationConstants.DOT".equals(appObj.get(0).getAppRsn())) {
				returnObj.setAppRsn(appObj.get(0).getAppRsn());
			}
		}
		List<OffenderMovementDetails> txnObj = tagTransportService.latestStatuses(ApplicationConstants.TXN,searchBean.getOffenderBookId().intValue(), searchBean.getMovementSeq().intValue());
		if (txnObj.size() > 0) {
			returnObj.setTxnStatus(txnObj.get(0).getTxnStatus());
			if (!"ApplicationConstants.DOT".equals(txnObj.get(0).getTxnRsn())) {
				returnObj.setTxnRsn(txnObj.get(0).getTxnRsn());
			}
		}
		return returnObj;

	}


	@Override
	public List<OffenderExternalMovements> getExtInCur() {
		List<OffenderExternalMovements> returnList= new ArrayList<OffenderExternalMovements>();
		try {
			returnList = oidpeximRepository.getExtInCur();
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + "getExtInCur()", e);
		}
		return returnList;
	}
	

	@Override
	public List<OffenderExternalMovements> offExtMovExecuteQuery(OffenderExternalMovements Offextmvts) {
		
		return oidpeximRepository.offExtMovExecuteQuery(Offextmvts);
	}

	
}