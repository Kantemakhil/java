package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderPptyConTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * interface OidvcontService
 */
public interface OidvcontService {
	List<Object> CgwhenNewFormInstance();

	List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	Integer conTxCommit(OffenderPptyConTxnsCommitBean CommitBean);

	Integer itmTxCommit(OffenderPptyItemTxnsCommitBean CommitBean);

	List<OffenderPptyItemTxns> itmTxExecuteQuery(OffenderPptyItemTxns objOffenderPptyItemTxns);

	AgencyInternalLocations cgfkchkOffConOffConPpty(AgencyInternalLocations paramBean);

	ReferenceCodes cgfkchkConTxConTxnRefCo(ReferenceCodes paramBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers objOffenderPptyContainers);

	List<OffenderPptyConTxns> conTxExecuteQuery(OffenderPptyConTxns objOffenderPptyConTxns);

	ReferenceCodes cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkConTxActionCodeRecordGroup(String propertyContainerId);

	List<OffenderPptyItems> offPItemExecuteQuery(OffenderPptyItemTxns searchRecord);

	Integer updateOffenderPptyContainers(OffenderPptyContainers commitBean);

}
