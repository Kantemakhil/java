package net.syscon.s4.triggers;

public interface OffenderSentCondActsT2Repository {
	OffenderSentCondActs getOffenderSentCondActs(OffenderSentCondActs offenderSentCondActs);

	Integer insert(OffenderSentCondActs offenderSentCondActs);
}
