package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.im.beans.Corporates;

/**
 * Interface OtucpayeService
 */
public interface OtucpayeService {
	List<Corporates> corpExecuteQuery(Corporates objCorporates);

	// Integer corpCommit(CorporatescommitBean CommitBean) ;

}
