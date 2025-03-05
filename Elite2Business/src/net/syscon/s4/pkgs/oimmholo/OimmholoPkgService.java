package net.syscon.s4.pkgs.oimmholo;

import java.math.BigDecimal;

import net.syscon.s4.im.beans.LivingUnits;

public interface OimmholoPkgService {

	String getNewLuType(final LivingUnits bean);

	LivingUnits defaultLivingUnitDesc(final LivingUnits objSearchDao);

	Integer updateParentCapAndCna(final BigDecimal livingUnitId,String userName);

	Integer insertChildAllLuProfiles(final LivingUnits object);

	Integer actDeactChildLu(final LivingUnits object);
}
