DELETE_BANK_CLEAR_RECONCILES_TMP{
DELETE FROM bank_clear_reconciles_tmp WHERE caseload_id = :p_caseload_id
}

GET_PROFILE_VALUE{
 SELECT profile_value FROM system_profiles WHERE profile_type = :p_profile_type AND profile_code = :p_profile_code
}

INSERT_BANK_CLEAR_RECONCILES_TMP{
--insert into bank_clear_reconciles_tmp ( txn_id, txn_entry_seq, gl_entry_seq, txn_post_usage, description, txn_entry_amount, caseload_id , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) select gl.txn_id txn_id, gl.txn_entry_seq txn_entry_seq, gl.gl_entry_seq, gl.txn_post_usage, nvl(bcd.payee_name_text, gl.txn_entry_desc), nvl(bcd.txn_entry_amount, decode(gl.txn_post_usage, 'CR', - 999999999, gl.txn_entry_amount)), gl.caseload_id, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP from gl_transactions gl, bank_cheque_data bcd where gl.caseload_id = :p_caseload_id and gl.account_code = :p_account_code and gl.txn_id = bcd.txn_id (+) and ( bcd.caseload_id = :p_caseload_id or bcd.caseload_id is null ) and ( ( gl.txn_post_usage = 'CR' and ( gl.txn_entry_seq = 1 or gl.txn_entry_seq = ( select MIN(glx.txn_entry_seq) from gl_transactions glx where glx.account_code = :p_account_code and glx.txn_id = gl.txn_id ) ) ) or gl.txn_post_usage = 'DR' ) and ( nvl(gl.txn_reversed_flag, 'N') = 'N' or :l_void_flag = 'Y' ) and ( ( :p_select_mode = 'ALL' and ( ( ( gl.bank_statement_date > nvl(:p_last_recon_date, TO_DATE('01-JAN-0001', 'DD-MON-YYYY')) and gl.txn_entry_date <= :p_bank_statement_date and :l_interface_flag = 'Y' ) or ( gl.bank_statement_date = :p_bank_statement_date and :l_interface_flag = 'N' ) ) or ( gl.recon_clear_flag != 'Y' and gl.txn_entry_date <= decode(:l_future_flag, 'Y', trunc(sysdate), :p_bank_statement_date) ) ) ) or ( :p_select_mode = 'CLR' and ( gl.recon_clear_flag = 'Y' and ( ( gl.bank_statement_date > nvl(:p_last_recon_date, TO_DATE('01-JAN-0001', 'DD-MON-YYYY')) and gl.txn_entry_date <= :p_bank_statement_date and :l_interface_flag = 'Y' ) or ( gl.bank_statement_date = :p_bank_statement_date and :l_interface_flag = 'N' ) ) ) ) or ( :p_select_mode = 'NOT_CLR' and ( gl.recon_clear_flag != 'Y' and gl.txn_entry_date <= decode(:l_future_flag, 'Y', trunc(sysdate), :p_bank_statement_date) ) ) )
insert
	into
	bank_clear_reconciles_tmp 
  (txn_id,
	txn_entry_seq,
	gl_entry_seq,
	txn_post_usage,
	description,
	txn_entry_amount,
	caseload_id,
	CREATE_USER_ID,
	CREATE_DATETIME,
	MODIFY_DATETIME )
   select
	gl.txn_id txn_id,
	gl.txn_entry_seq txn_entry_seq,
	gl.gl_entry_seq,
	gl.txn_post_usage,
	coalesce(bcd.payee_name_text, gl.txn_entry_desc),
	coalesce(bcd.txn_entry_amount, case when gl.txn_post_usage = 'CR' then - 999999999 else gl.txn_entry_amount end ),
	gl.caseload_id,
	:createUserId,
	CURRENT_TIMESTAMP,
	CURRENT_TIMESTAMP
from
	gl_transactions gl
left outer join bank_cheque_data bcd on
	(gl.txn_id = bcd.txn_id)
where
	gl.caseload_id = :P_CASELOAD_ID
	and gl.account_code = :P_ACCOUNT_CODE
	and ( bcd.caseload_id = :P_CASELOAD_ID
		or coalesce(bcd.caseload_id::text, '') = '' )
	and ( ( gl.txn_post_usage = 'CR'
		and ( gl.txn_entry_seq = 1
			or gl.txn_entry_seq =
    (
			select
				MIN(glx.txn_entry_seq)
			from
				gl_transactions glx
			where
				glx.account_code = :P_ACCOUNT_CODE
				and
       glx.txn_id = gl.txn_id ) ) )
	or gl.txn_post_usage = 'DR' )
	and ( coalesce(gl.txn_reversed_flag, 'N') = 'N'
		or :L_VOID_FLAG = 'Y' )
	and ( ( :P_SELECT_MODE = 'ALL'
		and ( ( ( gl.bank_statement_date > coalesce(:P_LAST_RECON_DATE, TO_DATE('01-JAN-0001', 'DD-MON-YYYY'))
			and gl.txn_entry_date <= :P_BANK_STATEMENT_DATE
			and :L_INTERFACE_FLAG = 'Y' )
		or ( gl.bank_statement_date = :P_BANK_STATEMENT_DATE
			and :L_INTERFACE_FLAG = 'N' ) )
		or ( gl.recon_clear_flag != 'Y'
			and gl.txn_entry_date <=
			case
				when :L_INTERFACE_FLAG = 'Y' then current_timestamp
				else :P_BANK_STATEMENT_DATE
			end ) ) )
	or ( :P_SELECT_MODE = 'CLR'
		and ( gl.recon_clear_flag = 'Y'
			and ( ( gl.bank_statement_date > coalesce(:P_LAST_RECON_DATE, TO_DATE('01-JAN-0001', 'DD-MON-YYYY'))
				and gl.txn_entry_date <= :P_BANK_STATEMENT_DATE
				and :L_INTERFACE_FLAG = 'Y' )
			or ( gl.bank_statement_date = :P_BANK_STATEMENT_DATE
				and :L_INTERFACE_FLAG = 'N' ) ) ) )
	or ( :P_SELECT_MODE = 'NOT_CLR'
		and ( gl.recon_clear_flag != 'Y'
			and gl.txn_entry_date <=
			case
				when :L_FUTURE_FLAG = 'Y' then current_timestamp
				else :P_BANK_STATEMENT_DATE
			end ) ) )
}

