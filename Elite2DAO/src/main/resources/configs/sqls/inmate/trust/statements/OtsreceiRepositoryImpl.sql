
OTSRECEI_FIND_CGFKOMSREQPRINTERID {
 	SELECT PRTR.PRINTER_ID  PRINTER_ID  /* CG$FK */        ,PRTR.DESCRIPTION  DSP_DESCRIPTION FROM   PRINTERS PRTR ORDER BY  PRTR.DESCRIPTION
}

OTSRECEI_FIND_CGFKOMSREQMODULENAME {
 	SELECT OMS_MOD.MODULE_NAME CODE /* CG$FK */  ,OMS_MOD.DESCRIPTION DESCRIPTION FROM OMS_MODULES OMS_MOD WHERE OMS_MOD.MODULE_TYPE = 'REPORT' AND OMS_MOD.MODULE_NAME='OTRRECEI' OR OMS_MOD.MODULE_NAME='OTRDRECE'
}

OTSRECEI_FIND_CGFKCSLDDPAGYLOC {
 	SELECT CAL.AGY_LOC_ID CODE /* CG$FK */ , AL.DESCRIPTION DESCRIPTION   FROM CASELOAD_AGENCY_LOCATIONS CAL , AGENCY_LOCATIONS AL  WHERE CAL.CASELOAD_ID = :CASELOAD_ID    AND AL.AGY_LOC_ID = CAL.AGY_LOC_ID
}

OTSRECEI_FIND_CGFKRECPTSCREATEDUSERS {
 	SELECT USER_ID DESCRIPTION, LAST_NAME , FIRST_NAME , STAFF_ID CODE  FROM STAFF_MEMBERS WHERE USER_ID IS NOT NULL --@@KUMAR 10/10/14 #24334 ADDED USERID FILTER
}

OTSRECEI_OMSREQ_FIND_OMS_REQUESTS {
 	SELECT REQUEST_ID ,MODULE_NAME ,NUMBER_OF_COPY ,REQUEST_STATUS ,PRINTER_ID ,DISPLAY_FLAG ,REQUEST_DATE ,REQUEST_USER_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG   FROM OMS_REQUESTS  /* where  */
}
OTSRECEI_OMSREQ_INSERT_OMS_REQUESTS {
	INSERT INTO OMS_REQUESTS(REQUEST_ID ,MODULE_NAME ,NUMBER_OF_COPY ,REQUEST_STATUS ,PRINTER_ID ,DISPLAY_FLAG ,REQUEST_DATE ,REQUEST_USER_ID ,CREATE_DATETIME ,CREATE_USER_ID ,MODIFY_DATETIME ,MODIFY_USER_ID ,SEAL_FLAG ) VALUES(:requestId ,:moduleName ,:numberOfCopy ,:requestStatus ,:printerId ,:displayFlag ,:requestDate ,:requestUserId ,CURRENT_TIMESTAMP ,:createUserId ,CURRENT_TIMESTAMP ,:modifyUserId ,:sealFlag )
}

OTSRECEI_OMSREQ_UPDATE_OMS_REQUESTS {
	UPDATE OMS_REQUESTS set REQUEST_ID  = :requestId ,MODULE_NAME  = :moduleName ,NUMBER_OF_COPY  = :numberOfCopy ,REQUEST_STATUS  = :requestStatus ,PRINTER_ID  = :printerId ,DISPLAY_FLAG  = :displayFlag ,REQUEST_DATE  = :requestDate ,REQUEST_USER_ID  = :requestUserId ,CREATE_DATETIME  = CURRENT_TIMESTAMP ,CREATE_USER_ID  = :createUserId ,MODIFY_DATETIME  = CURRENT_TIMESTAMP ,MODIFY_USER_ID  = :modifyUserId ,SEAL_FLAG  = :sealFlag /* where */
}

OTSRECEI_OMSREQ_DELETE_OMS_REQUESTS { 
	DELETE FROM OMS_REQUESTS/* where */
}


OTSRECEI_OMS_REQ_PREINSERT_ {
	SELECT  NEXTVAL('REQUEST_ID') FROM   DUAL
}

OTSRECEI_OTSRECEI_KEYCOMMIT {
	SELECT DESCRIPTION, DEFAULT_COPY  FROM OMS_MODULES WHERE MODULE_NAME = 'OTRRECEI' AND MODULE_TYPE = 'REPORT'
}

OTSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_PRINT_ {
	SELECT PRTR.DESCRIPTION FROM   PRINTERS PRTR WHERE  PRTR.PRINTER_ID = :PRINTERID
}

OTSRECEI_CGWHEN_NEW_FORM_INSTANCE_ {
	SELECT  SYSDATE, USER FROM    SYS.DUAL
}

OTSRECEI_CGFKCHK_OMS_REQ_OMS_REQ_MODUL_ {
	SELECT OMS_MODULES.DESCRIPTION FROM OMS_MODULES WHERE OMS_MODULES.MODULE_TYPE = 'REPORT' AND OMS_MODULES.MODULE_NAME =:MODULENAME
}

