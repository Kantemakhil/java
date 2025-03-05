package net.syscon.s4.pkgs.tag_ass_off_needs;

import net.syscon.s4.triggers.AssessedOffenderNeeds;

public interface TagAssOffNeedsRepository {
	Integer prDel(AssessedOffenderNeeds prRec);

	Integer prIns(AssessedOffenderNeeds prNewRec);

	Integer prUpd(AssessedOffenderNeeds prRec);
}
