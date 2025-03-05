GET_CASELOAD_TYPE{
SELECT caseload_type FROM caseloads  WHERE caseload_id =:p_caseload_id
}
GET_INSTITUTION_OFFENDERS{
SELECT a.last_name
               ,a.first_name
               ,a.offender_id
               ,a.root_offender_id
               ,a.offender_id_display
               ,a.agy_loc_id
               ,a.prison_location
               ,a.offender_book_id
               ,a.middle_name
           FROM V_HEADER_BLOCK_FN(:USERID) a
          WHERE 
              ( coalesce(:p_last_name ::text, '') = '' OR
                a.last_name LIKE (:p_last_name || '%')) AND
                ( coalesce(:p_first_name ::text, '') = '' OR
                a.first_name LIKE (:p_first_name || '%')) AND
                ( coalesce(:p_middle_name ::text, '') = '' OR
                a.middle_name LIKE (:p_middle_name || '%')) AND
                ( coalesce(:p_offender_id_display ::text, '') = '' OR
                a.offender_id_display =:p_offender_id_display) AND
                (( 'Y' = :lv_active_flag AND
                (a.offender_book_id IN
                (SELECT ob2.offender_book_id
                      FROM offender_bookings ob2
                     WHERE ob2.root_offender_id = a.root_offender_id AND
                           ob2.active_flag = 'Y' AND
                           ((coalesce(:p_agy_loc_id ::text, '') = ''  AND EXISTS
                            (SELECT cal.agy_loc_id
                                FROM caseload_agency_locations cal
                                    ,caseloads                 csld
                               WHERE cal.caseload_id =:p_caseload_id AND
                                     csld.caseload_type = 'INST' AND
                                     csld.caseload_id = cal.caseload_id AND
                                     cal.agy_loc_id = ob2.agy_loc_id AND
                                     ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type))) OR
                           ( coalesce (:p_agy_loc_id ,'') != '' AND
                           a.living_unit_id IN
                           (with recursive cte as (
				select
					liv.living_unit_id,
					liv.agy_loc_id
				from
					living_units liv
				where
					coalesce(:lv_living_unit_id::int8 , 0) = 0
						or 
						liv.living_unit_id = :lv_living_unit_id
				union all
					select
						liv.living_unit_id,
						liv.agy_loc_id
					from
						living_units liv
					join cte c on
						(c.living_unit_id = liv.parent_living_unit_id))
				select
					living_unit_id
				from
					cte
				where
					agy_loc_id = :p_agy_loc_id) AND
                           EXISTS (SELECT cal.agy_loc_id
                                      FROM caseload_agency_locations cal
                                          ,caseloads                 csld
                                     WHERE cal.caseload_id =:p_caseload_id AND
                                           csld.caseload_type = 'INST' AND
                                           csld.caseload_id = cal.caseload_id AND
                                           cal.agy_loc_id = ob2.agy_loc_id AND
                                           ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type))))))) OR
                ( ( coalesce(:lv_active_flag ::text, '') = '' or 'N' =:lv_active_flag) = 'N' AND
                (a.offender_book_id =
                (SELECT MAX(ob2.offender_book_id)
                      FROM offender_bookings ob2
                     WHERE ob2.root_offender_id = a.root_offender_id AND
                           EXISTS
                     (SELECT cal.agy_loc_id
                              FROM caseload_agency_locations cal
                                  ,caseloads                 csld
                             WHERE cal.caseload_id =:p_caseload_id AND
                                   csld.caseload_type = 'INST' AND
                                   csld.caseload_id = cal.caseload_id AND
                                   cal.agy_loc_id = ob2.agy_loc_id AND
                                   ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type)) AND NOT EXISTS
                     (SELECT '1'
                              FROM offender_bookings         bk
                                  ,caseload_agency_locations cal
                                  ,caseloads                 csld
                             WHERE bk.root_offender_id = ob2.root_offender_id AND
                                   bk.active_flag = 'Y' AND
                                   csld.caseload_type = 'INST' AND
                                   csld.caseload_id = cal.caseload_id AND
                                   cal.agy_loc_id = bk.agy_loc_id AND
                                    ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type))))) OR
                (( coalesce(:lv_active_flag ::text, '') = '')  AND
                ((a.offender_book_id IN
                (SELECT ob2.offender_book_id
                       FROM offender_bookings ob2
                      WHERE ob2.root_offender_id = a.root_offender_id AND
                            ob2.active_flag = 'Y' AND EXISTS
                      (SELECT cal.agy_loc_id
                               FROM caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE cal.caseload_id =:p_caseload_id AND
                                    csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = ob2.agy_loc_id AND
                                     ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type)))) OR
                (a.offender_book_id =
                (SELECT MAX(ob2.offender_book_id)
                       FROM offender_bookings ob2
                      WHERE ob2.root_offender_id = a.root_offender_id AND
                            EXISTS
                      (SELECT cal.agy_loc_id
                               FROM caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE cal.caseload_id =:p_caseload_id AND
                                    csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = ob2.agy_loc_id AND
                                     ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type)) AND
                            NOT EXISTS
                      (SELECT '1'
                               FROM offender_bookings         bk
                                   ,caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE bk.root_offender_id =
                                    ob2.root_offender_id AND
                                    bk.active_flag = 'Y' AND
                                    csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = bk.agy_loc_id AND
                                    ( coalesce(:lv_caseload_type ::text, '') = '' or 'INST' =:lv_caseload_type)))))))
          ORDER BY 1
                  ,2
                  ,3
                  ,7            
}
GET_COMMUNITY_OFFENDERS{
SELECT m.last_name ,m.first_name ,m.offender_id ,m.root_offender_id ,m.offender_id_display ,m.agy_loc_id ,m.prison_location ,m.offender_book_id ,m.middle_name FROM V_HEADER_BLOCK_FN(:USERID) m WHERE (:p_last_name IS NULL OR m.last_name LIKE (:p_last_name || '%')) AND (:p_first_name IS NULL OR m.first_name LIKE (:p_first_name || '%')) AND (:p_middle_name IS NULL OR m.middle_name LIKE (:p_middle_name || '%')) AND (:p_offender_id_display IS NULL OR m.offender_id_display =:p_offender_id_display) AND ((:lv_active_flag = 'Y' AND m.offender_book_id IN (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = m.root_offender_id AND ob2.community_active_flag = 'Y' AND (ob2.intake_agy_loc_id <> 'CLOSE' AND ((:p_agy_loc_id IS NULL AND EXISTS (SELECT 'X' FROM offender_booking_agy_locs locs ,caseload_agency_locations cal1 WHERE cal1.caseload_id =:p_caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = ob2.offender_book_id AND locs.removed_date IS NULL AND :lv_caseload_type = 'COMM')) OR (:p_agy_loc_id IS NOT NULL AND ob2.intake_agy_loc_id =:p_agy_loc_id))))) OR (:lv_active_flag = 'N' AND (m.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = m.root_offender_id AND NOT EXISTS (SELECT 'X' FROM offender_bookings ob3 WHERE ob3.community_active_flag = 'Y' AND ob3.root_offender_id = ob2.root_offender_id) AND (ob2.intake_agy_loc_id = 'CLOSE' AND EXISTS (SELECT 'X' FROM offender_booking_agy_locs locs ,caseload_agency_locations cal1 WHERE cal1.caseload_id =:p_caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = ob2.offender_book_id AND :lv_caseload_type = 'COMM'))))) OR (:lv_active_flag IS NULL AND (m.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = m.root_offender_id AND (ob2.community_active_flag = 'Y' OR (NOT EXISTS (SELECT 'X' FROM offender_bookings ob3 WHERE ob3.community_active_flag = 'Y' AND ob3.root_offender_id = ob2.root_offender_id))) AND ((ob2.intake_agy_loc_id <> 'CLOSE' AND EXISTS (SELECT 'X' FROM offender_booking_agy_locs locs ,caseload_agency_locations cal1 WHERE cal1.caseload_id =:p_caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = ob2.offender_book_id AND locs.removed_date IS NULL AND :lv_caseload_type = 'COMM')) OR (ob2.intake_agy_loc_id = 'CLOSE' AND EXISTS (SELECT 'X' FROM offender_booking_agy_locs locs ,caseload_agency_locations cal1 WHERE cal1.caseload_id =:p_caseload_id AND cal1.agy_loc_id = locs.agy_loc_id AND locs.offender_book_id = ob2.offender_book_id AND :lv_caseload_type = 'COMM'))))))) ORDER BY 1 ,2 ,3 ,7
}
GET_TRUST_OFFENDERS{
--select a.last_name , a.first_name , a.offender_id , a.root_offender_id , a.offender_id_display , a.agy_loc_id , a.prison_location , a.offender_book_id , a.middle_name from v_trust_header a where ( coalesce(:p_last_name ::text, '') = '' or a.last_name like (:p_last_name || '%')) and (coalesce(:p_first_name ::text, '')= '' or a.first_name like (:p_first_name || '%')) and (coalesce(:p_middle_name ::text, '') = '' or a.middle_name like (:p_middle_name || '%')) and (coalesce(:p_offender_id_display ::text, '')= '' or a.offender_id_display =:p_offender_id_display) and a.trust_caseload_id =:p_caseload_id and (((:lv_active_flag = 'Y' and a.offender_book_id = ( select MAX(offender_book_id) from offender_bookings ob3 where ob3.root_offender_id = a.root_offender_id and ob3.active_flag = 'Y' and ((( coalesce(:p_agy_loc_id ::text, '') = '') and a.caseload_id =:p_caseload_id and exists ( select '1' from caseload_agency_locations cal , caseloads csld where csld.caseload_id = cal.caseload_id and csld.caseload_type = 'INST' and cal.caseload_id =:p_caseload_id and :lv_caseload_type = 'INST' and cal.agy_loc_id = a.agy_loc_id)) or (coalesce(:p_agy_loc_id ::text, '') = '') and a.agy_loc_id =:p_agy_loc_id and a.caseload_id =:p_caseload_id and a.living_unit_id in (with recursive cte as ( select liv.living_unit_id, liv.agy_loc_id from living_units liv where coalesce(:lv_living_unit_id::text, '') = '' or liv.living_unit_id = :lv_living_unit_id union all select liv.living_unit_id, liv.agy_loc_id from living_units liv join cte c on (c.living_unit_id = liv.parent_living_unit_id)) select living_unit_id from cte where agy_loc_id = :p_agy_loc_id))))) or (:lv_active_flag = 'N' and a.offender_book_id = ( select MAX(ob2.offender_book_id) from offender_bookings ob2 where ob2.root_offender_id = a.root_offender_id and ob2.active_flag = 'N' and a.caseload_id =:p_caseload_id and exists ( select 'X' from caseload_agency_locations cal , caseloads csld 
--where csld.caseload_type = 'INST' and :lv_caseload_type = 'INST' and csld.caseload_id = cal.caseload_id and cal.agy_loc_id = a.agy_loc_id) and not exists ( select 'X' from offender_bookings ob3 , caseload_agency_locations cal , caseloads csld where ob3.root_offender_id = a.root_offender_id and ob3.active_flag = 'Y' and csld.caseload_type = 'INST' and :lv_caseload_type = 'INST' and csld.caseload_id = cal.caseload_id and cal.agy_loc_id = ob3.agy_loc_id))) or (( coalesce(:lv_active_flag ::text, '') = '') and a.caseload_id =:p_caseload_id and ((a.offender_book_id = ( select MAX(ob2.offender_book_id) from offender_bookings ob2
--where ob2.root_offender_id = a.root_offender_id and ob2.active_flag = 'Y' and exists ( select 'X' from caseload_agency_locations cal , caseloads csld where csld.caseload_type = 'INST' and csld.caseload_id = cal.caseload_id and cal.agy_loc_id = ob2.agy_loc_id and :lv_caseload_type = 'INST'))) or (a.offender_book_id = ( select MAX(ob2.offender_book_id) from v_trust_header ob2 where 
--ob2.root_offender_id = a.root_offender_id and exists ( select cal.agy_loc_id from caseload_agency_locations cal , caseloads csld where csld.caseload_type = 'INST' and csld.caseload_id = cal.caseload_id and cal.agy_loc_id = ob2.agy_loc_id and :lv_caseload_type = 'INST') and not exists ( select '1' from offender_bookings bk , caseload_agency_locations cal , caseloads csld where bk.root_offender_id = ob2.root_offender_id and bk.active_flag = 'Y' and csld.caseload_type = 'INST' and csld.caseload_id = cal.caseload_id and cal.agy_loc_id = bk.agy_loc_id and :lv_caseload_type = 'INST')))))) order by 1 , 2 , 3 , 7

SELECT a.last_name
               ,a.first_name
               ,a.offender_id
               ,a.root_offender_id
               ,a.offender_id_display
               ,a.agy_loc_id
               ,a.prison_location
               ,a.offender_book_id
               ,a.middle_name
           FROM v_trust_header a    
          WHERE (coalesce(:p_last_name ::text, '') = '' OR
                a.last_name LIKE (:p_last_name || '%')) AND
                (coalesce(:p_first_name ::text, '') = '' OR
                a.first_name LIKE (:p_first_name || '%')) AND
                (coalesce(:p_middle_name ::text, '') = '' OR
                a.middle_name LIKE (:p_middle_name || '%')) AND
                ( coalesce(:p_offender_id_display ::text, '') = '' OR
                a.offender_id_display =:p_offender_id_display) AND
                a.trust_caseload_id =:p_caseload_id 
                AND
                ((:lv_active_flag = 'Y' AND
                a.offender_book_id =
                (SELECT MAX(offender_book_id)
                     FROM offender_bookings ob3
                    WHERE ob3.root_offender_id = a.root_offender_id AND
                          ob3.active_flag = 'Y' and
                          (( coalesce(:p_agy_loc_id ::text, '') = '' AND
                          a.caseload_id =:p_caseload_id AND EXISTS
                           (SELECT '1'
                               FROM caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE csld.caseload_id = cal.caseload_id AND
                                    csld.caseload_type = 'INST' AND
                                    cal.caseload_id =:p_caseload_id AND
                                    :lv_caseload_type = 'INST' AND
                                    cal.agy_loc_id = a.agy_loc_id)) OR
                          ( coalesce (:p_agy_loc_id ,'') != ''
                         AND
                          a.agy_loc_id =:p_agy_loc_id AND
                          a.caseload_id =:p_caseload_id AND
                          a.living_unit_id IN
                          (with recursive cte as (
				select liv.living_unit_id,liv.agy_loc_id
				from living_units liv
				where coalesce(:lv_living_unit_id::text, '') = ''or liv.living_unit_id = :lv_living_unit_id
				union all select
						liv.living_unit_id,
						liv.agy_loc_id
					from
						living_units liv
					join cte c on
						(c.living_unit_id = liv.parent_living_unit_id))
				select
					living_unit_id
				from
					cte
				where
					agy_loc_id = :p_agy_loc_id))))) OR
                (:lv_active_flag = 'N' AND
                a.offender_book_id =
                (SELECT MAX(ob2.offender_book_id)
                     FROM offender_bookings ob2
                    WHERE ob2.root_offender_id = a.root_offender_id AND
                          ob2.active_flag = 'N' AND
                          a.caseload_id =:p_caseload_id AND EXISTS
                    (SELECT 'X'
                             FROM caseload_agency_locations cal
                                 ,caseloads                 csld
                            WHERE csld.caseload_type = 'INST' AND
                                  :lv_caseload_type = 'INST' AND
                                  csld.caseload_id = cal.caseload_id AND
                                  cal.agy_loc_id = a.agy_loc_id) AND
                          NOT EXISTS
                    (SELECT 'X'
                             FROM offender_bookings         ob3
                                 ,caseload_agency_locations cal
                                 ,caseloads                 csld
                            WHERE ob3.root_offender_id = a.root_offender_id AND
                                  ob3.active_flag = 'Y' AND
                                  csld.caseload_type = 'INST' AND
                                  :lv_caseload_type = 'INST' AND
                                  csld.caseload_id = cal.caseload_id AND
                                  cal.agy_loc_id = ob3.agy_loc_id))) or                          
                ( coalesce(:lv_active_flag ::text, '') = '' AND a.caseload_id =:p_caseload_id AND
                ((a.offender_book_id =
                (SELECT MAX(ob2.offender_book_id)
                       FROM offender_bookings ob2
                      WHERE ob2.root_offender_id = a.root_offender_id AND
                            ob2.active_flag = 'Y' AND EXISTS
                      (SELECT 'X'
                               FROM caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = ob2.agy_loc_id AND
                                    :lv_caseload_type = 'INST'))) OR
                (a.offender_book_id =
                (SELECT MAX(ob2.offender_book_id)
                       FROM v_trust_header ob2
                      WHERE ob2.root_offender_id = a.root_offender_id AND
                            EXISTS
                      (SELECT cal.agy_loc_id
                               FROM caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = ob2.agy_loc_id AND
                                    :lv_caseload_type = 'INST') AND
                            NOT EXISTS
                      (SELECT '1'
                               FROM offender_bookings         bk
                                   ,caseload_agency_locations cal
                                   ,caseloads                 csld
                              WHERE bk.root_offender_id =
                                    ob2.root_offender_id AND
                                    bk.active_flag = 'Y' AND
                                    csld.caseload_type = 'INST' AND
                                    csld.caseload_id = cal.caseload_id AND
                                    cal.agy_loc_id = bk.agy_loc_id AND
                                    :lv_caseload_type = 'INST'))))))
          ORDER BY 1
                  ,2
                  ,3
                  ,7

}


