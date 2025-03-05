package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffStatusesPkgSpec;
import net.syscon.s4.common.beans.OffenderInstRequests;

public interface AfterUpdateOffInstReqService {

	OffStatusesPkgSpec afterUpdateOffInstReq(OffenderInstRequests newOffenderInstRequests);

	

}
