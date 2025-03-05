package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.HealthRecordDetails;
import net.syscon.s4.im.beans.OffHealthRecordsData;

public interface OcdhealtRepository {

	Integer getRoleId(String string);

	Integer offenderRowHealthDataInsertData(List<OffHealthRecordsData> insertList);

	Integer offenderRowHealthDataUpdateData(List<OffHealthRecordsData> updateList);

	Integer offenderRowHealthDataDeleteData(List<OffHealthRecordsData> deleteList);

	List<OffHealthRecordsData> getOffenderRowHealthExecuteQuery(OffHealthRecordsData searchBean);

	List<HealthRecordDetails> getHealthDetailExecuteQuery(HealthRecordDetails searchBean);

	Integer healthRecordDetailsDeleteData(List<HealthRecordDetails> deleteList);

	Integer healthRecordDetailsUpdateData(List<HealthRecordDetails> updateList);

	Integer healthRecordDetailsInsertData(List<HealthRecordDetails> insertList);

}
