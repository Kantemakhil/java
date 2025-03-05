package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.Statutes;

/**
 * Interface OimstatuRepository
 */
public interface OimstatuRepository {

	List<Statutes> statExecuteQuery(Statutes objStatutes);

	Statutes statInsertStatutes(List<Statutes> lstStatutes);

	Statutes statDeleteStatutes(List<Statutes> lstStatutes);

	Statutes statUpdateStatutes(List<Statutes> lstStatutes);

	List<OffenceResultCodes> getInactiveStatutes(OffenceResultCodes searchRecord);

}