GET_PAYROLL_OFFENDERS{
select p.last_name , p.first_name , p.offender_id , p.root_offender_id , p.offender_id_display , p.agy_loc_id , p.prison_location , p.offender_book_id , p.middle_name from v_payroll_header p 
--where (coalesce(:p_last_name ::text, '') = '' or p.last_name like (:p_last_name || '%')) and (coalesce(:p_first_name ::text, '') = '' or p.first_name like (:p_first_name || '%')) and (coalesce(:p_middle_name ::text, '') = '' or p.middle_name like (:p_middle_name || '%')) and 
coalesce(:p_offender_id_display ::text, '') = '' or p.offender_id_display = :p_offender_id_display) and p.payroll_caseload_id = :p_caseload_id and p.payroll_caseload_id = p.caseload_id and (:lv_active_flag = 'Y' and p.offender_book_id = ( select MAX(ob2.offender_book_id) from offender_bookings ob2 where ob2.root_offender_id = p.root_offender_id and ob2.active_flag = 'Y' and 
(((coalesce(:p_agy_loc_id ::text, '') = '' and exists ( select '1' from caseload_agency_locations cal , caseloads csld where csld.caseload_id = cal.caseload_id and csld.caseload_type = 'INST' and cal.caseload_id = :p_caseload_id and :lv_caseload_type = 'INST' and cal.agy_loc_id = ob2.agy_loc_id)) or (:p_agy_loc_id is not null and ob2.agy_loc_id =:p_agy_loc_id and ob2.living_unit_id in 
(with recursive cte as ( select liv.living_unit_id, liv.agy_loc_id from living_units liv where coalesce(:lv_living_unit_id::text, '') = '' or liv.living_unit_id = :lv_living_unit_id union all select liv.living_unit_id, liv.agy_loc_id from living_units liv join cte c on (c.living_unit_id = liv.parent_living_unit_id))
select living_unit_id from cte where agy_loc_id = :p_agy_loc_id)))))) order by 1 , 2 , 3 , 7
}

