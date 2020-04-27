package com.mylabs.fuelmock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AbastecimentoMapperTest {

    private AbastecimentoMapper abastecimentoMapper;

    @BeforeEach
    public void setUp() {
        abastecimentoMapper = new AbastecimentoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(abastecimentoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(abastecimentoMapper.fromId(null)).isNull();
    }
}
