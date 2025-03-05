package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderLanguages;
import net.syscon.s4.im.beans.OffenderLanguagesCommitBean;

/**
 * Interface OcdlangsService
 */
public interface OcdlangsService {

	Integer offPrimLangCommit(OffenderLanguagesCommitBean commitBean);

	Integer offSecLangCommit(OffenderLanguagesCommitBean commitBean);

	List<ReferenceCodes> rgSecLangRecordGroup(String langCode, String langCode1);

	List<OffenderLanguages> offPrimLangExecuteQuery(OffenderLanguages obj);

	List<ReferenceCodes> rgPrefLangRecordGroup();

	List<OffenderLanguages> offSecLangExecuteQuery(OffenderLanguages obj);

	List<Object> offBkgOnCheckDeleteMaster(OffenderLanguages paramBean);

	Integer prefLangWriteCommit(OffenderLanguagesCommitBean commitBean);

	List<ReferenceCodes> rgLangSkillsRecordGroup();

	List<OffenderLanguages> prefLangWriteExecuteQuery(OffenderLanguages searchBean);

	List<OffenderLanguages> prefLangSpeakExecuteQuery(OffenderLanguages searchBean);

	ReferenceCodes getPreferredDefault();

}
