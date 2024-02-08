package com.gdsc.remine.post.domain.repository;

import com.gdsc.remine.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
