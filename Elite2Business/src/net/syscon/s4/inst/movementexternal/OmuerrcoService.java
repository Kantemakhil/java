package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;

/**
 * Interface OmuerrcoService
 */
public interface OmuerrcoService {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup();

	List<OffenderEscapes> offEscExecuteQuery(OffenderEscapes objOffEsc);

	String cgfkchkOffEscOffEscRef(ReferenceCodes paramBean);

	Integer offEscCommit(OffenderEscapesCommitBean commitBean);

	Object offEscPreInsert();

	List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup();

}
