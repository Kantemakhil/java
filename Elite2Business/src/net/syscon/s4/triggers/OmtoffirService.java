package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.legalorders.OffenderCommunityFiles;

public interface OmtoffirService {
	Integer omtoffirTgr(List<OffenderCommunityFiles> offenderCommunityFilesList);
}
