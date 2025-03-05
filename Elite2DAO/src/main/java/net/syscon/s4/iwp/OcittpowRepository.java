package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransfer;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Interface OcittpowRepository
 */
public interface OcittpowRepository {
	List<AgencyLocations> dspDescriptionRecordGroup(final String currentCaseLoad);

	List<VExtOwnershipTransfer> agyLocIdFromRecordGroup(final String agyLocIdFrom);

	List<VExtOwnershipTransfer> transferredOffendersExecuteQuery(final String code);

	Integer agyLocIdToExecuteQuery(final String agyLocIdTo);

	String getProfileValue();

	Integer getOffenderId(Long offenderBookId,String userName);

	public Integer getCurTran(Integer v_offenderId, String agyLocIdTo);

	public Integer pimsFileTrackingCancelTransfer(Integer v_offenderId, Integer vOffenderFileSeq,VExtOwnershipTransfer veot);

	public Integer cancelFileTransferUpdateOparation(VExtOwnershipTransfer veot);

	public Integer deleteExtOwnershipTransfer(List<VExtOwnershipTransfer> list);

	public Integer curExists(Long offenderBookId, Long extTransferId);

	public Integer curTran(Integer v_offenderId, String agyLocIdTo);

	public Integer curLoc(Integer v_offenderId, String agyLocIdFrom);

	public String getVagylocIdTo(Long offenderBookId, Long extTransferId);

	public Integer agylocIdToUpdate(VExtOwnershipTransfer veot);

}
