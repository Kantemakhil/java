package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;

/**
 * Interface OimoffenRepository
 */
public interface OimoffenRepository {
	List<ReferenceCodes> offIndLovRecordGroup();

	List<Statutes> statStatutesCodeRecordGroup();

	AllowableOffenceTypes oimoffenPreInsert(AllowableOffenceTypes paramBean);

	Integer alwOtUpdateAllowableOffenceTypes(List<AllowableOffenceTypes> lstAllowableOffenceTypes);

	List<OffenceIndicator> ofnOnCheckDeleteMaster(OffenceIndicator paramBean);

	Offence ofnInsertOffences(List<Offence> lstOffences);

	List<HoCodes> rgHoCodeRecordGroup();

	Integer offIndUpdateOffenceIndicators(List<OffenceIndicator> lstOffenceIndicators);

	Offence oimoffenPreInsert(Offence paramBean);

	List<AllowableOffenceTypes> alwOtExecuteQuery(AllowableOffenceTypes objAllowableOffenceTypes);

	AllowableOffenceTypes ofnOnCheckDeleteMaster(AllowableOffenceTypes paramBean);

	List<Statutes> statExecuteQuery(Statutes objStatutes);

	Offence ofnDeleteOffences(List<Offence> lstOffences);

	Integer ofnUpdateOffences(List<Offence> lstOffences);

	List<ReferenceCodes> cgfkOfnSeverityRankingRecordGroup();

	Integer offIndInsertOffenceIndicators(List<OffenceIndicator> lstOffenceIndicators);

	Integer alwOtInsertAllowableOffenceTypes(List<AllowableOffenceTypes> lstAllowableOffenceTypes);

	List<ReferenceCodes> cgfkAlwOtOffenceTypeRecordGroup();

	Integer alwOtDeleteAllowableOffenceTypes(List<AllowableOffenceTypes> lstAllowableOffenceTypes);

	Integer offIndDeleteOffenceIndicators(final List<OffenceIndicator> lstOffenceIndicators);

	List<OffenderCharges> ofnOnCheckDeleteMaster(OffenderCharges paramBean);

	List<ReferenceCodes> ofnHoOffSubclRecordGroup();

	Offence statOnCheckDeleteMaster(Offence paramBean);

	List<Offence> ofnExecuteQuery(Offence objOffences);

	Object insIndId(Dual paramBean);

	List<OffenceIndicator> offIndExecuteQuery(OffenceIndicator objOffenceIndicators);

	List<OffenceIndicator> oimoffenPreInsert(OffenceIndicator paramBean);

	String gettingLegaslatingId(String legislatingBodyCode);

	Long offenceIndicatorId();

	Long oimoffenOfnOncheckdeletemasterOffenderCharges(Offence paramBean);

	Long oimoffenStatOncheckdeletemasterOffences(Offence paramBean);

	List<OffenceByStatute> getOffencesOnStatute(Offence paramBean);
	
	List<OffenceByStatute> getOffencesOnStatuteList();

	Boolean isChgDependOnOffences(Integer offenceId);
}
