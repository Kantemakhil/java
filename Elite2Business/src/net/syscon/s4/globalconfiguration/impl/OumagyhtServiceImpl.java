package net.syscon.s4.globalconfiguration.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumagyhtRepository;
import net.syscon.s4.globalconfiguration.OumagyhtService;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Class OumagyhtServiceImpl
 */
@Service
public class OumagyhtServiceImpl extends BaseBusiness implements OumagyhtService {

	@Autowired
	private OumagyhtRepository oumagyhtRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<AgencyLocations> agyLocExecuteQuery() {
		return oumagyhtRepository.agyLocExecuteQuery();

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<AgencyLocationAmendments> agyLocAmExecuteQuery(final AgencyLocationAmendments searchRecord) {
		return oumagyhtRepository.agyLocAmExecuteQuery(searchRecord);
	}
}