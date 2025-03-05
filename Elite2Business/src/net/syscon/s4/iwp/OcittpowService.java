package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransfer;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcittpowService
 */
public interface OcittpowService {

	List<AgencyLocations> dspDescriptionRecordGroup(final String currentCaseLoad);

	List<VExtOwnershipTransfer> agyLocIdFromRecordGroup(final String agyLocIdFrom);

	List<VExtOwnershipTransfer> transferredOffendersExecuteQuery(final String code);

	Integer transferredOffendersCommit(List<VExtOwnershipTransfer> list,String userName);

	Integer whenCheckboxChanged(VExtOwnershipTransfer veot);

	Integer agyLocIdToExecuteQuery(final String agyLocIdTo);

}
