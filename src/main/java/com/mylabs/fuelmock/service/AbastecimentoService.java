package com.mylabs.fuelmock.service;

import com.mylabs.fuelmock.service.dto.AbastecimentoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mylabs.fuelmock.domain.Abastecimento}.
 */
public interface AbastecimentoService {

    /**
     * Save a abastecimento.
     *
     * @param abastecimentoDTO the entity to save.
     * @return the persisted entity.
     */
    AbastecimentoDTO save(AbastecimentoDTO abastecimentoDTO);

    /**
     * Get all the abastecimentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AbastecimentoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" abastecimento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbastecimentoDTO> findOne(Long id);

    /**
     * Delete the "id" abastecimento.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
