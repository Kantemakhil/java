IMAGES_TI_IMAGE_ORIGINALS{
insert into IMAGE_ORIGINALS (IMAGE_ID, CREATE_USER_ID , create_datetime , modify_datetime ) select :imageId, :createUserId, current_timestamp, null from DUAL where not exists ( select 'X' from IMAGE_ORIGINALS where IMAGE_ID = :imageId);
}
