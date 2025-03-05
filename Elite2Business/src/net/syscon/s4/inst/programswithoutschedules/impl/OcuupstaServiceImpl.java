package net.syscon.s4.inst.programswithoutschedules.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.OcufovdtRepository;
import net.syscon.s4.cm.programsservices.OcmxpstmService;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPrgObligationHty;
import net.syscon.s4.inst.programswithoutschedules.OcuupstaRepository;
import net.syscon.s4.inst.programswithoutschedules.OcuupstaService;
import net.syscon.s4.pkgs.tag_programmes.TagProgrammesService;

/**
 * Class OcuupstaServiceImpl
 */
@Service
public class OcuupstaServiceImpl extends BaseBusiness implements OcuupstaService {

	@Autowired
	private OcuupstaRepository ocuupstaRepository;

	@Autowired
	private OcmxpstmService ocmxpstmService;
	
	@Autowired
	private TagProgrammesService  tagProgrammesService;
	
	private final String Y="Y";
	
	private final Logger logger = LogManager.getLogger(OcuupstaServiceImpl.class);

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPrgObligationHty> offPrgOblHtyExecuteQuery(final OffenderPrgObligationHty searchRecord) {
		final List<OffenderPrgObligationHty> returnList = ocuupstaRepository.offPrgOblHtyExecuteQuery(searchRecord);
		logger.info(this.getClass().getName()+" offPrgOblHtyExecuteQuery");
		for (final OffenderPrgObligationHty obj : returnList) {
			if (obj!=null && obj.getStatusChangeReason() != null) {
				final String description = ocuupstaRepository.getDescription(obj.getStatusChangeReason());
				logger.info(this.getClass().getName()+" getDescription");
				if (obj.getStatusChangeReason() != null) {
					obj.setStatusChangeReasonDesc(description);
				}
			}
			if (obj!=null && obj.getStatus() != null) {
				final String description2 = ocuupstaRepository.getStatusDescription(obj.getStatus());
				logger.info(this.getClass().getName()+" getStatusDescription");
				obj.setStatusDesc(description2);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgPsPrgStatRecordGroup(final String statusDesc, final String lovDomain) {

		final List<ReferenceCodes> returnList = ocuupstaRepository.rgPsPrgStatRecordGroup(statusDesc, lovDomain);
		logger.info(this.getClass().getName()+" rgPsPrgStatRecordGroup");		
		returnList.forEach(result -> {
			result.setCode(String.valueOf(result.getCode()));
			result.setDescription(result.getDescription().toString());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgPsPrgObstsRecordGroup(final String parentCode) {
		return ocuupstaRepository.rgPsPrgObstsRecordGroup(parentCode);

	}

	@Override
	public Integer updateStatus(final OffenderPrgObligationHty searchBean) {
		return tagProgrammesService.updateStatus(searchBean);
	}

	@Override
	public Date getRefferalDate(final Integer offenderPrgObligationId) {

		return ocuupstaRepository.getRefferalDate(offenderPrgObligationId);
	}

	@Override
	public Date getMaxDate(final Integer offenderPrgObligationId) {
		return ocuupstaRepository.getMaxDate(offenderPrgObligationId);
	}
	
	@Override
	public Boolean updateStatusBtn(final String code) {
		String result = ocuupstaRepository.updateStatusBtn(code);
		logger.info(this.getClass().getName()+" updateStatusBtn");
		if (result != null && Y.equalsIgnoreCase(result)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean getAdministratorUserAccsess(String userName) {
		SystemProfiles bean = ocuupstaRepository.sysPflExecuteQuery();
		logger.info(this.getClass().getName() + " getAdministratorUserAccsess");
		if (bean != null && bean.getProfileValue() != null && bean.getProfileValue2() != null && "Y".equalsIgnoreCase(bean.getProfileValue())) {
			List<String> roleIdList = ocuupstaRepository.getRoleIdList(userName);
			logger.info(this.getClass().getName() + " getRoleIdList");
			if (roleIdList != null) {
				for (String data : roleIdList) {
					if (data.equalsIgnoreCase(bean.getProfileValue2())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	@Override
	public Boolean reasonForSuspendingOrEndingProgramDisable(final String code) {
		String flag = ocuupstaRepository.reasonForSuspendingOrEndingProgramDisable(code);
		if (Y.equalsIgnoreCase(flag)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchBean) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = ocmxpstmService.refCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " refCodeExecuteQuery", e);
		}
		return returnList;
	}
	
}