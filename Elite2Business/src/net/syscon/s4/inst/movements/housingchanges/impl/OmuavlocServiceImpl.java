package net.syscon.s4.inst.movements.housingchanges.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movements.housingchanges.OmuavlocRepository;
import net.syscon.s4.inst.movements.housingchanges.OmuavlocService;
import net.syscon.s4.inst.movements.housingchanges.beans.VLivUnits;

/**
 * Class OmuavlocServiceImpl
 */
@Service
public class OmuavlocServiceImpl extends BaseBusiness implements OmuavlocService {

	@Autowired
	private OmuavlocRepository omuavlocRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuavlocServiceImpl.class.getName());

	/**
	 * Creates new OmuavlocServiceImpl class Object
	 */
	public OmuavlocServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VLivUnits> livUnitExecuteQuery(final CaseloadAgencyLocations searchRecord) {
		return omuavlocRepository.livUnitExecuteQuery(searchRecord);

	}

}