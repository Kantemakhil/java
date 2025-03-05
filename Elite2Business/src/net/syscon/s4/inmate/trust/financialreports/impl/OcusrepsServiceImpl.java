package net.syscon.s4.inmate.trust.financialreports.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.trust.financialreports.OcusrepsRepository;
import net.syscon.s4.inmate.trust.financialreports.OcusrepsService;

/**
 * Class OcusrepsServiceImpl
 */
@Service
public class OcusrepsServiceImpl extends BaseBusiness implements OcusrepsService {

	@Autowired
	private OcusrepsRepository ocusrepsRepository;

	/**
	 * Creates new OcusrepsServiceImpl class Object
	 */
	public OcusrepsServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OmsModules> omsModulesExecuteQuery(final OmsModules searchRecord) {
		return ocusrepsRepository.omsModulesExecuteQuery(searchRecord);

	}
}