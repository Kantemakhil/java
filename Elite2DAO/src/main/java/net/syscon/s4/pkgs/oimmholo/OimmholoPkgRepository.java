package net.syscon.s4.pkgs.oimmholo;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.LivingUnits;

public interface OimmholoPkgRepository {

	String getNLuType(final Integer pLevel, final String pAgyLocId);

	String getDefaultLuDesc(final BigDecimal pParentLuId, final String pLivingUnitCode);

	List<LivingUnits> getLivingUnitsDetails(final BigDecimal livingUnitId);

	LivingUnits getChildTotals(final BigDecimal pParentLuId);

	List<LivingUnits> getLivUnitUpdCur(final BigDecimal livingUnitId);

	List<Object[]> getAllAttributesCur(final BigDecimal parentLivingUnitId);

	Integer agyIntLocProfiles(final BigDecimal livingUnitId);

	Integer updateLivingUnits(final BigDecimal lvCapacity, final BigDecimal lvCna, final BigDecimal operationCapacity, final BigDecimal livingUnitId, final String userName);

	Integer livingUnitsUpdate(final LivingUnits object);

	Integer livingUnitsUpdateElse(final LivingUnits object);
}
