package net.syscon.s4.inst.legals;

import java.util.List;
import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
import net.syscon.s4.inst.legals.beans.IdentifierType;


public interface OcuccideService {

	List<CaseIdentifiers> caseIdentifiers(Long caseId);
	List<IdentifierType> identifierType();
	int[]  updateIdentifierData(String userId, IdentifierCommitBean holdsBeanCommit);
}
