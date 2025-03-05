DELETE_RELEASE_PLANS_OTHER_OCCUPENTS {
DELETE rp_other_occupants ro where
ro.release_plan_id in ( select rp.release_plan_id from release_plans rp
WHERE rp.address_id = :addressId )
}