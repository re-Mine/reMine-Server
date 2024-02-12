package com.gdsc.remine.declaration.domain.repository;

import com.gdsc.remine.declaration.domain.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    @Query("select d from Declaration d " +
            "join fetch d.member " +
            "order by d.createdDate desc " +
            "limit 9 ")
    List<Declaration> findRecentDeclarations();

    @Query("select d from Declaration d " +
            "where d.createdDate >= :oneWeekAgo and d.member.id = :memberId " +
            "order by d.createdDate desc ")
    List<Declaration> findInRecent7Day(
            @Param("oneWeekAgo") final LocalDateTime oneWeekAgo,
            @Param("memberId") final Long loginMemberId
    );

    @Query("select count(d) from Declaration d " +
            "where d.createdDate >= :oneDayAgo ")
    Long countInRecent1Day(
            @Param("oneDayAgo") final LocalDateTime oneDayAgo
    );
}
