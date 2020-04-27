package com.mylabs.fuelmock.web.rest;

import com.mylabs.fuelmock.service.VeiculoService;
import com.mylabs.fuelmock.web.rest.errors.BadRequestAlertException;
import com.mylabs.fuelmock.service.dto.VeiculoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mylabs.fuelmock.domain.Veiculo}.
 */
@RestController
@RequestMapping("/api")
public class VeiculoResource {

    private final Logger log = LoggerFactory.getLogger(VeiculoResource.class);

    private static final String ENTITY_NAME = "fuelmockVeiculo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VeiculoService veiculoService;

    public VeiculoResource(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    /**
     * {@code POST  /veiculos} : Create a new veiculo.
     *
     * @param veiculoDTO the veiculoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new veiculoDTO, or with status {@code 400 (Bad Request)} if the veiculo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/veiculos")
    public ResponseEntity<VeiculoDTO> createVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO) throws URISyntaxException {
        log.debug("REST request to save Veiculo : {}", veiculoDTO);
        if (veiculoDTO.getId() != null) {
            throw new BadRequestAlertException("A new veiculo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VeiculoDTO result = veiculoService.save(veiculoDTO);
        return ResponseEntity.created(new URI("/api/veiculos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /veiculos} : Updates an existing veiculo.
     *
     * @param veiculoDTO the veiculoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated veiculoDTO,
     * or with status {@code 400 (Bad Request)} if the veiculoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the veiculoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/veiculos")
    public ResponseEntity<VeiculoDTO> updateVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO) throws URISyntaxException {
        log.debug("REST request to update Veiculo : {}", veiculoDTO);
        if (veiculoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VeiculoDTO result = veiculoService.save(veiculoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, veiculoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /veiculos} : get all the veiculos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of veiculos in body.
     */
    @GetMapping("/veiculos")
    public List<VeiculoDTO> getAllVeiculos() {
        log.debug("REST request to get all Veiculos");
        return veiculoService.findAll();
    }

    /**
     * {@code GET  /veiculos/:id} : get the "id" veiculo.
     *
     * @param id the id of the veiculoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the veiculoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/veiculos/{id}")
    public ResponseEntity<VeiculoDTO> getVeiculo(@PathVariable Long id) {
        log.debug("REST request to get Veiculo : {}", id);
        Optional<VeiculoDTO> veiculoDTO = veiculoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(veiculoDTO);
    }

    /**
     * {@code DELETE  /veiculos/:id} : delete the "id" veiculo.
     *
     * @param id the id of the veiculoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        log.debug("REST request to delete Veiculo : {}", id);
        veiculoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
