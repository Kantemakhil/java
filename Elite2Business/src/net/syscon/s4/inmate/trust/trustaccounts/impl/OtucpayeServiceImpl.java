package net.syscon.s4.inmate.trust.trustaccounts.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.inmate.trust.trustaccounts.OtucpayeRepository;
import net.syscon.s4.inmate.trust.trustaccounts.OtucpayeService;

/**
 * Class OtucpayeServiceImpl
 */
@Service
public class OtucpayeServiceImpl extends BaseBusiness implements OtucpayeService {

	@Autowired
	private OtucpayeRepository otucpayeRepository;

	/**
	 * Creates new OtucpayeServiceImpl class Object
	 */
	public OtucpayeServiceImpl() {
		// OtucpayeServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<Corporates> corpExecuteQuery(final Corporates searchRecord) {
		final List<Corporates> returnList = otucpayeRepository.corpExecuteQuery(searchRecord);
		for (final Corporates obj : returnList) {
			if (obj.getCorporateId() != null) {
				final BigDecimal corporateId = obj.getCorporateId();
				final Phones returnObj = otucpayeRepository.postQuery(corporateId);
				if (returnObj.getPhoneNo() != null) {
					if (returnObj.getExtNo() != null) {
						obj.setTelephoneNo(returnObj.getPhoneNo() + " ext. " + returnObj.getExtNo());
					} else {
						obj.setTelephoneNo(returnObj.getPhoneNo());
					}
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCORP
	 *
	 * @
	 */
	// @Transactional
	// public Integer corpCommit(CorporatescommitBean CommitBean) throws
	// SQLException {
	// int liReturn = 0;
	// }

}