package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Interface OcugltrdService
 */
public interface OcugltrdService {
	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);
}
