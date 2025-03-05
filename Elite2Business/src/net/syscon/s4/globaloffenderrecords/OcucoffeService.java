package net.syscon.s4.globaloffenderrecords;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderDetail;
import net.syscon.s4.im.beans.OffenderDetails;
import net.syscon.s4.im.beans.OffenderIdentifiersCommitBean;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OcucoffeService
 **/
public interface OcucoffeService {
	List<ReferenceCodes> rgIdentifierTypeRgroup();

	List<ReferenceCodes> rgAliasNameTypeRecordgroup();

	List<ReferenceCodes> rgOffRaceRecordGroup();

	List<ReferenceCodes> rgIdSourceRecordGroup();

	List<Offenders> aliasSearchOffenders(Offenders objOffenders);

	List<ReferenceCodes> rgOffSuffixRecordGroup();

	ReferenceCodes postQueryreferenceCodesC(ReferenceCodes paramBean);

	SystemProfiles ageValidationvsRangecur(SystemProfiles paramBean);

	List<Object> checkPncExistsgetPncEx(OffenderIdentifier paramBean);

	Object ageValidationvsAgecur(Date paramBean);

	List<Object> offOnCheckDeleteMasteroffIdAllCur(OffenderIdentifier paramBean);

	ReferenceCodes whenNewRecordInstancedefaultSeqCur(ReferenceCodes paramBean);
	
	OffenderDetails createOffenders(final List<OffenderDetail> offenderDetails, final String userName);

	Integer aliasInsertOffenders(List<Offenders> lstOffenders);

	List<Object> aliasOnCheckDeleteMasteroffIdCur(OffenderIdentifier paramBean);

	List<OffenderIdentifier> offIdSearchOffenderIdentifiers(OffenderIdentifier objOffenderIdentifiers)
			throws SQLException;

	Object validateAliasescheckForDupOffCur(Offenders paramBean);

	List<Object> offOnCheckDeleteMasteraliasCur(Offenders paramBean);

	Object preInsertgetNextAlias(Dual paramBean);

	List<Object> preInsertrecordEx(OffenderIdentifier paramBean);

	String offIdInsertOffenderIdentifiers(List<OffenderIdentifier> lstOffenderIdentifiers);

	List<Object> preInsertgetNextIdentifier(OffenderIdentifier paramBean);

	Object validateAliasescheckDupNameCur(Offenders paramBean);

	List<Offenders> offsearchOffenders(Offenders searchRecord);

	Object whenNewFormInstance(Dual paramBean);

	List<Object> createFormGlobals(OmsModules paramBean);

	Long offInsertOffenders(List<Offenders> lstOffenders);

	String offIdUpdateOffenderIdentifiers(List<OffenderIdentifier> lstOffenderIdentifiers);

	List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(OffenderIdentifier paramBean);

	Offenders postInsertgetRootOffenderId(Offenders paramBean);

	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	List<ReferenceCodes> rgOffSexRecordGroup();

	String insertUpdateDeleteOffenderIdentifiers(final OffenderIdentifiersCommitBean commitBean);

	Integer getOffenderMinAge(String caseload);

	String checkOffenderIdDisplay(String offenderIdDisplay);
	
	Date ocucoffeGetCurrentDate();
}
