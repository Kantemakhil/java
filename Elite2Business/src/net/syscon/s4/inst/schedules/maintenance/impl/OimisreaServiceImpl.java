package net.syscon.s4.inst.schedules.maintenance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.schedules.bean.InternalScheduleReasonsCommitBean;
import net.syscon.s4.inst.schedules.maintenance.OimisreaRepository;
import net.syscon.s4.inst.schedules.maintenance.OimisreaService;

/**
 * Class OimisreaServiceImpl
 */
@Service
public class OimisreaServiceImpl extends BaseBusiness implements OimisreaService {

	@Autowired
	private OimisreaRepository oimisreaRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public Long intSrKeyDelrec(final InternalScheduleReasons paramBean) {
		return oimisreaRepository.intSrKeyDelrec(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<InternalScheduleReasons> intSrExecuteQuery(final InternalScheduleReasons searchRecord) {
		return oimisreaRepository.intSrExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 */
	@Transactional
	public InternalScheduleReasons intSrCommit(final InternalScheduleReasonsCommitBean commitBean) {
		final InternalScheduleReasons returnData = new InternalScheduleReasons();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (InternalScheduleReasons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimisreaRepository.intSrInsertInternalScheduleReasons(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (InternalScheduleReasons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimisreaRepository.intSrUpdateInternalScheduleReasons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimisreaRepository.intSrDeleteInternalScheduleReasons(commitBean.getDeleteList());
		}
		if (liReturn == 1) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("0");
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgIntSchRsnRecordGroup() {
		return oimisreaRepository.rgIntSchRsnRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgIntSchTypeRecordGroup() {
		return oimisreaRepository.rgIntSchTypeRecordGroup();
	}
}