OTSRECEI_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'ROLE_BASE'
}

OTSRECEI_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'SYS' AND PROFILE_CODE = 'ROLE_PSWD'
}

OTSRECEI_RUN_REPORT_ {
	SELECT PROFILE_VALUE FROM SYSTEM_PROFILES WHERE PROFILE_TYPE = 'CLIENT' AND PROFILE_CODE = 'REPORT_ALIAS'
}

OTSRECEI_RUN_REPORT_ {
	select
	LTRIM(RTRIM(DESCRIPTION))
from
	SYSTEM_PROFILES
where
	PROFILE_CODE = PROFILE_CODE
	and PROFILE_TYPE = PROFILE_TYPE
}
OTSRECEI_CHECK_VALID_RECEIPTS {
SELECT 'Y'
     		FROM offender_transactions OT
   		 WHERE ot.caseload_id = :caseload_id
         AND ot.receipt_number IS NOT NULL
         AND ot.receipt_number LIKE :caseload_id||'%'
         AND ot.receipt_printed_flag IS NULL
         AND (:agy_loc_id IS NULL 
            OR EXISTS (SELECT 1
             		        FROM offender_bookings ob
            		       WHERE ob.root_offender_id = ot.offender_id
            		         AND ob.agy_loc_id = :agy_loc_id)
             )	       
         AND EXISTS (SELECT 1 
                       FROM transaction_types tt
                      WHERE tt.txn_usage = :txn_usage
                        AND tt.txn_type = ot.txn_type) 
         AND (:create_user_id IS NULL 
            OR EXISTS (SELECT 1 
                       FROM gl_transactions gt
                      WHERE gt.txn_id = ot.txn_id
                        AND gt.txn_entry_seq = ot.txn_entry_seq
                        AND gt.create_user_id = :create_user_id))
    }
