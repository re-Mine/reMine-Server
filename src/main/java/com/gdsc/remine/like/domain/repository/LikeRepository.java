package com.gdsc.remine.like.domain.repository;

import com.gdsc.remine.like.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
}
