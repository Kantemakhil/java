package net.syscon.s4.globaloffenderrecords.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderAlertsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OcualertRepository;
import net.syscon.s4.globaloffenderrecords.OcualertService;
import net.syscon.s4.im.beans.OffenderAlerts;

/**
 * Class OcualertServiceImpl
 * 
 */
@Service
public class OcualertServiceImpl extends BaseBusiness implements OcualertService {

	@Autowired
	private OcualertRepository ocualertRepository;

	/**
	 * Creates new OcualertServiceImpl class Object
	 */
	public OcualertServiceImpl() {
		// OcualertServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderAlerts> alertExecuteQuery(final OffenderAlerts searchRecord) {
		final List<OffenderAlerts> retrunObj = ocualertRepository.alertExecuteQuery(searchRecord);
		ReferenceCodes refCodeBean = new ReferenceCodes();
		for (final OffenderAlerts alertObj : retrunObj) {
			if (alertObj.getCaseloadType() != null) {
				final String str = String.valueOf(alertObj.getCaseloadType().charAt(0));
				alertObj.setCaseloadType(str);
			}
			refCodeBean.setDomain("ALERT");
			refCodeBean.setCode(alertObj.getAlertType().trim());
			final ReferenceCodes refTypeList = ocualertRepository.postQuery(refCodeBean);
			alertObj.setAlertTypeDes(refTypeList.getDescription());

			refCodeBean.setDomain("ALERT_CODE");
			refCodeBean.setCode(alertObj.getAlertCode().trim());
			final ReferenceCodes refCodeList = ocualertRepository.postQuery(refCodeBean);
			alertObj.setAlertCodeDes(refCodeList.getDescription());

		}
		return retrunObj;
	}
	
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<ReferenceCodes> rgAlertDescription(final String domain) {
		return  (List<ReferenceCodes>) ocualertRepository.rgAlertDescription(domain);
	
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstALERT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer alertCommit(final OffenderAlertsCommitBean commitBean) {
		return 0;
	}

}