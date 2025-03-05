package net.syscon.s4.inst.offenderobservations.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.offenderobservations.OiioffobRepository;
import net.syscon.s4.inst.offenderobservations.OiioffobService;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationInquiry;

@Service
public class OiioffobServiceImpl extends BaseBusiness implements OiioffobService{
	@Autowired
	private OiioffobRepository oiioffobRepository;
	@Override
	public List<OffenderObservationInquiry> getOffenderPeriodInquiryQuery(OffenderObservationInquiry searchBean) {
		if("true".equals(searchBean.getOverDueFlag())){
			searchBean.setOverDueFlag("Y");
		} else {
			searchBean.setOverDueFlag("N");
		}
		return oiioffobRepository.getOffenderPeriodInquiryQuery(searchBean);
	}

}
