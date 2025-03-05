package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ResponseEntityBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;

/**
 * Interface OcdaliasService
 */
public interface OcdaliasService {

	String offIdPreInsert(String params);

	VHeaderBlock getWorkingNameOffenderID(final Offenders params);

	List<Offenders> offNameSearchOffenders(Offenders objOffenders);

	List<OffenderIdentifier> offIdSearchOffenderIdentifiers(Offenders searchRecord);

	ResponseEntityBean offNameCommit(OffendersCommitBean commitBean);

	Integer offIdCommit(OffenderIdentifiersCommitBean commitBean);

	List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(Offenders searchBean);

	VHeaderBlock changeWorkingName(VHeaderBlock lstVHeaderBlock);

	List<ReferenceCodes> getGenderDescription();
	
	Integer offNameOnCheckDeleteMasteroffIdCur( Offenders aliasesBean);

	SystemProfiles vsRangeCursor();
}
