package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;

public interface OcmxpstmService {

	ReferenceCodes refCodeCondExecuteQuery(final String code);

	List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchRecord);

	Integer refCodeCondCommit(ReferenceCodesCommitBean commitBean);
}
