package net.syscon.s4.pkgs.tag_utils.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.AllConsColumns;
import net.syscon.s4.pkgs.tag_utils.TagUtilsRepository;
import net.syscon.s4.pkgs.tag_utils.TagUtilsService;

@Service
public class TagUtilsServiceImpl implements TagUtilsService {

	@Autowired
	private TagUtilsRepository tagUtilsRepository;
     //this method is used for retrieving DESCRIPTION from database
	@Override
	public String getLivingUnitDescp(final Integer pLivUnitId) {
		return tagUtilsRepository.getLivingUnitDescp(pLivUnitId);
	}

	@Override
	public Integer okToDeleteRecord(final Integer pKeyId, final String pTableName, final String pColumnName,
			String pExcludeTable, final String pOwner) {
		Integer resultValue = 0;
		Integer lRet;
		Boolean returnVal;
		final List<AllConsColumns> retList = tagUtilsRepository.getConstraintsDetails(pOwner, pTableName,pColumnName);
		if (pExcludeTable != null) {
			pExcludeTable = pExcludeTable.toUpperCase();
		} else {
			pExcludeTable = "XXXXXXXXXXXXXXXXXXXX";
		}
		for (final AllConsColumns allConsCol : retList) {
			if (allConsCol.getTableName() != pExcludeTable.toUpperCase()) {
				String query = "SELECT 1 FROM " + allConsCol.getTableName() + " WHERE " + allConsCol.getColumnName()
				           + " = " + pKeyId + " limit 1";
//						+ " = " + pKeyId + " AND rownum = 1";
				lRet = tagUtilsRepository.getOneForLRet(query);
				if (lRet != null) {
					lRet = lRet;
				} else {
					lRet = 0;
				}
				if (lRet == 1) {
					returnVal = false;
				}
			}
		}
		returnVal = true;
		if (returnVal) {
			resultValue = 1;
		} else {
			resultValue = 0;
		}
		return resultValue;
	}
    //This method is used to get table name from database
	@Override
	public String getTableName(final String pConstraintName) {
		return tagUtilsRepository.getTabName(pConstraintName);

	}

	@Override
	public Integer okToDeleteRecord(final Integer cni) {
		final List<Object[]> list = tagUtilsRepository.tableCur(cni);
		for (Object[] obj : list) {
			if (obj[0] != null) {
				Integer lRet = tagUtilsRepository.offenderCaseNotesSelect(cni);
				if (lRet == null) {
					lRet = 0;
					if (lRet == 1) {
						return 0;
					}
				}
			}
		}
		return 1;
	}

	@Override
	public Integer okToModifyRecord(final String keyString, final String tableName, final String columnName,
			final String excludeTable, final String owner) {
		final String lKeyString = keyString;
		String lCn1 = null, lCn2 = null, lCn3, lCn4, lCn5;
		String lKv1 = null, lKv2 = null, lKv3, lKv4, lKv5, lDynC = null;
		String lConSql = null;
		getNextKeyValuePair(keyString);
		if (lCn1 != null) {
			lDynC = lDynC;
			lDynC = lDynC.toUpperCase() + "" + lCn1 + ""
					+ " ( SELECT u1.column_name  FROM all_cons_columns u1  WHERE ac2.constraint_name = u1.constraint_name AND ac2.table_name = u1.table_name  AND ac2.table_name = u1.table_name   AND ac2.table_name = u1.table_name   AND ac2.owner = u1.owner )";
		}
		if (lCn2 != null) {
			lDynC = lDynC;
			lDynC = lDynC.toUpperCase() + "" + lCn1 + ""
					+ " ( SELECT u2.column_name FROM all_cons_columns u2 WHERE ac2.constraint_name = u2.constraint_name AND ac2.table_name = u2.table_name AND ac2.owner = u2.owner )";
		}
		if (tableName != excludeTable) {
			lConSql = "SELECT 1 FROM " + tableName + "WHERE";

		}
		if (lCn1 != null) {
			lCn1 = lCn1 + lCn1 + "=" + lKv1;
		}
		if (lCn2 != null) {
			lConSql = lConSql + "AND" + lCn2 + "=" + lKv2;
		}

		lConSql = lConSql + "AND rownum = 1";
		Integer lRet = Integer.parseInt(lConSql);
		if (lRet == null) {
			lRet = 0;
			if (lRet == 1) {
				return 0;
			}
		}
		return 1;
	}

	@Override
	public void getNextKeyValuePair(String keyString) {
		String lKeyString = null;
		lKeyString = keyString;
		if (keyString.length() == 0) {
			keyString = null;
			return;
		}
		final String pKv = tagUtilsRepository.subStr(keyString);
		final String pCn = tagUtilsRepository.subStr1(keyString);
		keyString = tagUtilsRepository.subStr2(keyString);

	}

	@Override
	public String getSysProfile(final String profileType, final String profileCode, final String profileVale) {
		final List<String> list = tagUtilsRepository.sysProfileCur(profileType, profileCode);
		String vValue = null;
		for (final String s : list) {
			vValue = s;
		}
		return vValue;
	}

	@Override
	public String getSysProfile(String profileType, String profileCode) {
		return tagUtilsRepository.sysProfileCurForProfileValue(profileType, profileCode);
	}

	@Override
	public String getStaffId(String userId) {
		String staffId = null;
		staffId = tagUtilsRepository.getStaffCur(userId);
		return null;
	}

}