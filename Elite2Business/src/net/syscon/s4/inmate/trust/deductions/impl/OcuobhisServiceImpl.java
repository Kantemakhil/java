package net.syscon.s4.inmate.trust.deductions.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.OffenderObligationHty;
import net.syscon.s4.inmate.beans.OffenderObligationHtyCommitBean;
import net.syscon.s4.inmate.trust.deductions.OcuobhisRepository;
import net.syscon.s4.inmate.trust.deductions.OcuobhisService;
import net.syscon.s4.pkgs.trust.TrustService;

/**
 * Class OcuobhisServiceImpl
 */
@Service
public class OcuobhisServiceImpl extends BaseBusiness implements OcuobhisService {

	@Autowired
	private OcuobhisRepository ocuobhisRepository;
	@Autowired
	private TrustService trust;

	/**
	 * Creates new OcuobhisServiceImpl class Object
	 */
	public OcuobhisServiceImpl() {
		// OcuobhisServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public SystemProfiles whenNewFormInstance(final SystemProfiles paramBean) {
		SystemProfiles objSystem = null;
		paramBean.setProfileType("CLIENT");
		paramBean.setProfileCode("CASE_OBLIGN");
		objSystem =  ocuobhisRepository.ocuobhisWhenNewFormInstance(paramBean);

		if (objSystem != null) {
		//	final String caseloadType = ocuobhisRepository.getCaseloadType(paramBean.getProfileValue());
			final String caseloadType = trust.getCaseloadType(paramBean.getProfileValue());
			objSystem.setProfileValue2(caseloadType);
		}
		return objSystem;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		return ocuobhisRepository.createFormGlobals(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderObligationHty> offOblHtyExecuteQuery(final OffenderObligationHty searchRecord) {
		return ocuobhisRepository.offOblHtyExecuteQuery(searchRecord);
	}


}