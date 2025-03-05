delete
from
	ocdlegls_data
where
	encode(form_info_json, 'escape')::jsonb->>'error' = encode(form_info_json, 'escape')::jsonb->>'error';