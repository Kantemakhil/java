package net.syscon.s4.pkgs.tag_ass_off_needs;

import net.syscon.s4.triggers.AssessedOffenderNeeds;

public interface TagAssOffNeedsService {
	Integer prInsProcedure(AssessedOffenderNeeds prOldRec, AssessedOffenderNeeds prNewRec);

	Integer prDelProcedure(AssessedOffenderNeeds prOldRec, AssessedOffenderNeeds prNewRec);

	Integer prUpdProcedure(AssessedOffenderNeeds prOldRec, AssessedOffenderNeeds prNewRec);

	String prLck(String ptPkColumnValues);

	String prTableLock(String pvTableName, String ptPkColumnNames, String ptPkColumnValues);

}
