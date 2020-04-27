package com.mylabs.fuelmock.web.rest;

import com.mylabs.fuelmock.service.AbastecimentoService;
import com.mylabs.fuelmock.web.rest.errors.BadRequestAlertException;
import com.mylabs.fuelmock.service.dto.AbastecimentoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mylabs.fuelmock.domain.Abastecimento}.
 */
@RestController
@RequestMapping("/api")
public class AbastecimentoResource {

    private final Logger log = LoggerFactory.getLogger(AbastecimentoResource.class);

    private static final String ENTITY_NAME = "fuelmockAbastecimento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AbastecimentoService abastecimentoService;

    public AbastecimentoResource(AbastecimentoService abastecimentoService) {
        this.abastecimentoService = abastecimentoService;
    }

    /**
     * {@code POST  /abastecimentos} : Create a new abastecimento.
     *
     * @param abastecimentoDTO the abastecimentoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new abastecimentoDTO, or with status {@code 400 (Bad Request)} if the abastecimento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/abastecimentos")
    public ResponseEntity<AbastecimentoDTO> createAbastecimento(@Valid @RequestBody AbastecimentoDTO abastecimentoDTO) throws URISyntaxException {
        log.debug("REST request to save Abastecimento : {}", abastecimentoDTO);
        if (abastecimentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new abastecimento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbastecimentoDTO result = abastecimentoService.save(abastecimentoDTO);
        return ResponseEntity.created(new URI("/api/abastecimentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /abastecimentos} : Updates an existing abastecimento.
     *
     * @param abastecimentoDTO the abastecimentoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abastecimentoDTO,
     * or with status {@code 400 (Bad Request)} if the abastecimentoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the abastecimentoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/abastecimentos")
    public ResponseEntity<AbastecimentoDTO> updateAbastecimento(@Valid @RequestBody AbastecimentoDTO abastecimentoDTO) throws URISyntaxException {
        log.debug("REST request to update Abastecimento : {}", abastecimentoDTO);
        if (abastecimentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AbastecimentoDTO result = abastecimentoService.save(abastecimentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abastecimentoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /abastecimentos} : get all the abastecimentos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of abastecimentos in body.
     */
    @GetMapping("/abastecimentos")
    public ResponseEntity<List<AbastecimentoDTO>> getAllAbastecimentos(Pageable pageable) {
        log.debug("REST request to get a page of Abastecimentos");
        Page<AbastecimentoDTO> page = abastecimentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /abastecimentos/:id} : get the "id" abastecimento.
     *
     * @param id the id of the abastecimentoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the abastecimentoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/abastecimentos/{id}")
    public ResponseEntity<AbastecimentoDTO> getAbastecimento(@PathVariable Long id) {
        log.debug("REST request to get Abastecimento : {}", id);
        Optional<AbastecimentoDTO> abastecimentoDTO = abastecimentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abastecimentoDTO);
    }

    /**
     * {@code DELETE  /abastecimentos/:id} : delete the "id" abastecimento.
     *
     * @param id the id of the abastecimentoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/abastecimentos/{id}")
    public ResponseEntity<Void> deleteAbastecimento(@PathVariable Long id) {
        log.debug("REST request to delete Abastecimento : {}", id);
        abastecimentoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
