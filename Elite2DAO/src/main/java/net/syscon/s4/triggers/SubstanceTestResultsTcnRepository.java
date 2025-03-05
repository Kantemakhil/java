package net.syscon.s4.triggers;

public interface SubstanceTestResultsTcnRepository {

	SubstanceTests vSubCur(Long offenderBookId, Integer sampleSeq);

}
