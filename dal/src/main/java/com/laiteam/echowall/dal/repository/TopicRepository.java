package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Id> {

    List<Topic> findTop2ByOrderByIdAsc();
}