package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.legals.OidhwdetRepository;
import net.syscon.s4.inst.legals.OidhwdetService;
import net.syscon.s4.inst.legals.beans.Charges;
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistory;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistoryCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetChargesCommitBean;
import net.syscon.s4.inst.legals.beans.HwdetCommitBean;

@Service
public class OidhwdetServiceImpl implements OidhwdetService {

	@Autowired
	OidhwdetRepository oidhwdetRepository;

	@Override
	public List<HoldWarrentDetainer> searchHoldWarrentDetainer(Long offenderBookId) {
		List<HoldWarrentDetainer> holdWarrentDetainerData = new ArrayList<>();
		holdWarrentDetainerData = oidhwdetRepository.searchHoldWarrentDetainer(offenderBookId);
		return holdWarrentDetainerData;
	}

	@Override
	public List<Charges> searchCharges(Long hwdId) {
		List<Charges> chargesData = new ArrayList<>();
		chargesData = oidhwdetRepository.searchCharges(hwdId);
		return chargesData;
	}

	@Override
	public Integer insertUpdateDeleteCourtReport(HwdetCommitBean hwDetCommitBean) {
		int liReturn = 0;
		if (hwDetCommitBean.getInsertList() != null && hwDetCommitBean.getInsertList().size() > 0) {
			List<HoldWarrentDetainer> recordSavingObject = new ArrayList<>();
			for (int i = 0; i < hwDetCommitBean.getInsertList().size(); i++) {
				final HoldWarrentDetainer hwDetData = hwDetCommitBean.getInsertList().get(i);
				if (hwDetCommitBean.getInsertList().get(i).getProbRevocFlag() == null) {
					hwDetData.setProbRevocFlag("N");
					hwDetData.setCreateUserId(hwDetCommitBean.getCreateUserId());
				}
				if (hwDetCommitBean.getInsertList().get(i).getProbRevocFlag() == "true") {
					hwDetData.setProbRevocFlag("Y");
				} else if (hwDetCommitBean.getInsertList().get(i).getProbRevocFlag() == "false") {
					hwDetData.setProbRevocFlag("N");
				}
				recordSavingObject.clear();
				recordSavingObject.add(hwDetData);
				liReturn = insertHwDetdata(recordSavingObject);
			}

		}

		if (hwDetCommitBean.getUpdateList() != null && hwDetCommitBean.getUpdateList().size() > 0) {
			if (hwDetCommitBean != null && hwDetCommitBean.getUpdateList().size() > 0) {
				hwDetCommitBean.getUpdateList().forEach(bo -> bo.setModifyUserId(hwDetCommitBean.getCreateUserId()));
			}
			liReturn = updateHwDetdata(hwDetCommitBean.getUpdateList());
		}
		
		if (hwDetCommitBean.getDeleteList() != null && hwDetCommitBean.getDeleteList().size() > 0) {
			for(HoldWarrentDetainer obj: hwDetCommitBean.getDeleteList()) {
				obj.setModifyUserId(hwDetCommitBean.getCreateUserId());
				
				List<HoldsWarantsHistory> deleteWarrantsHty = oidhwdetRepository.populateHistory(obj.getHoldWarrentId());
				if(deleteWarrantsHty!= null && deleteWarrantsHty.size()>0) {
					for (HoldsWarantsHistory object : deleteWarrantsHty) {
						object.setModifyUserId(hwDetCommitBean.getCreateUserId());
					}
					deleteHistoryRecord(deleteWarrantsHty);
				}
				List<Charges> deleteCharges = oidhwdetRepository.searchCharges(obj.getHoldWarrentId());
				if(deleteCharges!= null && deleteCharges.size()>0) {
					for (Charges chargeObj : deleteCharges) {
						chargeObj.setModifyUserId(hwDetCommitBean.getCreateUserId());
					}
					deleteHwdetCharges(deleteCharges);
				}
			}
			liReturn = oidhwdetRepository.deleteHwDetdata(hwDetCommitBean.getDeleteList());
		}
		return liReturn;
	}

