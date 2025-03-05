package net.syscon.s4.iwp.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.VExtOwnershipTransfer;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcittpowRepository;
import net.syscon.s4.iwp.OcittpowService;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingService;
import net.syscon.s4.triggers.OmtoffirService;
import net.syscon.s4.triggers.OmtofsbService;

/**
 * Class OcittpowServiceImpl
 */
@Service
public class OcittpowServiceImpl extends BaseBusiness implements OcittpowService {

	@Autowired
	private OcittpowRepository ocittpowRepository;
	@Autowired
	private PimsFileTrackingService pimsFileTrackingService;
	@Autowired
	private OmtoffirService omtoffirService;
	@Autowired
	private OmtofsbService omtofsbService;
	
	private static String YFLAG = "Y";

	/**
	 * This method is used to execute a record group
	 */
	@Transactional
	public List<AgencyLocations> dspDescriptionRecordGroup(final String currentCaseLoad) {
		return ocittpowRepository.dspDescriptionRecordGroup(currentCaseLoad);
	}

	/**
	 * This method is used to execute a record group
	 */
	@Transactional
	public List<VExtOwnershipTransfer> agyLocIdFromRecordGroup(final String agyLocIdFrom) {
		return ocittpowRepository.agyLocIdFromRecordGroup(agyLocIdFrom);
	}

	/**
	 * Fetch the records from database table
	 */
	@Transactional
	public List<VExtOwnershipTransfer> transferredOffendersExecuteQuery(final String code) {
		return ocittpowRepository.transferredOffendersExecuteQuery(code);
	}

	@Transactional
	public Integer agyLocIdToExecuteQuery(final String agyLocIdTo) {
		return ocittpowRepository.agyLocIdToExecuteQuery(agyLocIdTo);
	}

	/**
	 * This method is Used To perform Save operation
	 */
	@Transactional
	public Integer transferredOffendersCommit(List<VExtOwnershipTransfer> list, String userName) {
		int liReturn = 0;
		int transFlag = 0;
      list.forEach(bean->{bean.setCreateUserId(userName);
                          bean.setModifyUserId(userName);
                          });
                          
		for (VExtOwnershipTransfer veot : list) {
			transFlag = whenCheckboxChanged(veot);
			if (transFlag != 1) {
				liReturn = deleteTransfer(list);
				onUpdate(veot);
				if (liReturn > 0) {
					liReturn = 1;
				}
			}
		}
		return liReturn;
	}

	/*
	 * 3RD Procedure DELETE_TRANSFER
	 */
	public Integer deleteTransfer(List<VExtOwnershipTransfer> list) {
		int count = 0;
		int deot = 0;

		for (VExtOwnershipTransfer veot : list) {
			if (YFLAG.equals(veot.getTransferFlag())) {
				cancelFileTransfer(veot);

				Integer vExists = curExists(veot.getOffenderBookId(), veot.getExtTransferId());
				if (vExists > 0) {
					deot = deleteExtOwnershipTransfer(list);
				}
			}
		}
		if (deot > 0) {
			count = 1;
		}
		return count;
	}

	/*
	 * Cancel File Transfer Procedure
	 * 
	 */
	public void cancelFileTransfer(VExtOwnershipTransfer veot) {
		String profileValue = ocittpowRepository.getProfileValue();

		if (YFLAG.equals(profileValue)) {

			Integer v_offenderId = getOffenderId(veot.getOffenderBookId(),veot.getCreateUserId());

			Integer vOffenderFileSeq = getCurTran(v_offenderId, veot.getAgyLocIdTo());

			if (vOffenderFileSeq != 0) {
				pimsFileTrackingCancelTransfer(v_offenderId, vOffenderFileSeq, veot);
				omtofsbService.deleteOffenderFileTrigger(veot.getModifyUserId());
				cancelFileTransferUpdateOparation(veot);
				
			}
		}
	}

	/*
	 * pims FileTracking Cancel Transfer Procedure
	 */

