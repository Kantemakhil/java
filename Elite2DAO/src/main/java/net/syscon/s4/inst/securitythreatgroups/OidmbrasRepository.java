package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderStgAssociations;

/**
 * Interface OidmbrasRepository
 */
public interface OidmbrasRepository {
	Integer offenderStgAssociationsInsertOffenderStgAssociations(List<OffenderStgAssociations> lstOffenderStgAss);

	Long offenderStgAssociationsPreInsert(Long paramBean);

	List<ReferenceCodes> rgReasonCodeRecordGroup();

	Integer offenderStgAssociationsDeleteOffenderStgAssociations(List<OffenderStgAssociations> lstOffenderStgAss);

	List<OffenderStgAssociations> offenderStgAssociationsExecuteQuery(OffenderStgAssociations objOffenderStgAss);

	Integer offenderStgAssociationsUpdateOffenderStgAssociations(List<OffenderStgAssociations> lstOffenderStgAss);

}
