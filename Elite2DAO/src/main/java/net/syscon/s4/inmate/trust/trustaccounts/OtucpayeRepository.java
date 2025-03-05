package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.Corporates;

/**
 * Interface OtucpayeRepository
 */
public interface OtucpayeRepository {
	List<Corporates> corpExecuteQuery(Corporates objCorporates);

	Phones postQuery(BigDecimal corporateId);

}
