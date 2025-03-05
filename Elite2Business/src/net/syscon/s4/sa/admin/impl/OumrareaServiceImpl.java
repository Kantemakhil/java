package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.OumrareaRepository;
import net.syscon.s4.sa.admin.OumrareaService;
import net.syscon.s4.sa.admin.beans.AreasCommitBean;

/**
 * Class OumrareaServiceImpl
 */
@Service
public class OumrareaServiceImpl extends BaseBusiness implements OumrareaService {

	@Autowired
	private OumrareaRepository oumrareaRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Areas> maintRegExecuteQuery(final Areas searchRecord) {
		return oumrareaRepository.maintRegExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMAINT_REG
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<Areas> maintRegCommit(final AreasCommitBean commitBean) {
		final List<Areas> liReturnData = new ArrayList<>();
		 Areas areas = new Areas();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			 for (Areas obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrareaRepository.maintRegInsertAreas(commitBean.getInsertList());
			areas.setReturnValue(liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Areas obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			areas = oumrareaRepository.maintRegUpdateAreas(commitBean.getUpdateList());
		}
		liReturnData.add(areas);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Areas> maintAreaExecuteQuery(final Areas searchRecord) {
		return oumrareaRepository.maintAreaExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMAINT_AREA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<Areas> maintAreaCommit(final AreasCommitBean commitBean) {
		final List<Areas> liReturnData = new ArrayList<>();
		 Areas areas = new Areas();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (Areas obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrareaRepository.maintAreaInsertAreas(commitBean.getInsertList());
			areas.setReturnValue(liReturn);

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Areas obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			areas = oumrareaRepository.maintAreaUpdateAreas(commitBean.getUpdateList());
		}
		liReturnData.add(areas);
		return liReturnData;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstMAINT_SUB_AREA
	 *
	 * @throws SQLException
	 */

	@Transactional
	public List<Areas> maintSubAreaCommit(final AreasCommitBean commitBean) {
		final List<Areas> liReturnData = new ArrayList<>();
		Areas areas = new Areas();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			
			for (Areas obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oumrareaRepository.maintSubAreaInsertAreas(commitBean.getInsertList());
			areas.setReturnValue(liReturn);

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (Areas obj : commitBean.getUpdateList()) {
				  obj.setModifyUserId(commitBean.getCreateUserId());
			}
			areas = oumrareaRepository.maintSubAreaUpdateAreas(commitBean.getUpdateList());
		}
		liReturnData.add(areas);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Areas> maintSubAreaExecuteQuery(final Areas searchRecord) {
		  return oumrareaRepository.maintSubAreaExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		return oumrareaRepository.rgAreaTypeRecordGroup();

	}

}