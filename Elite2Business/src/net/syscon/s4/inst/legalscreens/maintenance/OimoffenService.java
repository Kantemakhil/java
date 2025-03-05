package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.im.beans.StatutesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenceIndicatorsCommitBean;

/**
 * Interface OimoffenService
 */
public interface OimoffenService {
	List<Offence> oimoffenPreInsert(Offence paramBean);

	List<ReferenceCodes> offIndLovRecordGroup();

	List<Statutes> statStatutesCodeRecordGroup();

	List<ReferenceCodes> cgfkAlwOtOffenceTypeRecordGroup();

	AllowableOffenceTypes ofnOnCheckDeleteMaster(AllowableOffenceTypes paramBean);

	Offence statOnCheckDeleteMaster(Offence paramBean);

	Integer alwOtCommit(AllowableOffenceTypesCommitBean commitBean);

	List<HoCodes> rgHoCodeRecordGroup();

	List<AllowableOffenceTypes> alwOtExecuteQuery(AllowableOffenceTypes objAllowableOffenceTypes);

	List<ReferenceCodes> ofnHoOffSubclRecordGroup();

	List<Statutes> statExecuteQuery(Statutes objStatutes);

	Offence ofnCommit(OffenceCommitBean commitBean);

	List<Offence> ofnExecuteQuery(Offence objOffences);

	List<ReferenceCodes> cgfkOfnSeverityRankingRecordGroup();

	List<OffenceIndicator> offIndExecuteQuery(OffenceIndicator objOffenceIndicators);

	Integer statCommit(StatutesCommitBean commitBean);

	Integer offIndCommit(OffenceIndicatorsCommitBean commitBean);

	Offence oimoffenStatOncheckdeletemasterOffences(final Offence searchBean);

	List<OffenceByStatute> getOffencesOnStatute(Offence paramBean);
	
	List<OffenceByStatute> getOffencesOnStatuteList();

	Boolean isChgDependOnOffences(Integer offenceId);

}
