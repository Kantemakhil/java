package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.inmate.beans.GlTransactions;
/**
 * Interface OtugltrdService 
 */
public interface OtugltrdService  {
	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);


}
