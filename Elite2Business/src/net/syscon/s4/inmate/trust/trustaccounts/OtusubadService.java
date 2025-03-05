package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSubAcShadows;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.im.beans.VTrustHeaderCommitBean;

/**
 * Interface OtusubadService
 */
public interface OtusubadService {

	List<VTrustHeader> vThaExecuteQuery(VTrustHeader objVTrustHeader);

	List<OffenderSubAcShadows> offSasExecuteQuery(OffenderSubAcShadows objOffenderSubAcShadows);

	ReferenceCodes cgfkchkOffSasOffSasRef(ReferenceCodes paramBean);

	List<VTrustHeader> getRootOffenderId(VTrustHeader searchBean);

}