OTSRECEI_GET_DEFAULT_COPIES {
	SELECT DEFAULT_COPY FROM OMS_MODULES  WHERE MODULE_NAME = 'OTRRECEI' AND MODULE_TYPE = 'REPORT'
}
OTSRECEI_RECEIPT_NUM_EXIST {
	  select COUNT (1) from offender_transactions off_txn where off_txn.caseload_id = :caseloadId and off_txn.receipt_number = coalesce (:caseloadId || LPAD(:receiptNumber, 6, '0'), off_txn.receipt_number) and exists ( select 1 from offender_bookings ob left join living_units lu on ob.living_unit_id = lu.living_unit_id and ob.offender_book_id = off_txn.offender_book_id and ( lu.active_flag = 'Y' or lu.active_flag is null) and ob.agy_loc_id = coalesce (:agyLocId, ob.agy_loc_id)) and exists ( select 1 from gl_transactions gt where gt.txn_id = off_txn.txn_id and gt.txn_entry_seq = off_txn.txn_entry_seq and gt.create_user_id = coalesce (:userId, gt.create_user_id))
}
OTSRECEI_GENERATE_OTRRECEI_REPORT {
select
	ot.txn_entry_date txn_date,
	ot.receipt_number rec_num,
	ot.transfer_caseload_id t_csld,
	ot.txn_entry_amount amt,
	o.last_name || ', ' || o.first_name || ' ' || COALESCE(o.middle_name,'') off_name,
	ot.offender_id off_id,
	ot.txn_entry_desc txn_desc,
	COALESCE(ot.modify_user_id,ot.create_user_id) user_id,
	ot.payee_name_text payee_name,
	bcr.cheque_number::varchar check_num,
	lu.description as location,
	ob.offender_book_id book_id,
	o.offender_id_display off_id_display,
	ot.REMITTER_ID remitter_id,
	ot.remitter_name remitter_name,
	ot.txn_id,
	ot.txn_posting_type,
	ac.ACCOUNT_CODE::varchar ACCOUNT_CODE,
	RC.DESCRIPTION ACCOUNT_NAME,
	SP.DESCRIPTION CLIENT,
	C.DESCRIPTION CLDESC
from
	system_profiles sp,
	reference_codes rc,
	offenders o,
	caseloads c,
	account_codes ac,
	offender_bookings ob
left outer join agency_internal_locations lu on
	(ob.living_unit_id = lu.internal_location_id
		and 'Y' = lu.active_flag)
,
	offender_transactions ot
left outer join bank_cheque_registers bcr on
	(ot.txn_id = bcr.txn_id)
where
	(ot.receipt_number is not null
		and ot.receipt_number::text <> '')
		--and #PARAM
	and ac.SUB_ACCOUNT_TYPE = ot.SUB_ACCOUNT_TYPE
	and AC.CASELOAD_TYPE = :caseloadType
	and RC.DOMAIN = 'SUB_AC_TYPE'
	and RC.CODE = ot.SUB_ACCOUNT_TYPE
	and ot.caseload_id = :caseloadId
	and o.root_offender_id = ot.offender_id
	and sp.PROFILE_CODE = 'CLIENT'
	and sp.PROFILE_TYPE = 'CLIENT'
	and C.CASELOAD_ID = OT.CASELOAD_ID
	and C.CASELOAD_TYPE = AC.CASELOAD_TYPE
	and exists (
	select
		1
	from
		OFFENDER_TRUST_ACCOUNTS
	where
		ACCOUNT_CLOSED_FLAG = 'N'
		and ot.offender_id = OFFENDER_ID)
	and exists (
	select
		1
	from
		caseloads c
	where
		c.caseload_type = ob.booking_type
		and c.caseload_id = :caseloadId)
	and o.offender_id = ob.offender_id
	and (ob.active_flag = 'Y'
		or (ob.active_flag != 'Y'
			and ob.offender_book_id =
                                                  (
			select
				MAX(ob2.offender_book_id)
			from
				offender_bookings ob2
			where
				exists (
				select
					1
				from
					caseloads c
				where
					c.caseload_type = ob2.booking_type
					and c.caseload_id = :caseloadId)
				and ob2.root_offender_id = ot.offender_id)
                                                   )
             )
	and exists (
	select
		1
	from
		transaction_types tt
	where
		tt.txn_usage = 'R'
		and tt.txn_type = ot.txn_type
	limit 1)
order by
	ot.receipt_number
		

}
OTSRECEI_GENERATE_OTRDRECE_REPORT {
	--SELECT REF.domain, sp.description client, cl.description cl_desc, ot.txn_entry_date txn_date, ot.receipt_number rec_num, ot.remitter_name remitter, ot.txn_entry_amount amt, va.last_name|| ', '|| va.first_name|| ' '|| va.middle_name off_name, va.offender_id_display off_id, ot.txn_entry_desc txn_desc, ot.modify_user_id user_id, ot.payee_name_text payee_name, to_char(bcr.cheque_number) check_num, lu.description location, ob.booking_no book_id FROM system_profiles sp, caseloads cl, offender_transactions ot, reference_domains REF, bank_cheque_registers bcr, offenders va, offender_bookings ob, living_units lu, transaction_types tt WHERE #PARAM AND sp.profile_type = 'CLIENT' AND sp.profile_code = 'CLIENT' AND ot.receipt_number IS NOT NULL AND cl.caseload_id = :caseloadId AND ot.caseload_id = :caseloadId AND lu.living_unit_id(+) = ob.living_unit_id AND va.offender_id = ob.offender_id AND va.root_offender_id = ot.offender_id AND (ob.active_flag = 'Y' or (ob.active_flag != 'Y' AND ob.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = ob.root_offender_id))) AND REF.domain IN (SELECT domain FROM reference_domains WHERE ROWNUM <= 1) AND tt.txn_type = ot.txn_type AND tt.txn_usage = 'D' AND ot.txn_id = bcr.txn_id(+) ORDER BY ot.receipt_number
	select
	REF.domain,
	sp.description client,
	cl.description cl_desc,
	ot.txn_entry_date txn_date,
	ot.receipt_number rec_num,
	ot.remitter_name remitter,
	ot.txn_entry_amount amt,
	va.last_name || ', ' || va.first_name || ' ' || COALESCE(va.middle_name,'') off_name,
	va.offender_id_display off_id,
	ot.txn_entry_desc txn_desc,
	COALESCE(ot.modify_user_id,ot.create_user_id) user_id,
	ot.payee_name_text payee_name,
	bcr.cheque_number::varchar check_num,
	lu.description as location,
	ob.booking_no book_id
from
	offenders va,
	transaction_types tt,
	system_profiles sp,
	reference_domains ref,
	caseloads cl,
	offender_transactions ot
left outer join bank_cheque_registers bcr on
	(ot.txn_id = bcr.txn_id)
,
	offender_bookings ob
left outer join living_units lu on
	(ob.living_unit_id = lu.living_unit_id)
where
	#PARAM
	and
	sp.profile_type = 'CLIENT'
	and sp.profile_code = 'CLIENT'
	and coalesce(:receipt_number, '') != ' '
	--and case when (:receipt_number)is not null then ot.receipt_number =:receipt_number else ''='' end
	and cl.caseload_id = :caseloadId
	and ot.caseload_id = :caseloadId
	and va.offender_id = ob.offender_id
	and va.root_offender_id = ot.offender_id
	and (ob.active_flag = 'Y'
		or (ob.active_flag != 'Y'
			and ob.offender_book_id =
           (
			select
				MAX(ob2.offender_book_id)
			from
				offender_bookings ob2
			where
				ob2.root_offender_id = ob.root_offender_id)))
	and REF.domain in (
	select
		domain
	from
		reference_domains
	limit 1)
	and tt.txn_type = ot.txn_type
	and tt.txn_usage = 'D'
order by
	ot.receipt_number
}
OTSRECEI_GET_CURRENCY_SYMBOL {
	SELECT UPPER(PROFILE_VALUE) FROM   SYSTEM_PROFILES WHERE  PROFILE_TYPE = 'DISPLAY' AND PROFILE_CODE = 'CURRENCY'
}