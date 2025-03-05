package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.PhonesCommitBean;

/**
 * Interface OcdccontService
 * 
 */
public interface OcdccontService {
	Object phonesPreInsert();

	List<Phones> phonesExecuteQuery(Phones objPhones);

	Integer phonesCommit(PhonesCommitBean commitBean);

	List<ReferenceCodes> rgPhoneTypeRecordGroup();

}