	@Transactional
	private Integer insertHwDetdata(List<HoldWarrentDetainer> insertedRecord) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.insertHwDetdata(insertedRecord);
		return liReturn;
	}

	private Integer updateHwDetdata(List<HoldWarrentDetainer> updatedRecords) {
		int liReturn = 0;
		if (updatedRecords != null && updatedRecords.size() > 0) {
			List<HoldWarrentDetainer> recordSavingObject = new ArrayList<>();
			for (int i = 0; i < updatedRecords.size(); i++) {
				final HoldWarrentDetainer hwDetData = updatedRecords.get(i);
				if (updatedRecords.get(i).getProbRevocFlag() == null) {
					hwDetData.setProbRevocFlag("N");
				}
				if (updatedRecords.get(i).getProbRevocFlag() == "true") {
					hwDetData.setProbRevocFlag("Y");
				} else if (updatedRecords.get(i).getProbRevocFlag() == "false") {
					hwDetData.setProbRevocFlag("N");
				}
				recordSavingObject.clear();
				recordSavingObject.add(hwDetData);
				liReturn = updateHoldWarrantDet(recordSavingObject);
			}

		}

		return liReturn;
	}

	@Transactional
	private Integer updateHoldWarrantDet(List<HoldWarrentDetainer> updateHwdetList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.updateHwDetdata(updateHwdetList);
		return liReturn;
	}

	@Override
	public Integer insertUpdateDeleteHwdetCharges(HwdetChargesCommitBean hwdetChargesCommitBean) {
		Integer liReturn = 0;
		if (hwdetChargesCommitBean.getInsertList().size() > 0) {
			for(Charges obj : hwdetChargesCommitBean.getInsertList()) {
				obj.setCreateUserId(hwdetChargesCommitBean.getCreateUserId());
			}
			liReturn = insertHwdetCharges(hwdetChargesCommitBean.getInsertList());
		}
		if (hwdetChargesCommitBean.getUpdateList().size() > 0) {
			hwdetChargesCommitBean.getUpdateList().forEach(data ->
				data.setModifyUserId(hwdetChargesCommitBean.getCreateUserId())
			);
			liReturn = updateHwdetCharges(hwdetChargesCommitBean.getUpdateList());
		}
		if (hwdetChargesCommitBean.getDeleteList().size() > 0) {
			hwdetChargesCommitBean.getDeleteList().forEach(ele->ele.setModifyUserId(hwdetChargesCommitBean.getCreateUserId()));
			liReturn = deleteHwdetCharges(hwdetChargesCommitBean.getDeleteList());
		}
		return liReturn;
	}

	@Transactional
	private Integer insertHwdetCharges(List<Charges> insertHwdetChargesList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.insertHwdetCharges(insertHwdetChargesList);
		return liReturn;
	}

	@Transactional
	private Integer updateHwdetCharges(List<Charges> updatedHwdetChargesList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.updateHwDetCharges(updatedHwdetChargesList);
		return liReturn;
	}

	@Transactional
	private Integer deleteHwdetCharges(List<Charges> deletedHwdetChargesList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.deleteHwdetCharges(deletedHwdetChargesList);
		return liReturn;
	}

	@Override
	public List<HoldsWarantsHistory> populateHistory(Long hwdId) {
		return oidhwdetRepository.populateHistory(hwdId);
	}

	@Override
	public int updateHwdetHistory(HoldsWarantsHistoryCommitBean historyCommitBean) {
		Integer liReturn = 0;
		if (historyCommitBean.getInsertList().size() > 0) {
			historyCommitBean.getInsertList().forEach(data-> data.setCreateUserId(historyCommitBean.getCreateUserId()));
			liReturn = insertHistoryRecord(historyCommitBean.getInsertList());
		}
		if (historyCommitBean.getUpdateList().size() > 0) {
			historyCommitBean.getUpdateList().forEach(updateData ->updateData.setModifyUserId(historyCommitBean.getCreateUserId()));
			liReturn = updateHistoryRecord(historyCommitBean.getUpdateList());
		}
		if (historyCommitBean.getDeleteList().size() > 0) {
			liReturn = deleteHistoryRecord(historyCommitBean.getDeleteList());
		}
		return liReturn;
	}

	@Transactional
	private Integer deleteHistoryRecord(List<HoldsWarantsHistory> deleteList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.deleteHistoryRecord(deleteList);
		return liReturn;
	}

	@Transactional
	private Integer updateHistoryRecord(List<HoldsWarantsHistory> updateList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.updateHistoryRecord(updateList);
		return liReturn;
	}

	@Transactional
	private Integer insertHistoryRecord(List<HoldsWarantsHistory> insertList) {
		int liReturn = 0;
		liReturn = oidhwdetRepository.insertHistoryRecord(insertList);
		return liReturn;
	}

}
