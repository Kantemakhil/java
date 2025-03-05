package net.syscon.s4.inst.movements.proposedmovements;

import java.util.List;

import net.syscon.s4.common.beans.OffenderOicSanctions;

/**
 * Interface OiusanctRepository
 */
public interface OiusanctRepository {

	List<OffenderOicSanctions> offenderOicSanctionsExecuteQuery(OffenderOicSanctions objOffenderOicSanctions);

}
