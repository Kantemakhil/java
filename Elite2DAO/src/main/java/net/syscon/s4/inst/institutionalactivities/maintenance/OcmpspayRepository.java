package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationBean;

public interface OcmpspayRepository {

	List<ReferenceCodes> rgCompensationCodeRecorGroup(Integer programId);

	List<ReferenceCodes> rgCompensationTypeRecorGroup(String programCategory);

	List<programsPayCompensationBean> prgCampensationExecuteQuery(programsPayBean beanObj);

	List<programsPayBean> prgCategoryExecuteQuery();

	Integer categoryInsert(List<programsPayBean> insertList);

	Integer categoryUpdate(List<programsPayBean> updateList);

	Integer campensationInsert(List<programsPayCompensationBean> insertList);

	Integer campensationUpdate(List<programsPayCompensationBean> updateList);

	List<ProgramServices> listOfProgServices();

	Integer compensatiponPreInsert(List<String> programCategory, List<Integer> programId, List<Integer> crsActyId);

	Integer categoryPreInsert(List<String> programCategory);

	Integer campensationDelete(List<programsPayCompensationBean> deleteList);

	List<ReferenceCodes> rgUnitRecordGroup();

}
