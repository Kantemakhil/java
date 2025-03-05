package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.VTrustHeader;

/**
 * Interface OtusubadRepository
 */
public interface OtusubadRepository {
	List<VTrustHeader> vThaExecuteQuery(VTrustHeader objVTrustHeader);

	List<OffenderSubAcShadows> offSasExecuteQuery(OffenderSubAcShadows objOffenderSubAcShadows);

	ReferenceCodes cgfkchkOffSasOffSasRef(ReferenceCodes paramBean);

	String getTrustacntdesc(String trustAccountCode);

	List<VTrustHeader> getRootOffenderId(VTrustHeader searchBean);

}
