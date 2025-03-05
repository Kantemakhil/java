update
    json_spec
set
    json_specs = '[{"operation":"shift","spec":{"staffDetails":{"*":{"user_id":"header.intUser.intUserId","first_name":"header.intUser.intUserName","date_time":["header.intDatetime","trailer.responseDatetime"]}},"correlationId":"header.intCorrelationId","photographListDetails":{"*":{"image_id":"body.photos[#2].photoId","capture_date":"body.photos[#2].photographedDate","image_thumbnail":"body.photos[#2].Photo"}}}},{"operation":"default","spec":{"header":{"intId":"SAPOLPhotoList"}}},{"operation":"modify-default-beta","spec":{"body":{"photos":[]},"trailer":{"respCode":"TBD","errMsg":""}}}]',
    modify_datetime = current_timestamp,
    modify_user_id = 'OMS_OWNER'
where
    spec_key = 'PHOTOGRAPH_LIST_RES_FORMATTER'; 