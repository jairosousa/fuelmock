package com.mylabs.fuelmock.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mylabs.fuelmock.web.rest.TestUtil;

public class VeiculoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VeiculoDTO.class);
        VeiculoDTO veiculoDTO1 = new VeiculoDTO();
        veiculoDTO1.setId(1L);
        VeiculoDTO veiculoDTO2 = new VeiculoDTO();
        assertThat(veiculoDTO1).isNotEqualTo(veiculoDTO2);
        veiculoDTO2.setId(veiculoDTO1.getId());
        assertThat(veiculoDTO1).isEqualTo(veiculoDTO2);
        veiculoDTO2.setId(2L);
        assertThat(veiculoDTO1).isNotEqualTo(veiculoDTO2);
        veiculoDTO1.setId(null);
        assertThat(veiculoDTO1).isNotEqualTo(veiculoDTO2);
    }
}
