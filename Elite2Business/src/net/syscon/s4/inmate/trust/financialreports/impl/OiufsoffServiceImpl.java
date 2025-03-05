package net.syscon.s4.inmate.trust.financialreports.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.trust.financialreports.OiufsoffRepository;
import net.syscon.s4.inmate.trust.financialreports.OiufsoffService;
import net.syscon.s4.pkgs.oiufsoff.OiufsoffPkgService;

@Service("OiufsoffService1")
public class OiufsoffServiceImpl extends BaseBusiness implements OiufsoffService {

	@Autowired
	private OiufsoffRepository oiufsoffRepository;
	
	@Autowired
	private OiufsoffPkgService oiufsoffpkgservice;

	/**
	 * Creates new OiufsoffServiceImpl class Object
	 */
	public OiufsoffServiceImpl() {
		// OiufsoffServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<OffenderTrustAccounts> vOffBkgPostQuery(final OffenderTrustAccounts paramBean) {
		List<OffenderTrustAccounts> offenderTrustAccountsList = new ArrayList<OffenderTrustAccounts>();
		return offenderTrustAccountsList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OiufsoffGetGeneralOffenders> vOffBkgExecuteQuery(final OiufsoffGetGeneralOffenders searchRecord) {
		//List<OiufsoffGetGeneralOffenders> result = oiufsoffRepository.vOffBkgExecuteQuery(searchRecord);		
		List<OiufsoffGetGeneralOffenders> result = oiufsoffpkgservice.getGeneralOffenders(searchRecord);
			result.forEach(data -> {
				String flag = oiufsoffRepository.vOffBkgPostQuery(searchRecord.getpCaseloadId(), data.getAgyLocId(),
						data.getRootOffenderId());
				data.setNbtActvTrustFlag(flag);
			});
		
		return result;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadId) {
		return oiufsoffRepository.cgfkAgyLocIdRecordGroup(caseloadId);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> cgfkHousingLevelOneRecordGroup(final String agyLocId) {
		return oiufsoffRepository.cgfkHousingLevelOneRecordGroup(agyLocId);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> cgfkHousingLevelTwoRecordGroup(final String agyLocId,
			final BigDecimal parentLivingUnitId) {
		return oiufsoffRepository.cgfkHousingLevelTwoRecordGroup(agyLocId, parentLivingUnitId);
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> cgfkHousingLevelThreeRecordGroup(final String agyLocId,
			final BigDecimal parentLivingUnitId) {
		return oiufsoffRepository.cgfkHousingLevelThreeRecordGroup(agyLocId, parentLivingUnitId);
	}

}