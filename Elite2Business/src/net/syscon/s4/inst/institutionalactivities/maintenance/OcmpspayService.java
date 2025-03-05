package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationCommitBean;

public interface OcmpspayService {

	List<ReferenceCodes> rgCompensationTypeRecorGroup(String programCategory);

	List<ReferenceCodes> rgCompensationCodeRecorGroup(Integer programId);

	List<programsPayBean> prgCategoryExecuteQuery();

	Integer prgCategoryCommit(programsPayCommitBean commitBean);

	List<programsPayCompensationBean> prgCampensationExecuteQuery(programsPayBean beanObj);

	Integer prgCampensationCommit(programsPayCompensationCommitBean commitBean);

	List<ProgramServices> listOfProgServices();

	List<ReferenceCodes> rgUnitRecordGroup();

}
