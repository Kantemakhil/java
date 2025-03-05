package net.syscon.s4.inst.offenderspecific.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.inst.offenderspecific.OiibooksRepository;
import net.syscon.s4.inst.offenderspecific.OiibooksService;
import net.syscon.s4.pkgs.oms_form_access.OmsFormAccessService;

/**
 * Class OiibooksServiceImpl
 */
@Service
public class OiibooksServiceImpl extends BaseBusiness implements OiibooksService {

	@Autowired
	private OiibooksRepository oiibooksRepository;
	@Autowired
	private OmsFormAccessService omsFormAccessService;
	
	@Autowired
	private OsiosearService osiosearServiceImpl;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * 
	 */

	@Override
	public List<OffenderBookings> offBooksExecuteQuery(final OffenderBookings objOffenderBookings) {
		return oiibooksRepository.offBooksExecuteQuery(objOffenderBookings);
	}

	@Override
	public List<FormAccessibleForms> bookDetailExecuteQuery(final FormAccessibleForms objFormAccessibleForms) {
		List<FormAccessibleForms> returnList = new ArrayList<FormAccessibleForms>();
		String dataAvail = null;
		returnList = oiibooksRepository.bookDetailExecuteQuery(objFormAccessibleForms);
		for (final FormAccessibleForms formAccessibleForms : returnList) {
			formAccessibleForms
					.setDescription(oiibooksRepository.bookDetailPostQuery(formAccessibleForms.getDestinationForm()));
			formAccessibleForms.setOffenderId(objFormAccessibleForms.getOffenderId());
			formAccessibleForms.setBookId(objFormAccessibleForms.getBookId());

			dataAvail  = omsFormAccessService.checkDataAvailable(formAccessibleForms);
			if (dataAvail != null) {
				if (dataAvail.equals("TRUE")) {
					dataAvail = "Y";
				} else {
					dataAvail = "N";
				}
			}
			formAccessibleForms.setChkData(dataAvail);
		}
		return returnList;
	}

	@Override
	public String checkFormAccess(final FormAccessibleForms searchBean) {
		final Integer modulePrivilege = oiibooksRepository.checkFormAccess(searchBean);
		if (modulePrivilege.equals(1) && !searchBean.getOriginatingForm().equals(searchBean.getDestinationForm())) {
			return searchBean.getDestinationForm();
		} else {
			return null;
		}
	}

	@Override
	public VHeaderBlock getOffenderObject(final VHeaderBlock paramBean) {
		VHeaderBlock returnObj=new VHeaderBlock();
		returnObj = oiibooksRepository.getOffenderObject(paramBean);
		
		returnObj = removeSealOffNameSearchActiveBookingSummery(returnObj, paramBean.getCreateUserId());
		return returnObj;
	}

	private VHeaderBlock removeSealOffNameSearchActiveBookingSummery(VHeaderBlock updatedList, String userId) {
		VHeaderBlock resultList = new VHeaderBlock();
		Integer returnValue=osiosearServiceImpl.getSystemProfileUserAccValue(userId);	
		if (returnValue == 0) {
			if (!"Y".equals(updatedList.getSealFlag())) {
				resultList = updatedList;
			} else {
				return resultList;
			}

		} else {
			return updatedList;
		}
		return resultList;
	}

}