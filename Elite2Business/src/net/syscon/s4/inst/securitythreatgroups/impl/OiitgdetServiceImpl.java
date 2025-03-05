package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.StgValidationsCommitBean;
import net.syscon.s4.common.beans.VStgLocationPresence;
import net.syscon.s4.common.beans.VStgLocationPresenceCommitBean;
import net.syscon.s4.common.beans.VStgRacialMakeup;
import net.syscon.s4.common.beans.VStgRacialMakeupCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OiitgdetRepository;
import net.syscon.s4.inst.securitythreatgroups.OiitgdetService;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessService;

@Service
public class OiitgdetServiceImpl extends BaseBusiness implements OiitgdetService {

	private final String STRTRUE = "TRUE";
	private final String STRFALSE = "FALSE";

	@Autowired
	private OiitgdetRepository oiitgdetRepository;

	@Autowired
	private OmsFormAccessService omsFormAccessService;

	/**
	 * Creates new OiitgdetServiceImpl class Object
	 */
	public OiitgdetServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Map<String, BigDecimal> stgDetailKeyExeqry(final BigDecimal param) {
		if (param != null) {
			BigDecimal totalMember = oiitgdetRepository.stgDetailKeyExeqry(param);
			if (totalMember == null) {
				totalMember = BigDecimal.ZERO;
			}
			BigDecimal validated = oiitgdetRepository.stgDetailKeyExeqryValidated(param);
			if (validated == null) {
				validated = BigDecimal.ZERO;
			}
			final Map<String, BigDecimal> result = new HashMap<>();
			result.put("totalMember", totalMember);
			result.put("validated", validated);
			result.put("nonValidated", totalMember.subtract(validated));
			return result;

		}
		return null;

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<ReferenceCodes> stgValidationsPostQuery(final ReferenceCodes paramBean) {
		return oiitgdetRepository.stgValidationsPostQuery(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public List<IwpTemplates> fafPostQuery(final OmsModules paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Map<String, String> oiitgdetWhenNewFormInstance() {
		final String value = oiitgdetRepository.oiitgdetWhenNewFormInstance();
		final Map<String, String> result = new HashMap<>();
		if (value == null) {
			result.put("value", null);
		} else {
			final String group = oiitgdetRepository.oiitgdetOiitgdetWhennewforminstanceStgDescriptionCur2(value);
			if (group == null) {
				result.put("value", null);
			} else {
				result.put("value", group);
			}
		}
		return result;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StgValidations> stgValidationsExecuteQuery(final StgValidations searchRecord) {
		return oiitgdetRepository.stgValidationsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_VALIDATIONS
	 *
	 * 
	 */
	@Transactional
	public Integer stgValidationsCommit(final StgValidationsCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VStgRacialMakeup> stgRaceMakeupExecuteQuery(final VStgRacialMakeup searchRecord) {
		return oiitgdetRepository.stgRaceMakeupExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_RACE_MAKEUP
	 *
	 * 
	 */
	@Transactional
	public Integer stgRaceMakeupCommit(final VStgRacialMakeupCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VStgLocationPresence> stgLocationPresenceExecuteQuery(final VStgLocationPresence searchRecord) {
		return oiitgdetRepository.stgLocationPresenceExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_LOCATION_PRESENCE
	 *
	 * 
	 */
	@Transactional
	public Integer stgLocationPresenceCommit(final VStgLocationPresenceCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<FormAccessibleForms> fafExecuteQuery(final FormAccessibleForms searchRecord) {
		final List<FormAccessibleForms> returnList = oiitgdetRepository.fafExecuteQuery(searchRecord);
		if (returnList != null) {
			returnList.forEach(data -> {
				BigDecimal lvCount = BigDecimal.ZERO;
				BigDecimal lvCountTwo = BigDecimal.ZERO;
				BigDecimal lvIwp = BigDecimal.ZERO;
				String lvData = "";
				if (data.getDestinationForm() != null) {
					final String description = oiitgdetRepository.fafPostQueryDescription(data.getDestinationForm());
					if (description != null) {
						data.setDescription(description);
					}
					if (searchRecord.getStgId() != null) {
						if ("OIDSTGIN".equals(data.getDestinationForm())) {
							lvCount = oiitgdetRepository.fafPostQueryVAlidDataCur(searchRecord.getStgId());
							lvCountTwo = oiitgdetRepository.fafPostQueryVAlidDataCurTwo(searchRecord.getStgId());
							if (lvCount.compareTo(BigDecimal.ZERO) > 0 || lvCountTwo.compareTo(BigDecimal.ZERO) > 0) {
								lvData = STRTRUE;
							} else {
								lvData = STRFALSE;
							}
						} else if ("OIUIWPVE".equals(data.getDestinationForm())) {
							lvIwp = oiitgdetRepository.fafPostQueryTwoValidIwpCur(searchRecord.getStgId());
							if (lvIwp.compareTo(BigDecimal.ZERO) > 0) {
								lvData = STRTRUE;
							} else {
								lvData = STRFALSE;
							}
						} else {
							lvData = omsFormAccessService.checkDataAvailableStg(data.getOriginatingForm(),
									data.getDestinationForm(), searchRecord.getStgId().longValue()).toString();
						}

						if (STRTRUE.equalsIgnoreCase(lvData)) {
							data.setCheckFlag("Y");
						} else {
							data.setCheckFlag("N");
						}
					}
				}
				data.setStgId(searchRecord.getStgId());
			});
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstFAF
	 *
	 * 
	 */
	@Transactional
	public Integer fafCommit(final FormAccessibleFormsCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		final List<SecurityThreatGroups> result = oiitgdetRepository.rgStg2RecordGroup();
		result.stream().forEach(data -> {
			if (data.getStgId() != null) {
				data.setCode(data.getStgId().toString());
			}
		});
		return result;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		final List<SecurityThreatGroups> result = oiitgdetRepository.rgStg1RecordGroup();
		result.stream().forEach(data -> {
			if (data.getStgId() != null) {
				data.setCode(data.getStgId().toString());
			}
		});
		return result;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		final List<SecurityThreatGroups> result = oiitgdetRepository.rgStg3RecordGroup();
		result.stream().forEach(data -> {
			if (data.getStgId() != null) {
				data.setCode(data.getStgId().toString());
			}
		});
		return result;

	}

	@Override
	public String oiitgdetPrimaryCur(final BigDecimal stgId) {
		return oiitgdetRepository.oiitgdetPrimaryCur(stgId);
	}

	@Override
	public String oiitgdetGetProfileValue(final String profileType, final String profileCode) {
		return oiitgdetRepository.oiitgdetGetProfileValue(profileType, profileCode);
	}

	@Override
	public String oiitgdetGetGroupPrivilege(String username) {
		return oiitgdetRepository.oiitgdetGetGroupPrivilege(username);
	}

}