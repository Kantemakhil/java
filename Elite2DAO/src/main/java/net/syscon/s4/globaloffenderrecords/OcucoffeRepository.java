package net.syscon.s4.globaloffenderrecords;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OcucoffeRepository
 */
public interface OcucoffeRepository {
	List<ReferenceCodes> rgIdentifierTypeRecordGroup();

	List<ReferenceCodes> rgAliasNameTypeRecordGroup();

	List<ReferenceCodes> rgOffSexRecordGroup();

	List<ReferenceCodes> rgOffRaceRecordGroup();

	List<ReferenceCodes> rgOffSuffixRecordGroup();

	List<ReferenceCodes> rgIdSourceRecordGroup();

	List<Offenders> aliasSearchOffenders(Offenders objOffenders);

	Offenders postInsertgetRootOffenderId(Offenders paramBean);

	ReferenceCodes whenNewRecordInstancedefaultSeqCur(ReferenceCodes paramBean);

	Integer aliasInsertOffenders(List<Offenders> lstOffenders);

	Long preInsertgetNextAliasDAO();

	List<Object> preInsertrecordEx(OffenderIdentifier paramBean);

	List<OffenderIdentifier> offIdSearchOffenderIdentifiers(OffenderIdentifier objOffenderIdentifiers)
			throws SQLException;

	ReferenceCodes postQueryreferenceCodesC(ReferenceCodes paramBean);

	Integer offIdInsertOffenderIdentifiers(List<OffenderIdentifier> lstOffenderIdentifiers);

	Object whenNewFormInstance(Dual paramBean);

	Object preInsertgetNextAlias(Dual paramBean);

	Object validateAliasescheckForDupOffCur(Offenders paramBean);

	List<Offenders> offsearchOffenders(Offenders objOffenders);

	List<Object> offOnCheckDeleteMasteroffIdAllCur(OffenderIdentifier paramBean);

	List<Object> checkPncExistsgetPncEx(OffenderIdentifier paramBean);

	List<Object> createFormGlobals(OmsModules paramBean);

	List<Object> aliasOnCheckDeleteMasteroffIdCur(OffenderIdentifier paramBean);

	SystemProfiles ageValidationvsRangecur(SystemProfiles paramBean);

	Object ageValidationvsAgecur(Date paramBean);

	List<Object> preInsertgetNextIdentifier(OffenderIdentifier paramBean);

	Object validateAliasescheckDupNameCur(Offenders paramBean);

	Long offInsertOffenders(List<Offenders> lstOffenders);

	List<Object> offOnCheckDeleteMasteraliasCur(Offenders paramBean);

	Integer offIdUpdateOffenderIdentifiers(List<OffenderIdentifier> lstOffenderIdentifiers);

	List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(OffenderIdentifier searchBean);

	List<ReferenceCodes> rgIdentifierTypeRgroup();

	Integer getOffenderMinAge(String caseload);

	Long checkOffenderIdDisplay(String offenderIdDisplay);
	
	Date ocucoffeGetCurrentDate();
	
	Boolean checkPncValidation(String identifiers);
	
	void offInsertExternalSearchOffenders(final List<Offenders> lstOffenders);
	
	BigDecimal fetchExternalId();
	
	String getIdDisplayProfileValue();
}
