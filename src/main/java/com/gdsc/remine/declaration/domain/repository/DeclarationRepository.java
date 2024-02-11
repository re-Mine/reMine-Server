package com.gdsc.remine.declaration.domain.repository;

import com.gdsc.remine.declaration.domain.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    @Query("select d from Declaration d " +
            "join fetch d.member " +
            "order by d.createdDate desc " +
            "limit 9 ")
    List<Declaration> findRecentDeclarations();
}
