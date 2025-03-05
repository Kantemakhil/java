update oms_modules_base_help_url set STATUS='N';

--------- Need to change the BaseUrl According to the Deployment server (ex. http://54.209.90.43:4900/syscon/onlinehelp/Content)
insert into oms_modules_base_help_url(id,base_help_pdf_url,base_help_video_url,base_help_html_url,status,create_datetime,create_user_id,modify_datetime,modify_user_id,seal_flag)
values(nextval('BASE_URL_ID'),null,null,'onlinehelpbaseurl','Y',current_timestamp,'OMS_OWNER',current_timestamp,null,null);

