package com.gdsc.remine.comment.domain.repository;

import com.gdsc.remine.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c " +
            "join fetch c.member " +
            "where c.post.id = :postId " +
            "order by c.createdDate asc ")
    Page<Comment> findCommentsByPost(
            @Param("postId") final Long postId,
            final Pageable pageable
    );
}
