package com.mylabs.fuelmock.repository;

import com.mylabs.fuelmock.domain.Abastecimento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Abastecimento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}
