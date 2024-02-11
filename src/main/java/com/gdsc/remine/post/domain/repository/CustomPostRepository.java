package com.gdsc.remine.post.domain.repository;

import com.gdsc.remine.post.domain.Post;
import com.gdsc.remine.post.dto.response.PostDetailResponse;
import com.gdsc.remine.post.dto.response.PostElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomPostRepository extends JpaRepository<Post, Long> {
    @Query("select new com.gdsc.remine.post.dto.response.PostElement(" +
                "m.name, " +
                "m.profileImage, " +
                "p.id, " +
                "p.createdDate, " +
                "p.content, " +
                "(l.id is not null) " +
            ") from Post p " +
            "join Member m on p.member = m " +
            "left join Likes l on l.post = p and l.member.id = :loginMemberId " +
            "order by p.createdDate desc ")
    Page<PostElement> findPreviewPost(
            @Param("loginMemberId") final Long loginMemberId,
            final Pageable pageable
    );

    @Query("select new com.gdsc.remine.post.dto.response.PostDetailResponse(" +
                "m.name, " +
                "m.profileImage, " +
                "p.createdDate, " +
                "p.content, " +
                "(l.id is not null) " +
            ") from Post p " +
            "join Member m on p.member = m " +
            "left join Likes l on l.post = p and l.member.id = :loginMemberId " +
            "where p.id = :postId ")
    PostDetailResponse findDetailPostInfo(
            @Param("loginMemberId") final Long loginMemberId,
            @Param("postId") final Long postId
    );
}
