package net.syscon.s4.cf.offendertransactions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.offendertransactions.OcugltrdRepository;
import net.syscon.s4.cf.offendertransactions.OcugltrdService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Class OcugltrdServiceImpl
 */
@Service
public class OcugltrdServiceImpl extends BaseBusiness implements OcugltrdService {

	@Autowired
	private OcugltrdRepository ocugltrdRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<GlTransactions> glTxnExecuteQuery(GlTransactions searchRecord) {
		return ocugltrdRepository.glTxnExecuteQuery(searchRecord);

	}
}