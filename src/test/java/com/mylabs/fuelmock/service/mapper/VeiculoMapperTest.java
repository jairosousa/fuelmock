package com.mylabs.fuelmock.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VeiculoMapperTest {

    private VeiculoMapper veiculoMapper;

    @BeforeEach
    public void setUp() {
        veiculoMapper = new VeiculoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(veiculoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(veiculoMapper.fromId(null)).isNull();
    }
}
