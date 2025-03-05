package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.common.beans.OffenderOicSanctions;

/**
 * Interface OiusanctService
 */
public interface OiusanctService {
	List<OffenderOicSanctions> offenderOicSanctionsExecuteQuery(OffenderOicSanctions objOffenderOicSanctions);

}
