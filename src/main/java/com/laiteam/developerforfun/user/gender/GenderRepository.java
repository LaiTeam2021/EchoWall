package com.laiteam.developerforfun.user.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderType, Long> {
    GenderType save(GenderType genderType);
}
