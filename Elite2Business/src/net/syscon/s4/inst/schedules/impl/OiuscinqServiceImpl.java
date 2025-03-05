package net.syscon.s4.inst.schedules.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.schedules.OiuscinqRepository;
import net.syscon.s4.inst.schedules.OiuscinqService;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

/**
 * Class OiuscinqServiceImpl
 */
@Service
public class OiuscinqServiceImpl extends BaseBusiness implements OiuscinqService {

	@Autowired
	private OiuscinqRepository oiuscinqRepository;

	/**
	 * Creates new OiuscinqServiceImpl class Object
	 */
	public OiuscinqServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public StaffMembers oiuscinqPostInsert(final StaffMembers paramBean) {
		final StaffMembers staffMembers = oiuscinqRepository.oiuscinqPostInsert(paramBean);
		return staffMembers;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		return oiuscinqRepository.offSchExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offSchCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

}