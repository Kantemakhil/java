package net.syscon.s4.pkgs.oidshlog.impl;

import java.io.Reader;
import java.sql.Clob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.pkgs.oidshlog.OidshlogPkgRepository;
import net.syscon.s4.pkgs.oidshlog.OidshlogPkgService;

/*
 * Below comments are copied from package OIDSHLOG
 *  -- ======================================================================
  v_version CONSTANT VARCHAR2(60) := '11.2.1.0 10-Dec-2013';
  -- ======================================================================
-- ====================================================================================================
/*
|| MODIFICATION HISTORY
-- =================================================================================================
|| Person      Date         Version   Comments
--=================================================================================================
|| Ruxandra    10-DEC-2013  11.2.1.0   Initial version
-- =================================================================================================
*/
@Service
public class OidshlogPkgServiceImpl extends BaseBusiness implements OidshlogPkgService {
	@Autowired
	private OidshlogPkgRepository oidshlogRepository;

	private static Logger logger = LogManager.getLogger(OidshlogPkgServiceImpl.class.getName());

	@Override
	public Integer saveObservationText(final AgencyShiftLogs agyShtLog, final String userName) {
		AgencyShiftLogs agyShiftLogs = null;
		Integer retVal = 0;
		try {
			final AgencyShiftLogs bean = oidshlogRepository.checkClobState(agyShtLog.getShiftLogSeq().longValue());
			if (bean.getAgyActivityCode().equals("CLOB_IS_NULL")) {
				if (bean.getAgyActivityCode().length() > 0) {
					oidshlogRepository
							.updateAencyShiftLogs(agyShtLog.getShiftLogSeq().longValue(), userName);
				}
			} else {
				agyShiftLogs = oidshlogRepository.selectAgyShiftLogs(agyShtLog.getShiftLogSeq().longValue());
			}
			if (agyShiftLogs.getAgyActivityCode() != null) {
				if (agyShiftLogs.getAgyActivityCode() == null) {
				}
			}
			retVal = 1;
		} catch (DataAccessException e) {
			logger.error("updateAencyShiftLogs" + e);
			retVal = 1;
		}
		return retVal;
	}

	@Override
	public String getObservationText(final AgencyShiftLogs agyShtLog) {
		String pObservationText = null;
		Clob lvClob = null;
		Reader red = null;
		lvClob = oidshlogRepository.getObservText(agyShtLog.getShiftLogSeq());
		try {
			red = lvClob.getCharacterStream();
			final StringBuffer buffer = new StringBuffer();
			int ch;
			while ((ch = red.read()) != -1) {
				buffer.append("" + (char) ch);
			}
			final String clobData = buffer.toString();
			final Integer len = clobData.length();
			if (lvClob != null && len > 0) {
				pObservationText = subStr(clobData, 0, 32000);
			}
		} catch (Exception e) {
			logger.error("getObservationText :", e);
			pObservationText = null;
		}
		return pObservationText;
	}

}
