package net.syscon.s4.triggers;

public interface OffenderOasysSectionsT1Repository {
	OffenderOasysSections getOffenderOasysSections(Long offenderBookId, Integer planSeq, String sectionCode);

	Integer weightedScore(String sectionCode, Integer rawRcore);

	Double summaryRatio(Integer weightedScore, String sectionCode);
}
