package net.syscon.s4.inst.transportation.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.OidfixadRepository;
import net.syscon.s4.inst.transportation.maintenance.OidfixadService;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssets;
import net.syscon.s4.inst.transportation.maintenance.beans.FixedAssetsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.OidfixadCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Vehicles;
import net.syscon.s4.inst.transportation.maintenance.beans.VehiclesCommitBean;

/**
 * Class OidfixadServiceImpl
 */
@Service
public class OidfixadServiceImpl implements OidfixadService {

	@Autowired
	private OidfixadRepository oidfixadRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<FixedAssets> faExecuteQuery(FixedAssets searchRecord) {
		return oidfixadRepository.faExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer faCommit(FixedAssetsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			liReturn = oidfixadRepository.faInsertFixedAssets(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			liReturn = oidfixadRepository.faUpdateFixedAssets(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidfixadRepository.faDeleteFixedAssets(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Vehicles> vehExecuteQuery(Vehicles searchRecord) {
		return oidfixadRepository.vehExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVEH
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vehCommit(VehiclesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		int lVehicleIdCount = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Vehicles ele : commitBean.getInsertList()) {
				lVehicleIdCount = oidfixadRepository.vehicleIdCount(ele.getVehicleId());
				if (lVehicleIdCount > 0) {
					return 2;
				}
				liReturn = oidfixadRepository.vehInsertVehicles(commitBean.getInsertList());
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oidfixadRepository.vehUpdateVehicles(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * common commit Service method() for both FixedAssets and Vehicle
	 */
	@Transactional
	@Override
	public Integer oidfixadCommonSave(OidfixadCommitBean commitBean) {

		Integer returnVal = 0;
		if (commitBean != null) {
			if ((commitBean.getFixedAssetsCommitBean() != null && commitBean.getVehiclesCommitBean() != null)
					|| (commitBean.getFixedAssetsCommitBean() != null)) {

				if ((commitBean.getFixedAssetsCommitBean().getInsertList() != null
						&& commitBean.getFixedAssetsCommitBean().getInsertList().size() > 0)
						&& (commitBean.getVehiclesCommitBean().getInsertList() != null
								&& commitBean.getVehiclesCommitBean().getInsertList().size() > 0)) {
					Long assetId = oidfixadRepository.genAssetId("ASSET_ID");

					commitBean.getFixedAssetsCommitBean().getInsertList().forEach(value -> {
						value.setCreateUserId(commitBean.getCreateUserId());
						value.setAssetId(assetId);
					});
					commitBean.getVehiclesCommitBean().getInsertList().forEach(value -> {
						value.setCreateUserId(commitBean.getCreateUserId());
						value.setAssetId(assetId);
					});
					returnVal = faCommit(commitBean.getFixedAssetsCommitBean());
					returnVal = vehCommit(commitBean.getVehiclesCommitBean());
				} else {
					if ((commitBean.getFixedAssetsCommitBean().getInsertList() != null
							&& commitBean.getFixedAssetsCommitBean().getInsertList().size() > 0)) {
						commitBean.getFixedAssetsCommitBean().getInsertList().forEach(value -> {
							value.setCreateUserId(commitBean.getCreateUserId());
							value.setAssetId(oidfixadRepository.genAssetId("ASSET_ID"));
						});

						returnVal = faCommit(commitBean.getFixedAssetsCommitBean());
					}
					if(commitBean.getVehiclesCommitBean().getInsertList() != null) {
						commitBean.getVehiclesCommitBean().getInsertList().forEach(value -> {
							value.setCreateUserId(commitBean.getCreateUserId());
							value.setAssetId(commitBean.getFixedAssetsCommitBean().getUpdateList().get(0).getAssetId());
						});
						returnVal = vehCommit(commitBean.getVehiclesCommitBean());
					}
				}
				if ((commitBean.getFixedAssetsCommitBean().getUpdateList() != null
						&& commitBean.getFixedAssetsCommitBean().getUpdateList().size() > 0)) {
					commitBean.getFixedAssetsCommitBean().getUpdateList().forEach(value -> {
						value.setModifyUserId(commitBean.getCreateUserId());
					});

					returnVal = faCommit(commitBean.getFixedAssetsCommitBean());
				}
				if ((commitBean.getFixedAssetsCommitBean().getDeleteList() != null
						&& commitBean.getFixedAssetsCommitBean().getDeleteList().size() > 0)) {

					returnVal = faCommit(commitBean.getFixedAssetsCommitBean());
				}
			}
			if (commitBean.getVehiclesCommitBean() != null) {
//				if(commitBean.getVehiclesCommitBean().getInsertList() != null) {
//					commitBean.getVehiclesCommitBean().getInsertList().forEach(value -> {
//						value.setCreateUserId(commitBean.getCreateUserId());
//						value.setAssetId(commitBean.getFixedAssetsCommitBean().getUpdateList().get(0).getAssetId());
//					});
//					returnVal = vehCommit(commitBean.getVehiclesCommitBean());
//				}
				if ((commitBean.getVehiclesCommitBean().getUpdateList() != null
						&& commitBean.getVehiclesCommitBean().getUpdateList().size() > 0)) {
					commitBean.getVehiclesCommitBean().getUpdateList().forEach(value -> {
						value.setModifyUserId(commitBean.getCreateUserId());
					});

					returnVal = vehCommit(commitBean.getVehiclesCommitBean());
				}
				if ((commitBean.getVehiclesCommitBean().getDeleteList() != null
						&& commitBean.getVehiclesCommitBean().getDeleteList().size() > 0)) {

					returnVal = vehCommit(commitBean.getVehiclesCommitBean());
				}
			}

		}
		return returnVal;
	}

}