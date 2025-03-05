package net.syscon.s4.pkgs.pims_weight;

import java.util.List;

import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.inst.legals.au.StaffWorkAssignmentsV1;
import net.syscon.s4.pkgs.PotConcurrencies;
import net.syscon.s4.pkgs.TempWeightings;

public interface PimsWeightRepository {

	Long getMaxAssSeqCur(final String pCaseloadType, final Long pOffenderBookId);

	String getCaseloadType(final String createUserId);

	String getSupLevelCur(final Long pOffenderBookId, final Long cpAssSeq);

	Long userEnv();

	List<StaffWorkAssignmentsV1> offaaasCur(final StaffMemberRoles bean);

	Long selectWeight(final String orderType, final String orderCode, final String pComponent, final String lvSupLevel,
			final String lvTimeServed);

	Integer insertTempWeightings(final TempWeightings bean);

	Integer insertTempWeightingsOne(final TempWeightings bean);

	List<TempWeightings> allRecsCur(final Long lvSessionId);

	List<PotConcurrencies> conCur(final String pOrderType, final String pOrderCode);

	List<TempWeightings> conTmpWeiCur(final TempWeightings bean);

	Long lvOverWeighting(final Long overriddenBy, final Long lvSessionId);

	Integer updatrWeightingsOne(final Long weighting, final Long roWid, final String userName);

	Integer updatrWeightingsTwo(final Long overriddenBy, final Long tmpWeiId, final Long lvSessionId,
			final String userName);

	Integer updatrWeightingsThree(final Long overriddenBy, final Long roWid, final String userName);

	Integer updateWeightingsFour(final Long weighting, final Long roWid, final String userName);

	Integer updateWeightingsFive(final Long allTmpWeiId, final Long conTmpWeiId, final Long lvSessionId,
			final String userName);

	Integer updateWeightingsSix(final Long overriddenBy, final Long roWid, final String userName);

	Integer updateWeightingsSeven(final Long weighting, final Long roWid, final String userName);

	Long calculatedWeighting(final Long lvSessionId);

	Integer deleteWeightings(final Long lvSessionId, final String userName);

}