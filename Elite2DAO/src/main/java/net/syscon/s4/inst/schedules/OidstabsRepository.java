package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VPhones;

/**
 * Interface OidstabsRepository
 */
public interface OidstabsRepository {
	List<VAddressUsages> othAdrExecuteQuery(VAddressUsages objVAddressUsages);

	List<VOffenderAllSchedules> offBkgOnCheckDeleteMaster(VOffenderAllSchedules paramBean);

	List<VAddressUsages> rgOthLocRecordGroup(String rootOffenderId);

	List<VPhones> agyPhonesExecuteQuery(VPhones objVPhones);

	Integer busAdrDeleteVCorporateAddresses(List<VCorporateAddresses> lstVCorporateAddresses);

	List<VCorporateAddresses> offSchedulesOnCheckDeleteMaster(VCorporateAddresses paramBean);

	List<VAgencyAddresses> agyAdrExecuteQuery(VAgencyAddresses objVAgencyAddresses);

	Integer offSchedulesDeleteVOffenderAllSchedules(List<OffenderIndSchedules> lstVOffenderAllSchedules);

	Integer agyAdrInsertVAgencyAddresses(List<VAgencyAddresses> lstVAgencyAddresses);

	Integer agyAdrUpdateVAgencyAddresses(List<VAgencyAddresses> lstVAgencyAddresses);

	Integer busAdrUpdateVCorporateAddresses(List<VCorporateAddresses> lstVCorporateAddresses);

	List<VAddressUsages> offSchedulesOnCheckDeleteMaster(VAddressUsages paramBean);

	List<VAgencyAddresses> offSchedulesOnCheckDeleteMaster(VAgencyAddresses paramBean);

	Integer othAdrDeleteVAddressUsages(List<VAddressUsages> lstVAddressUsages);

	List<ReferenceCodes> rgStatusRecordGroup();

	Integer othAdrInsertVAddressUsages(List<VAddressUsages> lstVAddressUsages);

	List<VCorporateAddresses> rgCorpLocRecordGroup();

	List<ReferenceCodes> rgTransportRecordGroup();

	List<VAgencyAddresses> rgAgyLocRecordGroup();

	List<VCorporateAddresses> busAdrExecuteQuery(VCorporateAddresses objVCorporateAddresses);

	Integer getMaxEventid();

	List<ReferenceCodes> rgEscortRecordGroup();

	Integer offSchedulesUpdateVOffenderAllSchedules(List<OffenderIndSchedules> list);

	List<VAddressUsages> populateOthAddress(VAddressUsages paramBean);

	Integer busAdrInsertVCorporateAddresses(List<VCorporateAddresses> lstVCorporateAddresses);

	List<VPhones> offSchedulesOnCheckDeleteMaster(VPhones paramBean);

	Integer offSchedulesInsertVOffenderAllSchedules(List<OffenderIndSchedules> list);

	List<VOffenderAllSchedules> offSchedulesExecuteQuery(VOffenderAllSchedules objVOffenderAllSchedules);

	Integer othAdrUpdateVAddressUsages(List<VAddressUsages> lstVAddressUsages);

	List<MovementReasons> rgSubTypeRecordGroup(String type);

	List<VPhones> busPhonesExecuteQuery(VPhones searchRecord);

	List<VPhones> othPhonesExecuteQuery(VPhones searchRecord);

	Integer calculateDays(String eventDate, String returnDate);

	Object calculateHours(String startTime, String returnTime);

	Integer adressLocationsUpdateQuery(List<OffenderIndSchedules> updateList);

	Integer offSchCheckScheduleConflict(OffenderIndSchedules obj);

	Integer createSchedule(OffenderIndSchedules obj);

	List<OffenderIndSchedules> checkOffenderScheduleConflicts(BigDecimal offenderBookId, OffenderIndSchedules vOffSch);

	List<ReferenceCodes> rgPurposeRecordGroup(String reason);

}
