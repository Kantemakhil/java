package net.syscon.s4.pkgs.oiufsoff;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;

public interface OiufsoffPkgService {
	String getCaseloadType(final String caseLodId);   

	String detmLivUnitId(final BigDecimal pLv1Id, final BigDecimal pLv2Id, final BigDecimal pLv3Id);

	List<OiufsoffGetGeneralOffenders> getInstitutionOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getCommunityOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getTrustOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getPayrollOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getCommissaryOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getGeneralOffenders(final OiufsoffGetGeneralOffenders bean);

}
