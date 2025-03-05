package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderLanguages;

/**
 * Interface OcdlangsRepository
 */
public interface OcdlangsRepository {

	List<ReferenceCodes> rgSecLangRecordGroup(String langCode, String langCode1);

	Integer offPrimLangDeleteOffenderLanguages(List<OffenderLanguages> list);

	Integer offPrimLangInsertOffenderLanguages(List<OffenderLanguages> list);

	Integer offPrimLangUpdateOffenderLanguages(List<OffenderLanguages> list);

	Integer offSecLangInsertOffenderLanguages(List<OffenderLanguages> list);

	Integer offSecLangUpdateOffenderLanguages(List<OffenderLanguages> list);

	List<ReferenceCodes> rgLangSkillsRecordGroup();

	Integer offSecLangDeleteOffenderLanguages(List<OffenderLanguages> list);

	List<Object> offBkgOnCheckDeleteMaster(OffenderLanguages paramBean);

	List<OffenderLanguages> offPrimLangExecuteQuery(OffenderLanguages obj);

	List<ReferenceCodes> rgPrefLangRecordGroup();

	List<OffenderLanguages> offSecLangExecuteQuery(OffenderLanguages obj);

	List<OffenderLanguages> prefLangWriteExecuteQuery(OffenderLanguages searchRecord);

	List<OffenderLanguages> prefLangSpeakExecuteQuery(OffenderLanguages searchRecord);

	Integer prefLangWriteInsertprefLangWrite(List<OffenderLanguages> insertList);

	Integer prefLangWriteUpdateprefLangWrite(List<OffenderLanguages> updateList);

	ReferenceCodes getPreferredDefault();

}
