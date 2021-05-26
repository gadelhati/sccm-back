package br.com.fattoria.sccm.persistence.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;
import br.com.fattoria.sccm.reports.data.FichaPesquisaCientificaDTO;

@Repository
@RepositoryRestResource(collectionResourceRel = "pesquisas_cientificas", path="pesquisas_cientificas", exported = false)
public interface PesquisaCientificaRepository extends CrudRepository<PesquisaCientifica, Long> {
	
	Collection<PesquisaCientifica> findAll(Sort sort);
	
	Collection<PesquisaCientifica> findByNumeroPCContainingIgnoreCase(String numeroPC);
	
	Page<PesquisaCientifica> findAll(Pageable page);
	
	@Query("select " +
			" new br.com.fattoria.sccm.reports.data.FichaPesquisaCientificaDTO( " + 
			"    pc.id, " + 
			"    c.nomeComissao, " + 
			"    tc.descricao, " + 
			"    i.nome, " + 
			"    eI.logradouro, " + 
			"    eI.cidade, " + 
			"    eI.estado, " + 
			"    eI.cep, " + 
			"    i.email, " +			 
			"    c.resumo, " + 
			"    c.palavrasChaves, " + 
			"    pc.coordenadorCientifico, " + 
			"    pc.cartaNautica, " + 
			"    TO_CHAR(c.dataInicio, 'DD/MM/YYYY') AS data_inicio, " + 
			"    TO_CHAR(c.dataFim, 'DD/MM/YYYY')    AS data_fim, " + 
			"    pc.limiteNorteLatitude, " + 
			"    pc.limiteSulLatitude, " + 
			"    pc.limiteLesteLongitude, " + 
			"    pc.limiteOesteLongitude, " + 
			"    p.nome       AS nome_plataforma, " + 
			"    tp.descricao AS tipo_plataforma, " + 
			"    pc.comandante, " + 
			"    p.indicativoVisual, " + 
			"    bP.nome AS bandeira, " +
			"    bI.nome AS paisinstituicao, " + 
			"    ''      AS telefoneinstituicao, " + 
			"    ''      AS outrasinstituicoes, " + 
			"    ci.recibo, " + 
			"    ci.numeroOficio || TO_CHAR(ci.dataOficio, 'DD/MM/YYYY') AS oficio, " + 
			"    pc.numeroPC  AS fitoteca, " + 
			"    ci.arquivoTecnico, " + 
			"    ci.formaEnvio, " + 
			"    ci.numeroAutorizacao " +
			") " + 
			"FROM " + 
			"    PesquisaCientifica pc " + 
			"LEFT JOIN Comissao c " + 
			"ON " + 
			"    pc.comissao.id = c.id " + 
			"LEFT JOIN TipoComissao tc " + 
			"ON " + 
			"    tc.id = c.tipoComissao.id " + 
			"LEFT JOIN Instituicao i " + 
			"ON " + 
			"    i.id = pc.instituicao.id " + 
			"LEFT JOIN Endereco eI " + 
			"ON " + 
			"    eI.id = i.endereco.id " + 
			"LEFT JOIN Plataforma p " + 
			"ON " + 
			"    p.id = pc.plataforma.id " + 
			"LEFT JOIN TipoPlataforma tp " + 
			"ON " + 
			"    tp.id = p.tipoPlataforma.id " + 
			"LEFT JOIN Pais bP " + 
			"ON " + 
			"    bP.id = p.bandeira.id " + 
			"LEFT JOIN Pais bI " + 
			"ON " + 
			"    bI.id = i.pais.id " + 
			"LEFT JOIN ControleInterno ci " + 
			"ON " + 
			"    pc.id = ci.pesquisaCientifica.id " +
			"AND  ci.dataRecebimento = (SELECT MAX(c.dataRecebimento) FROM ControleInterno c WHERE c.pesquisaCientifica.id = pc.id)" + 
			"LEFT JOIN Instituicao iCI " + 
			"ON " + 
			"    iCI.id = ci.instituicao.id " + 
			"WHERE " + 
			"    pc.id = :id")
	FichaPesquisaCientificaDTO getIdFichaPesquisaCientificaView(@Param("id") Long id);

	@Query("select pc from PesquisaCientifica pc where "
			+ "(pc.comissao.nomeComissao like :search or pc.instituicao.nome like :search "
			+ "or pc.instituicao.sigla like :search or pc.plataforma.nome like :search "
			+ "or pc.comandante like :search or pc.coordenadorCientifico like :search or pc.numeroPC like :search or pc.situacao like :search)")
	Page<PesquisaCientifica> findAllBySearch(Pageable page, @Param("search") String search);

}
