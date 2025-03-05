package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;

/**
 * Interface OmuerrcoRepository
 */
public interface OmuerrcoRepository {
	List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup();

	Integer offEscPreInsert();

	String cgfkchkOffEscOffEscRef(String paramBean);

	List<OffenderEscapes> offEscExecuteQuery(OffenderEscapes objOffEsc);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	Integer offEscUpdateOffenderEscapes(List<OffenderEscapes> lstOffEsc);

	List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup();

	Integer offEscInsertOffenderEscapes(List<OffenderEscapes> lstOffEsc);

	List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup();

	List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup();

	List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup();

	String cgfkchkOffArrestCodeRef(String arrDesc);

}
