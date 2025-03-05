package net.syscon.s4.pkgs.oiufsoff;

import java.util.List;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;

public interface OiufsoffPkgRepository {
	String getCaseloadType(String caseLodId);

	List<OiufsoffGetGeneralOffenders> getInstitutionOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getCommunityOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getTrustOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getPayrollOffenders(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getPayrollOffendersOne(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getPayrollOffendersTwo(final OiufsoffGetGeneralOffenders bean);

	List<OiufsoffGetGeneralOffenders> getCommissaryOffenders(final OiufsoffGetGeneralOffenders bean);
}