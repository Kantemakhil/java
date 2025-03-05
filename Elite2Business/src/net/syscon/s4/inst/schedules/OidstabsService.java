package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VAddressUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VAgencyAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VPhones;
import net.syscon.s4.inst.schedules.bean.VPhonesCommitBean;

/**
 * Interface OidstabsService
 */
public interface OidstabsService {
	List<VAddressUsages> othAdrExecuteQuery(VAddressUsages objVAddressUsages);

	List<VAgencyAddresses> rgAgyLocRecordGroup();

	List<VCorporateAddresses> busAdrExecuteQuery(VCorporateAddresses objVCorporateAddresses);

	Integer othAdrCommit(VAddressUsagesCommitBean commitBean);

	List<VAddressUsages> rgOthLocRecordGroup(String rootOffenderId);

	List<ReferenceCodes> rgEscortRecordGroup();

	List<VPhones> agyPhonesExecuteQuery(VPhones objVPhones);

	Integer othPhonesCommit(VPhonesCommitBean commitBean);

	List<VAgencyAddresses> agyAdrExecuteQuery(VAgencyAddresses objVAgencyAddresses);

	Integer agyAdrCommit(VAgencyAddressesCommitBean commitBean);

	VOffenderAllSchedules offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);

	Integer busAdrCommit(VCorporateAddressesCommitBean commitBean);

	List<VOffenderAllSchedules> offSchedulesExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	Integer busPhonesCommit(VPhonesCommitBean commitBean);

	List<VAgencyAddresses> offSchedulesOnCheckDeleteMaster(VAgencyAddresses paramBean);

	Integer offSchedulesCommit(OffenderIndSchedulesCommitBean commitBean);

	List<ReferenceCodes> rgStatusRecordGroup();

	List<MovementReasons> rgSubTypeRecordGroup(String type);

	List<VCorporateAddresses> rgCorpLocRecordGroup();

	List<ReferenceCodes> rgTransportRecordGroup();

	Integer agyPhonesCommit(VPhonesCommitBean commitBean);

	List<VPhones> busPhonesExecuteQuery(VPhones searchRecord);

	List<VPhones> othPhonesExecuteQuery(VPhones searchBean);

	Integer calculateDays(String eventDate, String returnDate);

	Object calculateHours(String startTime, String returnTime);

	Integer adressLocationsCommit(OffenderIndSchedulesCommitBean commitBean);

	Integer offSchCheckScheduleConflict(OffenderIndSchedules obj);

	Integer offenderSchedulesCommit(OffenderIndSchedulesCommitBean commitBean);

	String checkNonAssociations(OffenderIndSchedulesCommitBean commitBean);

	String getNonAssociationsData(List<OffenderIndSchedules> offenderIndSchedules, String createUserId);

	List<ReferenceCodes> rgPurposeRecordGroup(String reason);

}
