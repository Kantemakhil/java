update
	victim_linked_offenders
set
	associated_legal_case = null
where
	encode(associated_legal_case, 'escape')::jsonb->>'orderType' = 'undefined'
	or encode(associated_legal_case, 'escape')::jsonb->>'orderNo' = 'undefined';