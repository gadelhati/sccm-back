package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.domain.PeriodoData;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

public interface RelatorioRepository {
	
	Long countByDataCadastroBetween(PeriodoData periodoData);

	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupByTipoComissao(PeriodoData periodoData);

	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao(PeriodoData periodoData);

	Collection<QuantitativoDTO> countByDataCadastroBetweenGroupByEquipamentos(PeriodoData periodoData);

	Collection<PesquisaCientifica> findByDataCadastroIdSituacaoBetween(PeriodoData periodoData, long idSituacao);

}
