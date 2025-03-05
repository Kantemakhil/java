 STG_ID_CUR{ 
select
	stg1.stg_id,
	stg2.related_stg_id
from
	(with recursive cte as 
  (
	select
		stg.stg_id stg_id
	from
		security_threat_groups stg
	where
		stg.stg_id = :p_stg_id
union all
	select
		stg.stg_id stg_id
	from
		security_threat_groups stg
	join cte c on
		(c.stg_id = stg.parent_stg_id) )
	select
		*
	from
		cte) stg1,
	(with recursive cte as (
	select
		stg.stg_id related_stg_id
	from
		security_threat_groups stg
	where
		stg.stg_id = :p_related_id
union all
	select
		stg.stg_id related_stg_id
	from
		security_threat_groups stg
	join cte c on
		(c.related_stg_id = stg.parent_stg_id)

)
	select
		*
	from
		cte) stg2
where
	not exists 
    (
	select
		*
	from
		stg_relationships sr
	where
		sr.stg_id = stg1.stg_id
		and
       sr.related_stg_id = stg2.related_stg_id)
union all 
select
	stg1.stg_id,
	stg2.related_stg_id
from
	(with recursive cte as (
	select
		stg.stg_id stg_id
	from
		security_threat_groups stg
	where
		stg.stg_id = :p_related_id
union all
	select
		stg.stg_id stg_id
	from
		security_threat_groups stg
	join cte c on
		(c.stg_id = stg.parent_stg_id)

)
	select
		*
	from
		cte) stg1,
	(with recursive cte as (
	select
		stg.stg_id related_stg_id
	from
		security_threat_groups stg
	where
		stg.stg_id = :p_stg_id
union all
	select
		stg.stg_id related_stg_id
	from
		security_threat_groups stg
	join cte c on
		(c.related_stg_id = stg.parent_stg_id)

)
	select
		*
	from
		cte) stg2
where
	not exists 
    (
	select
		*
	from
		stg_relationships sr
	where
		sr.stg_id = stg1.stg_id
		and
       sr.related_stg_id = stg2.related_stg_id)

  }
  
 
 
 OMS_STG_INSERT{
 --INSERT INTO stg_relationships (stg_id, relationship_seq, relationship_type, related_stg_id, reason, effective_date,  create_user_id, active_flag, expiry_date, expired_by, comment_text) VALUES (each_record.stg_id, :l_seq, 'ENEMY', each_record.related_stg_id, :p_reason, :p_eff_date, :p_user_id, :p_active, :p_expiry_date, :p_expiried_by, :p_expiried_by)             
insert
	into
	stg_relationships (stg_id,
	relationship_seq,
	relationship_type,
	related_stg_id,
	reason,
	effective_date,
	create_user_id,
	active_flag,
	expiry_date,
	expired_by,
	comment_text,
	CREATE_DATETIME,
	MODIFY_DATETIME )
values (:stgId,
:relationshipSeq,
'ENEMY',
:relatedStgId,
:reason,
:effectiveDate,
:createUserId,
:activeFlag,
:expiryDate,
:expiredBy,
:commentText,
CURRENT_TIMESTAMP ,
CURRENT_TIMESTAMP)
 }
 
 SEQ_CUR_RELATIONSHIP_SEQ{ 
SELECT coalesce (MAX(relationship_seq + 1), 1)
           FROM stg_relationships
          WHERE stg_id = :l_stg_id
            AND relationship_type = 'ENEMY' 
            }
      
   
     