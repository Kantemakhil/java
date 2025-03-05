package net.syscon.s4.pkgs.ocmsuwpj;

import java.math.BigDecimal;

import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;

public interface OcmsuwpjPkgRepository {

	String getPlacementCur(BigDecimal corporateId);

	VCorporateAddresses getPlacementAddressCur(final BigDecimal corporateId, final BigDecimal serviceaddressId);

	VCorporateAddresses getPlacementAddressCurAylocId(final String agyLocId, final BigDecimal serviceaddressId);

}