GET_PAYROLL_OFFENDERS_ONE{
SELECT p.last_name ,p.first_name ,p.offender_id ,p.root_offender_id ,p.offender_id_display ,p.agy_loc_id ,p.prison_location ,p.offender_book_id ,p.middle_name FROM v_payroll_header p WHERE (:p_last_name IS NULL OR p.last_name LIKE (:p_last_name || '%')) AND (:p_first_name IS NULL OR p.first_name LIKE (:p_first_name || '%')) AND (:p_middle_name IS NULL OR p.middle_name LIKE (:p_middle_name || '%')) AND (:p_offender_id_display IS NULL OR p.offender_id_display =:p_offender_id_display) AND p.payroll_caseload_id =:p_caseload_id AND p.payroll_caseload_id = p.caseload_id AND (:lv_active_flag = 'N' AND p.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = p.root_offender_id AND ob2.active_flag = 'N' AND EXISTS (SELECT cal.agy_loc_id FROM caseload_agency_locations cal ,caseloads csld WHERE csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = ob2.agy_loc_id AND :lv_caseload_type = 'INST') AND NOT EXISTS (SELECT '1' FROM offender_bookings bk ,caseload_agency_locations cal ,caseloads csld WHERE bk.root_offender_id = ob2.root_offender_id AND bk.active_flag = 'Y' AND csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = bk.agy_loc_id AND :lv_caseload_type = 'INST'))) ORDER BY 1 ,2 ,3 ,7
}
GET_PAYROLL_OFFENDERS_TWO{
SELECT p.last_name ,p.first_name ,p.offender_id ,p.root_offender_id ,p.offender_id_display ,p.agy_loc_id ,p.prison_location ,p.offender_book_id ,p.middle_name FROM v_payroll_header p WHERE (:p_last_name IS NULL OR p.last_name LIKE (:p_last_name || '%')) AND (:p_first_name IS NULL OR p.first_name LIKE (:p_first_name || '%')) AND (:p_middle_name IS NULL OR p.middle_name LIKE (:p_middle_name || '%')) AND (:p_offender_id_display IS NULL OR p.offender_id_display = :p_offender_id_display) AND p.payroll_caseload_id = :p_caseload_id AND p.payroll_caseload_id = p.caseload_id AND (:lv_active_flag IS NULL AND (p.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = p.root_offender_id AND EXISTS (SELECT cal.agy_loc_id FROM caseload_agency_locations cal ,caseloads csld WHERE csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = ob2.agy_loc_id AND :lv_caseload_type = 'INST') AND NOT EXISTS (SELECT '1' FROM offender_bookings bk ,caseload_agency_locations cal ,caseloads csld WHERE bk.root_offender_id = ob2.root_offender_id AND bk.active_flag = 'Y' AND csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = bk.agy_loc_id AND :lv_caseload_type = 'INST'))) OR (p.offender_book_id = (SELECT MAX(ob2.offender_book_id) FROM offender_bookings ob2 WHERE ob2.root_offender_id = p.root_offender_id AND ob2.active_flag = 'Y' AND (:p_agy_loc_id IS NULL AND p.caseload_id = :p_caseload_id AND EXISTS (SELECT '1' FROM caseload_agency_locations cal ,caseloads csld WHERE csld.caseload_id = cal.caseload_id AND csld.caseload_type = 'INST' AND cal.caseload_id = :p_caseload_id AND :lv_caseload_type = 'INST' AND cal.agy_loc_id = ob2.agy_loc_id))))) ORDER BY 1 ,2 ,3 ,7
}
GET_COMMISSARY_OFFENDERS{

SELECT a.last_name ,a.first_name ,a.offender_id ,a.root_offender_id ,a.offender_id_display ,a.agy_loc_id ,a.prison_location ,a.offender_book_id ,a.middle_name FROM v_trust_header a WHERE (:p_last_name IS NULL OR a.last_name LIKE (:p_last_name || '%')) AND (:p_first_name IS NULL OR a.first_name LIKE (:p_first_name || '%')) AND (:p_middle_name IS NULL OR a.middle_name LIKE (:p_middle_name || '%')) AND (:p_offender_id_display IS NULL OR a.offender_id_display = :p_offender_id_display) AND :lv_caseload_type = 'INST' AND a.caseload_id = :p_caseload_id AND (((a.trust_caseload_id = a.caseload_id AND a.offender_book_id = ((case when (SELECT DISTINCT ('Y') FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND active_flag = 'Y' AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'Y' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id =:p_caseload_id)) = 'Y' then (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND active_flag = 'Y' AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'Y' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id = :p_caseload_id)) else (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'Y' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id = :p_caseload_id)) end )))) OR (a.trust_caseload_id = a.commissary_trust_caseload AND a.offender_book_id = ((case when (SELECT DISTINCT ('Y') FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND active_flag = 'Y' AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'N' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id =:p_caseload_id)) = 'Y' then (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND active_flag = 'Y' AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'N' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id = :p_caseload_id)) else (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND EXISTS (SELECT 'X' FROM caseload_agency_locations cal ,caseloads csld WHERE csld.caseload_id = cal.caseload_id AND csld.caseload_id = a.caseload_id)) end ))) OR (a.trust_caseload_id IN (SELECT cs.caseload_id FROM caseloads cs ,caseload_agency_locations ca WHERE cs.trust_commissary_caseload = :p_caseload_id AND ca.caseload_id = cs.caseload_id AND ca.caseload_id = a.trust_caseload_id AND EXISTS (SELECT 1 FROM offender_external_movements oem where (case when oem.direction_code = 'OUT' then oem.from_agy_loc_id else oem.to_agy_loc_id end) = ca.agy_loc_id AND oem.offender_book_id = a.offender_book_id AND (oem.movement_seq = (SELECT MAX(oem1.movement_seq) FROM offender_external_movements oem1 WHERE oem1.offender_book_id = a.offender_book_id)))) AND a.offender_book_id = ((case when (SELECT DISTINCT ('Y') FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND (active_flag = 'Y' OR NOT EXISTS (SELECT 1 FROM offender_bookings obx WHERE obx.root_offender_id = vhb2.root_offender_id AND obx.active_flag = 'Y')) AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'N' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id =:p_caseload_id)) = 'Y' then (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND (active_flag = 'Y' OR NOT EXISTS (SELECT 1 FROM offender_bookings obx WHERE obx.root_offender_id = vhb2.root_offender_id AND obx.active_flag = 'Y')) AND EXISTS (SELECT 'X' FROM caseloads csld WHERE (csld.trust_accounts_flag = 'N' AND csld.commissary_flag = 'Y') AND csld.caseload_type = :lv_caseload_type AND csld.caseload_id = :p_caseload_id)) else (SELECT MAX(vhb2.offender_book_id) FROM offender_bookings vhb2 WHERE vhb2.root_offender_id = a.root_offender_id AND EXISTS (SELECT 'X' FROM caseload_agency_locations cal ,caseload_agency_locations cal1 ,caseloads csld WHERE cal.caseload_id = a.trust_caseload_id AND cal.agy_loc_id = cal1.agy_loc_id AND cal.agy_loc_id NOT IN ('TRN', 'OUT') AND (csld.trust_accounts_flag = 'Y' AND csld.commissary_flag = 'N' AND csld.trust_commissary_caseload IS NOT NULL) AND cal1.caseload_id = csld.caseload_id AND csld.caseload_type = :lv_caseload_type)) end ))) AND (EXISTS (SELECT '1' FROM sales_orders so ,caseloads csld WHERE so.offender_id = a.root_offender_id AND ((so.caseload_id = csld.caseload_id AND csld.commissary_flag = 'Y' AND csld.caseload_id =:p_caseload_id) OR (so.caseload_id = csld.trust_commissary_caseload AND so.trust_caseload_id =:p_caseload_id AND csld.trust_accounts_flag = 'Y' AND csld.commissary_flag = 'N' AND csld.caseload_id =:p_caseload_id))))) ORDER BY 1 ,2 ,3 ,7

}
