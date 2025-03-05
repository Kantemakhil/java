package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.IdentifierType;


public interface OcuccideRepository {
	List<CaseIdentifiers> caseIdentifiers(Long caseId);
	List<IdentifierType> identifierType();
	//int[] updateIdentifierData(String caseId,IdentifierCommitBean holdsBeanCommit);	
	int[]  insertIdentifierData(List<CaseIdentifiers> insertList);
	int[] updateIdentifierData(List<CaseIdentifiers> updateRecord);
	int[] deleteIdentifierData(List<CaseIdentifiers> deleteList);
}
