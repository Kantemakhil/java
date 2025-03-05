package net.syscon.s4.inst.offenderissuestracking.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.offenderissuestracking.OiuincreRepository;
import net.syscon.s4.inst.offenderissuestracking.OiuincreService;

/**
 * Class OiuincreServiceImpl
 */
@Service
public class OiuincreServiceImpl extends BaseBusiness implements OiuincreService {

	@Autowired
	private OiuincreRepository oiuincreRepository;

	/**
	 * Creates new OiuincreServiceImpl class Object
	 */
	public OiuincreServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final OmsModules omsModules = oiuincreRepository.createFormGlobals(paramBean);
		return omsModules;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(final Integer rootOffenderId) {
		return oiuincreRepository.agencyIncidentsExecuteQuery(rootOffenderId);

	}

}