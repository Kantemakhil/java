package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubadRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubadService;

/**
 * Class OtusubadServiceImpl
 */
@Service
public class OtusubadServiceImpl extends BaseBusiness implements OtusubadService {

	@Autowired
	private OtusubadRepository otusubadRepository;

	/**
	 * Creates new OtusubadServiceImpl class Object
	 */
	public OtusubadServiceImpl() {
		// OtusubadServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfkchkOffSasOffSasRef(final ReferenceCodes paramBean) {
		return otusubadRepository.cgfkchkOffSasOffSasRef(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VTrustHeader> vThaExecuteQuery(final VTrustHeader searchRecord) {
		return otusubadRepository.vThaExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderSubAcShadows> offSasExecuteQuery(final OffenderSubAcShadows searchRecord) {

		final List<OffenderSubAcShadows> returnList = otusubadRepository.offSasExecuteQuery(searchRecord);
		for (final OffenderSubAcShadows obj : returnList) {
			final String trustacntdesc = otusubadRepository.getTrustacntdesc(obj.getTrustAccountCode());
			if (trustacntdesc != null) {
				obj.setTrustAccountCode(trustacntdesc);
			}

		}
		return returnList;

	}

	@Override
	public List<VTrustHeader> getRootOffenderId(final VTrustHeader searchBean) {
		return otusubadRepository.getRootOffenderId(searchBean);

	}


}