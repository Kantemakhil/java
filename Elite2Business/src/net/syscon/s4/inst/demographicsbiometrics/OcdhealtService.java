package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.HealthRecordDetails;
import net.syscon.s4.im.beans.HealthRecordDetailsCommitBean;
import net.syscon.s4.im.beans.OffHealthRecordsData;
import net.syscon.s4.im.beans.OffHealthRecordsDataCommitBean;

public interface OcdhealtService {

	Integer getUserRoleForHealUser(String userName);

	Integer getUserRoleForHealAdvUser(String userName);

	List<OffHealthRecordsData> offenderRowHealthDataCommit(OffHealthRecordsDataCommitBean commitBean);

	List<OffHealthRecordsData> getOffenderRowHealthExecuteQuery(OffHealthRecordsData searchBean);

	List<HealthRecordDetails> getHealthDetailExecuteQuery(HealthRecordDetails searchBean);

	List<HealthRecordDetails> healthRecordDetailDataCommit(HealthRecordDetailsCommitBean commitBean);

}
