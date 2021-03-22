package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.Follow;
import com.laiteam.echowall.dal.entity.FollowId;
import com.laiteam.echowall.dal.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
    @Query(value = "select p from Profile p left join Follow f on p.user = f.followId where f.userId = (:id) order by f.create_date desc")
    Page<Profile> findAllByUserId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "delete from Follow f where f.userId = (:userId) and f.followId = (:followId)")
    void deleteFollowByFollowId(Long userId, Long followId);

    Optional<Follow> findFollowByUserIdAndFollowId(Long userId, Long followId);
}
