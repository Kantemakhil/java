GET_IWP_DOC_SEQ{ 
select nextval('IWP_DOCS_SEQ') from dual
}

GET_OFFENDER_DOCS { 

select id.document_id ,id.offender_book_id, id.document_name ,id.template_id,id.comment_text ,id.active_flag,id.object_id ,id.object_type,id.document_status,id.document_body ,id.date_created,id.date_modified,id.user_created ,id.user_modified ,id.create_datetime ,id.modify_datetime,id.document_context ,id.signed_doc, it.template_name ,it.description,it.template_body ,it.signature_access,om.description module_description from iwp_documents id ,iwp_templates it,oms_modules om


 where id.template_id = it.template_id  and id.object_type=om.module_name and id.offender_book_id=:offenderBookId  order by id.create_datetime desc
}

GET_IWP_DOC_BY_MODULE_OFFENDER_ID_OBJECT_ID{ 
select
	id.document_id ,
	id.offender_book_id,
	id.document_name ,
	id.template_id,
	id.comment_text ,
	id.active_flag,
	id.object_id ,
	id.object_type,
	id.document_status,
	id.document_body ,
	id.date_created,
	id.date_modified,
	id.user_created ,
	id.user_modified ,
	id.create_datetime ,
	id.modify_datetime,
	id.document_context ,
	id.signed_doc,
	it.template_name ,
	it.description,
	it.template_body ,
	it.signature_access
from
	iwp_documents id ,
	iwp_templates it
where
	id.template_id = it.template_id
	and id.object_type = :moduleName
	and id.offender_book_id =:offenderBookId  
	and id.object_Id =:objectId
order by
	id.create_datetime desc

}

GET_IWP_DOC_BY_MODULE_OFFENDER_BOOK_ID{ 
select
	id.document_id ,
	id.offender_book_id,
	id.document_name ,
	id.template_id,
	id.comment_text ,
	id.active_flag,
	id.object_id ,
	id.object_type,
	id.document_status,
	id.document_body ,
	id.date_created,
	id.date_modified,
	id.user_created ,
	id.user_modified ,
	id.create_datetime ,
	id.modify_datetime,
	id.document_context ,
	id.signed_doc,
	it.template_name ,
	it.description,
	it.template_body ,
	it.signature_access
from
	iwp_documents id ,
	iwp_templates it
where
	id.template_id = it.template_id
	and id.object_type = :moduleName
	and id.offender_book_id =:offenderBookId  
order by
	id.create_datetime desc

}

GET_IWP_DOC_BY_MODULE_OBJECT_ID{ 
select
	id.document_id ,
	id.offender_book_id,
	id.document_name ,
	id.template_id,
	id.comment_text ,
	id.active_flag,
	id.object_id ,
	id.object_type,
	id.document_status,
	id.document_body ,
	id.date_created,
	id.date_modified,
	id.user_created ,
	id.user_modified ,
	id.create_datetime ,
	id.modify_datetime,
	id.document_context ,
	id.signed_doc,
	it.template_name ,
	it.description,
	it.template_body ,
	it.signature_access
from
	iwp_documents id ,
	iwp_templates it
where
	id.template_id = it.template_id
	and id.object_type = :moduleName
	and id.object_Id =:objectId 
order by
	id.create_datetime desc

}

GET_STAFF_ELITE_DOC_DELETE_ROLES {
	select * from STAFF_MEMBER_ROLES where staff_id in (select staff_id from staff_members sm where user_id =:userId) and role_code  in('ENH_DEL_EDOC','DEL_EDOC')
}
DELETE_ELITE_DOCUMENT{ 
   delete from iwp_documents where document_id=:documentId
}
UPDATE_ELITE_DOC_MODIFY_USER{ 
update iwp_documents set modify_user_id=:user,del_reason=:deleteReason where document_id=:documentId
}

