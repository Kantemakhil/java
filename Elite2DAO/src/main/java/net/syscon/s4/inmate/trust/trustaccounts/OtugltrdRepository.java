package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.inmate.beans.GlTransactions;
/**
 * Interface OtugltrdRepository
 */
public interface OtugltrdRepository {
	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	Integer getCalcn(GlTransactions searchRecord);

	Integer getCgl(GlTransactions searchRecord);

}
