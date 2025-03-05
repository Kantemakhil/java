package net.syscon.s4.pkgs.tag_utils;

public interface TagUtilsService {
	String getLivingUnitDescp(final Integer pLivUnitId);

	Integer okToDeleteRecord(final Integer pKeyId, final String pTableName, final String pColumnName,
			final String pExcludeTable, final String pOwner);

	String getTableName(final String pConstraintName);

	Integer okToDeleteRecord(final Integer cni);

	Integer okToModifyRecord(final String keyString, final String tableName, final String columnName,
			final String excludeTable, final String owner);

	void getNextKeyValuePair(final String keyString);

	String getSysProfile(final String profileType, final String profileCode, final String profileVale);

	String getSysProfile(String string, String string2);
	
	String getStaffId (String userId);
}