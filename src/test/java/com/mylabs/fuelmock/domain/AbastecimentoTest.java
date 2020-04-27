package com.mylabs.fuelmock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mylabs.fuelmock.web.rest.TestUtil;

public class AbastecimentoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Abastecimento.class);
        Abastecimento abastecimento1 = new Abastecimento();
        abastecimento1.setId(1L);
        Abastecimento abastecimento2 = new Abastecimento();
        abastecimento2.setId(abastecimento1.getId());
        assertThat(abastecimento1).isEqualTo(abastecimento2);
        abastecimento2.setId(2L);
        assertThat(abastecimento1).isNotEqualTo(abastecimento2);
        abastecimento1.setId(null);
        assertThat(abastecimento1).isNotEqualTo(abastecimento2);
    }
}
