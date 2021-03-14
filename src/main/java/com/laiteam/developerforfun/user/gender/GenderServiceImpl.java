package com.laiteam.developerforfun.user.gender;

import com.laiteam.developerforfun.user.gender.GenderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService{

    final GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public Optional<GenderType> saveGender(GenderType genderType) {
        return Optional.ofNullable(genderRepository.save(genderType));
    }
}
