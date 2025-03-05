package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.OffenceResultCodesCommitBean;

/**
 * Interface OcmorcodService
 */
public interface OcmorcodService {
	List<OffenceResultCodes> resCodExecuteQuery(OffenceResultCodes objOffResCodes);

	List<OffenceResultCodes> resCodCommit(OffenceResultCodesCommitBean commitBean);
}
