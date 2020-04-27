package com.mylabs.fuelmock.service.impl;

import com.mylabs.fuelmock.service.AbastecimentoService;
import com.mylabs.fuelmock.domain.Abastecimento;
import com.mylabs.fuelmock.repository.AbastecimentoRepository;
import com.mylabs.fuelmock.service.dto.AbastecimentoDTO;
import com.mylabs.fuelmock.service.mapper.AbastecimentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Abastecimento}.
 */
@Service
@Transactional
public class AbastecimentoServiceImpl implements AbastecimentoService {

    private final Logger log = LoggerFactory.getLogger(AbastecimentoServiceImpl.class);

    private final AbastecimentoRepository abastecimentoRepository;

    private final AbastecimentoMapper abastecimentoMapper;

    public AbastecimentoServiceImpl(AbastecimentoRepository abastecimentoRepository, AbastecimentoMapper abastecimentoMapper) {
        this.abastecimentoRepository = abastecimentoRepository;
        this.abastecimentoMapper = abastecimentoMapper;
    }

    /**
     * Save a abastecimento.
     *
     * @param abastecimentoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AbastecimentoDTO save(AbastecimentoDTO abastecimentoDTO) {
        log.debug("Request to save Abastecimento : {}", abastecimentoDTO);
        Abastecimento abastecimento = abastecimentoMapper.toEntity(abastecimentoDTO);
        abastecimento = abastecimentoRepository.save(abastecimento);
        return abastecimentoMapper.toDto(abastecimento);
    }

    /**
     * Get all the abastecimentos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AbastecimentoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Abastecimentos");
        return abastecimentoRepository.findAll(pageable)
            .map(abastecimentoMapper::toDto);
    }

    /**
     * Get one abastecimento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AbastecimentoDTO> findOne(Long id) {
        log.debug("Request to get Abastecimento : {}", id);
        return abastecimentoRepository.findById(id)
            .map(abastecimentoMapper::toDto);
    }

    /**
     * Delete the abastecimento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Abastecimento : {}", id);
        abastecimentoRepository.deleteById(id);
    }
}
