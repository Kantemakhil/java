package net.syscon.s4.inst.transportation.maintenance.impl;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.transportation.maintenance.OimsglenRepository;
import net.syscon.s4.inst.transportation.maintenance.OimsglenService;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengths;
import net.syscon.s4.inst.transportation.maintenance.beans.AgencySegmentLengthsCommitBean;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * Class OimsglenServiceImpl
 */
@Service
public class OimsglenServiceImpl implements OimsglenService {

	@Autowired
	private OimsglenRepository oimsglenRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */

	public List<AgencySegmentLengths> agencySegmentLengthsExecuteQuery(AgencySegmentLengths searchRecord) {

		List<AgencySegmentLengths> list = oimsglenRepository.agencySegmentLengthsExecuteQuery(searchRecord);
		if (Optional.ofNullable(list).isPresent()) {
			for (AgencySegmentLengths bean : list) {

			}
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_SEGMENT_LENGTHS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer agencySegmentLengthsCommit(AgencySegmentLengthsCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimsglenRepository.agencySegmentLengthsUpdateAgencySegmentLengths(commitBean.getUpdateList());
		}
		return liReturn;
	}

}