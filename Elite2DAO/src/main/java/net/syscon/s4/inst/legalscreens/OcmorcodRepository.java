package net.syscon.s4.inst.legalscreens;

import java.util.List;

import net.syscon.s4.im.beans.OffenceResultCodes;

/**
 * Interface OcmorcodRepository
 */
public interface OcmorcodRepository {
	List<OffenceResultCodes> resCodExecuteQuery(OffenceResultCodes paramBean);

	Integer resCodUpdateOffenceResultCodes(List<OffenceResultCodes> lstOffResCodes);

	Integer resCodInsertOffenceResultCodes(List<OffenceResultCodes> lstOffResCodes);

	String getChargeStatus(final String chargeCode);
}
