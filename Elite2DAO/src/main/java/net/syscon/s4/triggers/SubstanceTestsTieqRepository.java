package net.syscon.s4.triggers;

public interface SubstanceTestsTieqRepository {
	SubstanceTests getSubstanceTests(Long offenderBookId, Integer sampleSeq, Integer testSeq);

	String vSubCur(Long offenderBookId, Integer sampleSeq);
}
