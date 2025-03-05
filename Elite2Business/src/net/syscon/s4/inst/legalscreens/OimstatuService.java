package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.StatutesCommitBean;

/**
 * Interface OimstatuService
 */
public interface OimstatuService {
	List<Statutes> statCommit(StatutesCommitBean commitBean);

	List<Statutes> statExecuteQuery(Statutes objStatutes);
	
	List<OffenceResultCodes> getInactiveStatutes(OffenceResultCodes objStatutes);
}
