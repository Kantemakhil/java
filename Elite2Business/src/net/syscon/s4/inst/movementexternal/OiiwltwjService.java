package net.syscon.s4.inst.movementexternal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2;
import net.syscon.s4.inst.movementexternal.beans.VTransferWaitingLists2CommitBean;
/**
 * Interface OiiwltwjService 
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
public interface OiiwltwjService  {
	ReferenceCodes GetParentCode(ReferenceCodes paramBean);

	List<VTransferWaitingLists2> vTwlExecuteQuery(String caseLoadId,String userName);

	List<AgencyLocations> cgfkVTwlAgencyLocationToRecordGroup();

	VHeaderBlock CgfkchkVTwlVTwlVOffBkg(VHeaderBlock paramBean) ;

	List<VTransferWaitingLists2> RefreshCheckSum(VTransferWaitingLists2 paramBean) ;

	List<ReferenceCodes> cgfkVTwlDspDescription3RecordGroup();

	List<ReferenceCodes> cgfkVTwlDspDescriptionRecordGroup();

	List<ReferenceCodes> rgCancelReasonRecordGroup();

	String vTwlCommit(VTransferWaitingLists2CommitBean CommitBean);
	
	AgencyLocations cgfkchkVTwlVTwlAgyLoc(String param);
}
