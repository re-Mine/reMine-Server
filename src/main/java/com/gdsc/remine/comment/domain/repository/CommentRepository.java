package com.gdsc.remine.comment.domain.repository;

import com.gdsc.remine.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
