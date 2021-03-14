package com.laiteam.developerforfun.user.gender;

import com.laiteam.developerforfun.user.gender.GenderType;

import java.util.Optional;

public interface GenderService {
    Optional<GenderType> saveGender(GenderType genderType);
}
