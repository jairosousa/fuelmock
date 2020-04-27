package com.mylabs.fuelmock.web.rest;

import com.mylabs.fuelmock.FuelmockApp;
import com.mylabs.fuelmock.domain.Abastecimento;
import com.mylabs.fuelmock.repository.AbastecimentoRepository;
import com.mylabs.fuelmock.service.AbastecimentoService;
import com.mylabs.fuelmock.service.dto.AbastecimentoDTO;
import com.mylabs.fuelmock.service.mapper.AbastecimentoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AbastecimentoResource} REST controller.
 */
@SpringBootTest(classes = FuelmockApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class AbastecimentoResourceIT {

    private static final String DEFAULT_POSTO = "AAAAAAAAAA";
    private static final String UPDATED_POSTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PRECO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRECO_COMBUSTIVEL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECO_COMBUSTIVEL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KM_ATUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_KM_ATUAL = new BigDecimal(2);

    @Autowired
    private AbastecimentoRepository abastecimentoRepository;

    @Autowired
    private AbastecimentoMapper abastecimentoMapper;

    @Autowired
    private AbastecimentoService abastecimentoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAbastecimentoMockMvc;

    private Abastecimento abastecimento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Abastecimento createEntity(EntityManager em) {
        Abastecimento abastecimento = new Abastecimento()
            .posto(DEFAULT_POSTO)
            .data(DEFAULT_DATA)
            .preco(DEFAULT_PRECO)
            .precoCombustivel(DEFAULT_PRECO_COMBUSTIVEL)
            .kmAtual(DEFAULT_KM_ATUAL);
        return abastecimento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Abastecimento createUpdatedEntity(EntityManager em) {
        Abastecimento abastecimento = new Abastecimento()
            .posto(UPDATED_POSTO)
            .data(UPDATED_DATA)
            .preco(UPDATED_PRECO)
            .precoCombustivel(UPDATED_PRECO_COMBUSTIVEL)
            .kmAtual(UPDATED_KM_ATUAL);
        return abastecimento;
    }

    @BeforeEach
    public void initTest() {
        abastecimento = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbastecimento() throws Exception {
        int databaseSizeBeforeCreate = abastecimentoRepository.findAll().size();

        // Create the Abastecimento
        AbastecimentoDTO abastecimentoDTO = abastecimentoMapper.toDto(abastecimento);
        restAbastecimentoMockMvc.perform(post("/api/abastecimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abastecimentoDTO)))
            .andExpect(status().isCreated());

        // Validate the Abastecimento in the database
        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeCreate + 1);
        Abastecimento testAbastecimento = abastecimentoList.get(abastecimentoList.size() - 1);
        assertThat(testAbastecimento.getPosto()).isEqualTo(DEFAULT_POSTO);
        assertThat(testAbastecimento.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testAbastecimento.getPreco()).isEqualTo(DEFAULT_PRECO);
        assertThat(testAbastecimento.getPrecoCombustivel()).isEqualTo(DEFAULT_PRECO_COMBUSTIVEL);
        assertThat(testAbastecimento.getKmAtual()).isEqualTo(DEFAULT_KM_ATUAL);
    }

    @Test
    @Transactional
    public void createAbastecimentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abastecimentoRepository.findAll().size();

        // Create the Abastecimento with an existing ID
        abastecimento.setId(1L);
        AbastecimentoDTO abastecimentoDTO = abastecimentoMapper.toDto(abastecimento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbastecimentoMockMvc.perform(post("/api/abastecimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abastecimentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Abastecimento in the database
        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPostoIsRequired() throws Exception {
        int databaseSizeBeforeTest = abastecimentoRepository.findAll().size();
        // set the field null
        abastecimento.setPosto(null);

        // Create the Abastecimento, which fails.
        AbastecimentoDTO abastecimentoDTO = abastecimentoMapper.toDto(abastecimento);

        restAbastecimentoMockMvc.perform(post("/api/abastecimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abastecimentoDTO)))
            .andExpect(status().isBadRequest());

        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAbastecimentos() throws Exception {
        // Initialize the database
        abastecimentoRepository.saveAndFlush(abastecimento);

        // Get all the abastecimentoList
        restAbastecimentoMockMvc.perform(get("/api/abastecimentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abastecimento.getId().intValue())))
            .andExpect(jsonPath("$.[*].posto").value(hasItem(DEFAULT_POSTO)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].preco").value(hasItem(DEFAULT_PRECO.intValue())))
            .andExpect(jsonPath("$.[*].precoCombustivel").value(hasItem(DEFAULT_PRECO_COMBUSTIVEL.intValue())))
            .andExpect(jsonPath("$.[*].kmAtual").value(hasItem(DEFAULT_KM_ATUAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getAbastecimento() throws Exception {
        // Initialize the database
        abastecimentoRepository.saveAndFlush(abastecimento);

        // Get the abastecimento
        restAbastecimentoMockMvc.perform(get("/api/abastecimentos/{id}", abastecimento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(abastecimento.getId().intValue()))
            .andExpect(jsonPath("$.posto").value(DEFAULT_POSTO))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.preco").value(DEFAULT_PRECO.intValue()))
            .andExpect(jsonPath("$.precoCombustivel").value(DEFAULT_PRECO_COMBUSTIVEL.intValue()))
            .andExpect(jsonPath("$.kmAtual").value(DEFAULT_KM_ATUAL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAbastecimento() throws Exception {
        // Get the abastecimento
        restAbastecimentoMockMvc.perform(get("/api/abastecimentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbastecimento() throws Exception {
        // Initialize the database
        abastecimentoRepository.saveAndFlush(abastecimento);

        int databaseSizeBeforeUpdate = abastecimentoRepository.findAll().size();

        // Update the abastecimento
        Abastecimento updatedAbastecimento = abastecimentoRepository.findById(abastecimento.getId()).get();
        // Disconnect from session so that the updates on updatedAbastecimento are not directly saved in db
        em.detach(updatedAbastecimento);
        updatedAbastecimento
            .posto(UPDATED_POSTO)
            .data(UPDATED_DATA)
            .preco(UPDATED_PRECO)
            .precoCombustivel(UPDATED_PRECO_COMBUSTIVEL)
            .kmAtual(UPDATED_KM_ATUAL);
        AbastecimentoDTO abastecimentoDTO = abastecimentoMapper.toDto(updatedAbastecimento);

        restAbastecimentoMockMvc.perform(put("/api/abastecimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abastecimentoDTO)))
            .andExpect(status().isOk());

        // Validate the Abastecimento in the database
        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeUpdate);
        Abastecimento testAbastecimento = abastecimentoList.get(abastecimentoList.size() - 1);
        assertThat(testAbastecimento.getPosto()).isEqualTo(UPDATED_POSTO);
        assertThat(testAbastecimento.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testAbastecimento.getPreco()).isEqualTo(UPDATED_PRECO);
        assertThat(testAbastecimento.getPrecoCombustivel()).isEqualTo(UPDATED_PRECO_COMBUSTIVEL);
        assertThat(testAbastecimento.getKmAtual()).isEqualTo(UPDATED_KM_ATUAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAbastecimento() throws Exception {
        int databaseSizeBeforeUpdate = abastecimentoRepository.findAll().size();

        // Create the Abastecimento
        AbastecimentoDTO abastecimentoDTO = abastecimentoMapper.toDto(abastecimento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbastecimentoMockMvc.perform(put("/api/abastecimentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(abastecimentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Abastecimento in the database
        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbastecimento() throws Exception {
        // Initialize the database
        abastecimentoRepository.saveAndFlush(abastecimento);

        int databaseSizeBeforeDelete = abastecimentoRepository.findAll().size();

        // Delete the abastecimento
        restAbastecimentoMockMvc.perform(delete("/api/abastecimentos/{id}", abastecimento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Abastecimento> abastecimentoList = abastecimentoRepository.findAll();
        assertThat(abastecimentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
