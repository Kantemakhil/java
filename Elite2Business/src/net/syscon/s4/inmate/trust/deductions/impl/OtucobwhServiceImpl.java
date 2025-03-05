package net.syscon.s4.inmate.trust.deductions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.trust.deductions.OtucobwhRepository;
import net.syscon.s4.inmate.trust.deductions.OtucobwhService;

/**
 * Class OtucobwhServiceImpl
 */
@Service
public class OtucobwhServiceImpl extends BaseBusiness implements OtucobwhService {

	@Autowired
	private OtucobwhRepository otucobwhRepos;

	/**
	 * Creates new OtucobwhServiceImpl class Object
	 */
	public OtucobwhServiceImpl() {
		// OtucobwhServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderAdjustmentTxns> cowohExecuteQuery(final OffenderAdjustmentTxns searchRecord) {
		return otucobwhRepos.cowohExecuteQuery(searchRecord);

	}

}