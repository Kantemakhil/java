package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.Offenders;

public interface SubstanceTestResultsTcnService {

	Integer SubstanceTestResultsTcn(SubstanceTestResults substanceTestResults, CaseNotes caseNotesObj,Offenders offenders,String userName);
}
