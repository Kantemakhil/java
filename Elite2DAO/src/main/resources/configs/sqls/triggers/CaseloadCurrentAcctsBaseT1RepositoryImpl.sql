GETTING_CHQ_BOOKS_EXISTS_FLAG{
  SELECT 'Y'
       FROM BANK_CHEQUE_BOOKS
      WHERE caseload_id =  :caseload_id
        AND account_code = :account_code;
}

 UPDATE_BANK_CHEQUE_BOOKS{
      update
	bank_cheque_books
set
	account_number = :bankAccountNumber
where
	caseload_id = :caseload_id
	and account_code = :account_code;
 }