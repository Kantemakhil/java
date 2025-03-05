package net.syscon.s4.inst.movements.housingchanges;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OidehlocRepository
 */
public interface OidehlocRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer vOffBkgUpdateVHeaderBlock(List<VHeaderBlock> lstVHeaderBlock);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();

	String checkOffStatus(BigDecimal offenderBookId);

	String chkNonAssociation(BigDecimal offenderId, BigDecimal livingUnitId);

	boolean allowOverride(String userId);

	String checkSecurity(BigDecimal offenderBookId, BigDecimal livingUnitId);

	Integer updOffBook(VHeaderBlock objVHeaderBlock, VHeaderBlock objHeaderBlock);

	OffenderBookings gettingOldData(BigDecimal offenderBookId);
	
	Boolean checkOverrideLocation(String userId);

}
