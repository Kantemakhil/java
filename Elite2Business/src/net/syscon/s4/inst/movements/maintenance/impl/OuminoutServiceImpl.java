package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.MovementReasonsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movements.maintenance.OuminoutRepository;
import net.syscon.s4.inst.movements.maintenance.OuminoutService;

/**
 * Class OuminoutServiceImpl
 */
@Service
public class OuminoutServiceImpl extends BaseBusiness implements OuminoutService {

	@Autowired
	private OuminoutRepository ouminoutRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<MovementReasons> moveRsnExecuteQuery(final MovementReasons searchRecord) {
		return ouminoutRepository.moveRsnExecuteQuery(searchRecord);

	}

	/**
	 * update and deletion of records from database table
	 *
	 * @param lstMOVE_RSN
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<MovementReasons> moveRsnCommit(final MovementReasonsCommitBean commitBean) {
		final List<MovementReasons> liReturnData = new ArrayList<>();
		final MovementReasons movementReasons = new MovementReasons();
		int liReturn = 0;

		if (!commitBean.getUpdateList().isEmpty()) {
			for (MovementReasons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ouminoutRepository.moveRsnUpdateMovementReasons(commitBean.getUpdateList());
		}

		if (!commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ouminoutRepository.moveRsnDeleteMovementReasons(commitBean.getDeleteList());
		}

		movementReasons.setReturnValue(liReturn);
		liReturnData.add(movementReasons);
		return liReturnData;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkMoveRsnInMovementReasRecordGroup() {
		List<ReferenceCodes> refList = ouminoutRepository.cgfkMoveRsnInMovementReasRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

	/**
	 * 
	 * Checking validation before Deleting a record
	 * 
	 */
	@Override
	public MovementReasons cgrichkMovementReasonsDeleteCheck(final MovementReasons paramBean) {

		Integer offenderDel = 0;
		Integer externalMov = 0;
		externalMov = ouminoutRepository.cgrichkMovementReasonsMovements(paramBean);
		offenderDel = ouminoutRepository.cgrichkMovementReasonsBooking(paramBean);
		paramBean.setOffenderDeleteCount(offenderDel);
		paramBean.setExternalMovmentCount(externalMov);
		return paramBean;
	}

}