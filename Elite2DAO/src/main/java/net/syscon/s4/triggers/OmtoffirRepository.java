package net.syscon.s4.triggers;

import net.syscon.s4.legalorders.OffenderCommunityFiles;

public interface OmtoffirRepository {

	Integer insertOffenderFilesTrig(OffenderCommunityFiles offenderCommunityFiles);

	Integer insertOffenderFilesTrig1(OffenderCommunityFiles offenderCommunityFiles);

	OffenderCommunityFiles getOffenderCommunityFiles(OffenderCommunityFiles offenderCommunityFiles);

}
