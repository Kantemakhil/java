package net.syscon.s4.pkgs.non_association;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ProgramsNonAssocTmp;
import net.syscon.s4.inst.movements.beans.OffRec;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;

public interface NonAssociationService {

	String chkNaIntLocConflict(final Long pOffenderId, final Long pOffenderBookId, final Integer pInternalLocId);

	List<Offenders> getStgNaIntLoc(final Long pOffenderBookId, final Integer pInternalLocId);

	List<Offenders> getNaIntLoc(final Long pOffenderId, final Integer pInternalLocId);

	List<AgyIntLocProfiles> getParentNsTypeLoc(final Integer pInternalLocId, final String flag);

	Map<String, Object> chkNaPrgSrvConflictRt(final ProgramsNonAssocTmp offPrg,final String coursePhase);

	Map<String, Object> getStgNaPrgSrv(final Long pOffenderBookId, final Long pCrsActyId, final List<OffRec> listOffInfoType);

	Map<String, Object> getNaPrgSrvRealtime(final Long pOffenderId, final Long pCrsActyId, final Date pOffStrtDate, final Date pOffenderEndDate);

	Map<String, Object> getConflictWarning(final Long offenderId, final List<OffRec> pOffInfo);

	String addMsg(final String pMsg, final String pAddMsg);

	String formatOffRec(final OffRec lvOffRec);

	String formatOffList(final List<OffRec> lvOffInfo);

	Integer getCountForLivUnit(final ReserveBedLocations searchBean);

	Integer addToProgramList(final VOffenderPrgObligations vOff, final String userName);

	List<Offenders> getNonAssociations(final BigDecimal offId, final BigDecimal livingUnitId);

	Boolean chkNaLivUnitConflict(final BigDecimal livingUnitId, final Long offenderId, final String nameType);

	Map<String, Object> getStgNonAssMsg(final BigDecimal livingUnitId, final BigDecimal offId, final String lvEnemyFoundFlag);

	Integer clearTempList(String modifyUserId);	

	Integer removeFromProgramList(final VOffenderPrgObligations vOff);

	String checkNonAssociation(final BigDecimal offenderId, final BigDecimal livingUnitId);

	String checkStgNonAssociation(final BigDecimal livingUnitId, final BigDecimal offenderId, final String lEnemyFoundFlag);

	String checkSecurity(final BigDecimal offenderBookId, final BigDecimal livingUnitId);
	
	String chkNaBetweenOffenders(final Long pOffenderBookId, final Long pVisOffenderBookId);
	
	public Integer isOffenderExists(String pOffIdDisplay);
	
	public List<OffenderNaDetails> getNonAssociationOffenderList(Integer offenderBookId);
	
	public List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId, BigDecimal livingUnitId, String livingUnitCode);
	
	public List<OffenderStgAffiliations> getOffendersOfNonAssociationGroup(BigDecimal offenderBookId);
	
	public String checkAgyNonAssociation(Long offenderId, String agyLocId);
	
	public Boolean chkAgyConflict(String pAgyLoc,Long offenderBookId, String nsType);
	
	public String checkAnyNonAssociation(Long offenderId);
	
}
