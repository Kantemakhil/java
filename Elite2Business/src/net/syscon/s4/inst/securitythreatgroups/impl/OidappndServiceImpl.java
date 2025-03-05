package net.syscon.s4.inst.securitythreatgroups.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.inst.securitythreatgroups.OidappndRepository;
import net.syscon.s4.inst.securitythreatgroups.OidappndService;
import net.syscon.s4.triggers.OffenderPtr;
import net.syscon.s4.triggers.OffenderPtrTwfService;

@Service
public class OidappndServiceImpl extends BaseBusiness implements OidappndService {

	@Autowired
	private OidappndRepository oidappndRepository;

	@Autowired
	private EliteDateService eliteDateService;

	@Autowired
	private OffenderPtrTwfService offenderPtrTwfService;
	


	/**
	 * Creates new OidappndServiceImpl class Object
	 */

	public OidappndServiceImpl() {
		// OidappndServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {

		return oidappndRepository.createFormGlobalscreateFormGlobals(paramBean);

	}

	Integer checkText(final int pTextLen, final String form, final String commentText) {
		Integer lvLenTxt;
		Integer lvLenTxt1;
		Integer lvReduceLen = 0;
		Integer lvLength = null;
		if ("OIDMBRDT".equals(form)) {
			lvLength = 2000;
		} else if ("OIDSTGCN".equals(form) || "OIDMBRQU".equals(form) || "OCDCPTIT".equals(form)) {
			lvLength = 4000;
		}
		if (commentText != null) {
			lvLenTxt = commentText.length();
		} else {
			lvLenTxt = 0;
		}
		lvLenTxt1 = pTextLen;
		if (lvLenTxt1 != 0) {
			lvLenTxt = lvLenTxt + lvLenTxt1;
		}
		if (lvLength != null && lvLenTxt != null) {
			if (lvLenTxt > lvLength) {
				lvReduceLen = lvLenTxt - lvLength;

			}
		}
		return lvReduceLen;
	}

	@Transactional
	public Integer stgCommit(final StgCaseNotes commitBean) {
		int liReturn = 0;
		if (commitBean.getNewText() != null && !commitBean.getNewText().isEmpty()) {
			final String newText = " [ "
					+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(eliteDateService.getDBTime()) + " "
					+ commitBean.getCreateUserId() + " ] " + commitBean.getNewText();
			if (newText != null) {
				final Integer checkText = checkText(newText.length(), commitBean.getModuleName(), commitBean.getText());
				if (checkText.equals(0)) {
					commitBean.setNewText(newText);
					if ("OIDMBRDT".equals(commitBean.getModuleName())) {
						liReturn = oidappndRepository.oidappndOidmbrdtUpdateOffenderStgAffiliations(commitBean);
					} else if ("OIDSTGCN".equals(commitBean.getModuleName())) {
						liReturn = oidappndRepository.oidappndOidstgcnUpdateStgCaseNotes(commitBean);
					} else if ("OIDMBRQU".equals(commitBean.getModuleName())) {
						liReturn = oidappndRepository.oidappndOidmbrquUpdateOffenderAssessmentItems(commitBean);

					} else if ("OCDCPTIT".equals(commitBean.getModuleName())) {
						OffenderPtr ptr = new OffenderPtr();
						BeanUtils.copyProperties(commitBean, ptr);
						ptr.setPtrId(commitBean.getPtrId().longValue());
						ptr.setOffenderBookId(commitBean.getOffenderBookId().longValue());
						ptr.setCreateUserId(commitBean.getCreateUserId());
						offenderPtrTwfService.offenderPtrTwfTgr(ptr, "INSERTING");
						liReturn = oidappndRepository.oidappndOcdcptitUpdateOffenderPtr(commitBean);
					}

				} else {
					liReturn = checkText;
				}
			}
		}

		return liReturn;
	}
}