UPDATE_BANK_CLEAR_RECONCILES_TMP{
-- UPDATE bank_clear_reconciles_tmp bcrt SET bcrt.txn_entry_amount = ( SELECT SUM(gl.txn_entry_amount) FROM gl_transactions gl WHERE gl.txn_post_usage = 'CR' AND gl.account_code = :p_account_code AND gl.txn_id = bcrt.txn_id ) WHERE bcrt.txn_entry_amount = - 999999999 AND caseload_id = :p_caseload_id
update
	bank_clear_reconciles_tmp bcrt
set
	txn_entry_amount = (
	select
		SUM(gl.txn_entry_amount)
	from
		gl_transactions gl
	where
		gl.txn_post_usage = 'CR'
		and gl.account_code = :P_ACCOUNT_CODE
		and gl.txn_id = bcrt.txn_id ),
	MODIFY_USER_ID = :modifyUserId ,
	MODIFY_DATETIME = CURRENT_TIMESTAMP
where
	txn_entry_amount = - 999999999
	and caseload_id = :P_CASELOAD_ID;
}

UPDATE_BANK_CLEAR_RECONCILES_TMP_ONE{
--update bank_clear_reconciles_tmp bcrt set MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP, bcrt.reference_no_type = ( select decode(bcr.cheque_number, null, 'TRN', 'CHK') from gl_transactions gl, bank_cheque_registers bcr where gl.txn_id = bcrt.txn_id and gl.txn_entry_seq = bcrt.txn_entry_seq and gl.gl_entry_seq = bcrt.gl_entry_seq and gl.txn_id = bcr.txn_id (+) and ( bcr.caseload_id = gl.caseload_id or bcr.caseload_id is null ) ), bcrt.reference_no = ( select decode(bcr.cheque_number, null, gl.txn_id, bcr.cheque_number) from gl_transactions gl, bank_cheque_registers bcr where gl.txn_id = bcrt.txn_id and gl.txn_entry_seq = bcrt.txn_entry_seq and gl.gl_entry_seq = bcrt.gl_entry_seq and gl.txn_id = bcr.txn_id (+) and ( bcr.caseload_id = gl.caseload_id or bcr.caseload_id is null ) )
update
	bank_clear_reconciles_tmp bcrt
set
	MODIFY_USER_ID = :modifyUserId,
	MODIFY_DATETIME = CURRENT_TIMESTAMP,
	reference_no_type = 
  (
	select
		case
			when coalesce(bcr.cheque_number::text, '') = '' then 'TRN'
			else 'CHK'
		end
	from
		gl_transactions gl
	left outer join bank_cheque_registers bcr on
		(gl.txn_id = bcr.txn_id)
	where
		gl.txn_id = bcrt.txn_id
		and gl.txn_entry_seq = bcrt.txn_entry_seq
		and gl.gl_entry_seq = bcrt.gl_entry_seq
		and ( bcr.caseload_id = gl.caseload_id
			or coalesce(bcr.caseload_id::text, '') = '' )),
			reference_no = 
  (
	select
		case
			when coalesce(bcr.cheque_number::text, '') = '' then gl.txn_id
			else bcr.cheque_number
		end
	from
		gl_transactions gl
	left outer join bank_cheque_registers bcr on
		(gl.txn_id = bcr.txn_id)
	where
		gl.txn_id = bcrt.txn_id
		and gl.txn_entry_seq = bcrt.txn_entry_seq
		and gl.gl_entry_seq = bcrt.gl_entry_seq
		and ( bcr.caseload_id = gl.caseload_id
			or coalesce(bcr.caseload_id::text, '') = '' ) )
}