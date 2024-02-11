package com.gdsc.remine.post.domain.repository;

import com.gdsc.remine.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p " +
            "join fetch p.member " +
            "left join Likes l on l.post = p " +
            "group by p.id " +
            "order by count(p.id) desc limit 10 ")
    List<Post> findFamousPost();
}