	public Integer pimsFileTrackingCancelTransfer(Integer v_offenderId, Integer vOffenderFileSeq,
			VExtOwnershipTransfer veot) {
		OffenderFileTransactions bean=new OffenderFileTransactions();
		bean.setOffenderFileSeq(vOffenderFileSeq);
		bean.setOffenderId(v_offenderId);
		bean.setTransactionDate(veot.getTransferDate());
		bean.setAgyLocIdFrom(veot.getAgyLocIdFrom());
		bean.setAgyLocIdTo(null);
		bean.setStaffIdFrom(veot.getAssStaffId());
		bean.setNonOfficeFrom(null);
		bean.setNonOfficerTo(null);
		bean.setFileTransComment(null);
		int count = 0;
		count = pimsFileTrackingService.cancelTransfer(bean, veot.getCreateUserId());

		return count;
	}

	/*
	 * Cancel FileTransfer Update Oparation
	 */

	public Integer cancelFileTransferUpdateOparation(VExtOwnershipTransfer veot) {
		int count = 0;
		count = ocittpowRepository.cancelFileTransferUpdateOparation(veot);
		OffenderCommunityFiles obj=new OffenderCommunityFiles();
		List<OffenderCommunityFiles> data = new ArrayList<>();
			obj.setOffenderId(veot.getV_offenderId());
			obj.setOffenderFileSeq(veot.getvOffenderFileSeq().intValue());
			obj.setHoldingAgyLocId(veot.getAgyLocIdFrom());
			obj.setNonOfficerStatus(null);
			obj.setCreateUserId(veot.getCreateUserId());
		data.add(obj);
		
		omtoffirService.omtoffirTgr(data);
		return count;
	}

	/*
	 * second Procedure DELETE_EXT_OWNERSHIP_TRANSFER
	 */
	public Integer deleteExtOwnershipTransfer(List<VExtOwnershipTransfer> list) {
		int count = 0;
		count = ocittpowRepository.deleteExtOwnershipTransfer(list);
		return count;
	}

	/*
	 * Funcation cancelTrnf
	 */
	public String cancelTrnf(List<VExtOwnershipTransfer> list) {
		String vBCancelTrnf = "false";

		for (VExtOwnershipTransfer veot : list) {
			if (YFLAG.equals(veot.getTransferFlag())) {
				vBCancelTrnf = "true";
			}
		}
		return "vBCancelTrnf";
	}

	/**
	 * This method is used to getting Profile vlaue
	 */
	public String getProfileValue() {
		return ocittpowRepository.getProfileValue();
	}

	public Integer getCurTran(Integer v_offenderId, String agyLocIdTo) {
		Integer count = 0;
		count = ocittpowRepository.getCurTran(v_offenderId, agyLocIdTo);
		return count;
	}

	public Integer curExists(Long offenderBookId, Long extTransferId) {
		return ocittpowRepository.curExists(offenderBookId, extTransferId);
	}

	/*
	 * 4th Funcation When_Checkbox_Changed_2
	 */

	public Integer whenCheckboxChanged(VExtOwnershipTransfer veot) {
		int count = 0;
		String profileValue = ocittpowRepository.getProfileValue();
		if (YFLAG.equals(profileValue)) {
			if (YFLAG.equals(veot.getTransferFlag())) {
				Integer v_offenderId = getOffenderId(veot.getOffenderBookId(),veot.getCreateUserId());
				Integer vTran = curTran(v_offenderId, veot.getAgyLocIdTo());
				Integer vLoc = curLoc(v_offenderId, veot.getAgyLocIdFrom());
				if (vTran == 0 && vLoc == 0) {
					veot.setTransferFlag("false");
					count = 1;
				}
			}
		}
		return count;
	}

	public Integer getOffenderId(Long offenderBookId,String userName) {
		return ocittpowRepository.getOffenderId(offenderBookId,userName);
	}

	public Integer curTran(Integer v_offenderId, String agyLocIdTo) {
		int count = 0;
		count = ocittpowRepository.curTran(v_offenderId, agyLocIdTo);
		return count;
	}

	public Integer curLoc(Integer v_offenderId, String agyLocIdFrom) {
		int count = 0;
		count = ocittpowRepository.curLoc(v_offenderId, agyLocIdFrom);
		return count;
	}

	/* on_Update Procedure */
	public Integer onUpdate(VExtOwnershipTransfer veot) {
		int count = 0;
		String VagylocIdTo = ocittpowRepository.getVagylocIdTo(veot.getOffenderBookId(), veot.getExtTransferId());
		veot.setVagylocIdTo(VagylocIdTo);

		if (!VagylocIdTo.equals(veot.getAgyLocIdTo())) {
			count = ocittpowRepository.agylocIdToUpdate(veot);
		}
		return count;
	}

}