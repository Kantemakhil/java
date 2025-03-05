package net.syscon.s4.pkgs.tag_utils;

import java.util.List;

import net.syscon.s4.pkgs.AllConsColumns;

public interface TagUtilsRepository {
	public String getLivingUnitDescp(final Integer pLivUnitId);

	public List<AllConsColumns> getConstraintsDetails(final String pOwner, final String pTableName,final String columName);

	public Integer getOneForLRet(final String query);

	public String getTabName(final String pConstraintName);

	List<Object[]> tableCur(final Integer voff);

	Integer offenderCaseNotesSelect(final Integer voff);

	String subStr(final String key);

	String subStr1(final String key);

	String subStr2(final String key);

	List<String> sysProfileCur(final String profileType, final String profileCode);

	String sysProfileCurForProfileValue(String profileType, String profileCode);

	public String getStaffCur(String userId);

}