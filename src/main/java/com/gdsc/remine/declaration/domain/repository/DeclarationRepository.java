package com.gdsc.remine.declaration.domain.repository;

import com.gdsc.remine.declaration.domain.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
}
