package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomesCommitBean;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasuresCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.iwp.OcmeventRepository;
import net.syscon.s4.iwp.OcmeventService;
import net.syscon.s4.triggers.EventMeasureOutcomesTjnService;
import net.syscon.s4.triggers.EventMeasuresTjnService;

/**
 * Class OcmeventServiceImpl
 */
@Service
public class OcmeventServiceImpl extends BaseBusiness implements OcmeventService {

	@Autowired
	private OcmeventRepository ocmeventRepository;
	
	@Autowired
	private EventMeasuresTjnService eventMeasuresTjnService;
	
	@Autowired
	private EventMeasureOutcomesTjnService eventMeasureOutcomesTjnService;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public long ocmeventPreInsert() {
		return ocmeventRepository.ocmeventPreInsert();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<EventMeasures> scheduleExecuteQuery() {
		return ocmeventRepository.scheduleExecuteQuery();

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSCHEDULE
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer scheduleCommit(final EventMeasuresCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (EventMeasures eventMeasures : commitBean.getInsertList()) {
				long eventMeasureId = ocmeventPreInsert();
				eventMeasures.setEventMeasureId(eventMeasureId);
				eventMeasures.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmeventRepository.scheduleInsertEventMeasures(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (EventMeasures bean : commitBean.getUpdateList()) {
				//aading user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmeventRepository.scheduleUpdateEventMeasures(commitBean.getUpdateList());	
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmeventRepository.scheduleDeleteEventMeasures(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<EventMeasureOutcomes> outcomeExecuteQuery(final EventMeasureOutcomes searchRecord) {
		return ocmeventRepository.outcomeExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOUTCOME
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer outcomeCommit(final EventMeasureOutcomesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (EventMeasureOutcomes bean : commitBean.getInsertList()) {
				//aading user to bean
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmeventRepository.outcomeInsertEventMeasureOutcomes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (EventMeasureOutcomes bean : commitBean.getUpdateList()) {
				//aading user to bean
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setRowIdNo(Long.valueOf(bean.getRowId()));
			}
			liReturn = ocmeventRepository.outcomeUpdateEventMeasureOutcomes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmeventRepository.outcomeDeleteEventMeasureOutcomes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return ocmeventRepository.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup(final String eventType) {
		return ocmeventRepository.rgSubTypeRecordGroup(eventType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgOutcomeCodeRecordGroup() {
		return ocmeventRepository.rgOutcomeCodeRecordGroup();

	}

	public List<ReferenceCodes> rgStaticRecordGroup() {
		return ocmeventRepository.rgStaticRecordGroup();
	}

}