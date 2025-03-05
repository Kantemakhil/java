package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.MovementReasonsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movements.maintenance.OumemoveRepository;
import net.syscon.s4.inst.movements.maintenance.OumemoveService;

/**
 * Class OumemoveServiceImpl
 */
@Service
public class OumemoveServiceImpl extends BaseBusiness implements OumemoveService {

	@Autowired
	private OumemoveRepository oumemoveRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<MovementReasons> moveRsnExecuteQuery(final MovementReasons searchRecord) {
		return oumemoveRepository.moveRsnExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMOVE_RSN
	 */
	@Transactional
	public List<MovementReasons> moveRsnCommit(final MovementReasonsCommitBean commitBean) {
		final List<MovementReasons> liReturnData = new ArrayList<>();
		final MovementReasons movementReasons = new MovementReasons();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (MovementReasons bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumemoveRepository.moversnInsertMovementReasons(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (MovementReasons bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumemoveRepository.moveRsnUpdateMovementReasons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oumemoveRepository.moveRsnDeleteMovementReasons(commitBean.getDeleteList());
		}
		movementReasons.setReturnValue(liReturn);
		liReturnData.add(movementReasons);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkMoveRsnMovementReasonRecordGroup() {
		return oumemoveRepository.cgfkMoveRsnMovementReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> cgfkMoveRsnMovementTypeRecordGroup() {
		return oumemoveRepository.cgfkMoveRsnMovementTypeRecordGroup();

	}

	@Override
	public MovementReasons cgrichkMovementReasonsDeleteCheck(final MovementReasons paramBean) {
		Integer offenderDel = 0;
		Integer externalMov = 0;
		offenderDel = oumemoveRepository.cgrichkMovementReasonsScheduleCheck(paramBean);
		externalMov = oumemoveRepository.cgrichkMovementReasonsExterMovment(paramBean);
		paramBean.setOffenderDeleteCount(offenderDel);
		paramBean.setExternalMovmentCount(externalMov);
		return paramBean;
	}

}