package br.com.fattoria.sccm.persistence.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.api.Periodo;
import br.com.fattoria.sccm.dto.QuantitativoDTO;
import br.com.fattoria.sccm.persistence.domain.PeriodoData;
import br.com.fattoria.sccm.persistence.model.PesquisaCientifica;

@Repository
public class RelatorioRepositoryImpl implements RelatorioRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Long countByDataCadastroBetween(PeriodoData periodoData) {
		
		Query query = entityManager.createQuery("select count(pc.id) from PesquisaCientifica pc where pc.dataCadastro between :dataInicio and :dataFim");
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		Long count = (Long) query.getSingleResult();
		
		return count;
	}
	
	public Collection<QuantitativoDTO> countByDataCadastroBetweenGroupByTipoComissao(PeriodoData periodoData) {
		
		String sql = 	
			"SELECT " +
			    " COUNT(pc.fk_comissao) AS count_, tc.descricao AS desc_ " +
			"FROM tipo_comissao tc LEFT OUTER JOIN  " +
			"(select * from pesquisa_cientifica pc, comissao c where pc.fk_comissao = c.id  " +
			"and pc.data_cadastro BETWEEN :dataInicio AND :dataFim) as pc " +
			"ON pc.fk_tipo_comissao = tc.id GROUP BY tc.descricao";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}
	
	public Collection<QuantitativoDTO> countByDataCadastroBetweenGroupBySituacao(PeriodoData periodoData) {
		
		String sql = 	
			"SELECT " +
			    " COUNT(pc.fk_situacao) AS count_, s.descricao AS desc_ " +
			"FROM situacao s LEFT OUTER JOIN  " +
			"(select * from pesquisa_cientifica pc WHERE pc.data_cadastro BETWEEN :dataInicio AND :dataFim) as pc " +
			"ON pc.fk_situacao = s.id WHERE s.para_pesquisa_cientifica = true GROUP BY s.descricao";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}
	
	public Collection<QuantitativoDTO> countByDataCadastroBetweenGroupByEquipamentos(PeriodoData periodoData) {
		
		String sql = 	
			"SELECT " +
			    " COUNT(pc.fk_equipamento) AS count_, equip.sigla AS desc_ " +
			"FROM equipamento equip LEFT OUTER JOIN  " +
			"(select pc_equip.fk_equipamento from pesquisa_cientifica pc, pesquisa_cientifica_equipamento pc_equip "
			+ "WHERE pc_equip.fk_pesquisa_cientifica = pc.id and pc.data_cadastro BETWEEN :dataInicio AND :dataFim) as pc " +
			"ON pc.fk_equipamento = equip.id GROUP BY equip.sigla";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}
	
	public Collection<PesquisaCientifica> findByDataCadastroIdSituacaoBetween(PeriodoData periodoData, long idSituacao) {
		
		String sql = "select pc.* from pesquisa_cientifica pc WHERE pc.fk_situacao = :idSituacao and pc.data_cadastro BETWEEN :dataInicio AND :dataFim";
		
		Query query = entityManager.createNativeQuery(sql, PesquisaCientifica.class);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		query.setParameter("idSituacao", idSituacao);
		
		List<PesquisaCientifica> resultList = query.getResultList();
		
		return resultList;
	}

	public Collection<QuantitativoDTO> countMidiasParticularesByDataCadastroBetweenGroupBySituacao(Periodo periodoData) {
		
		String sql = 	
				"SELECT " +
				    " COUNT(qtd.fk_situacao) AS count_, s.descricao AS desc_ " +
				"FROM situacao s LEFT OUTER JOIN  " +
				"(select mp.* from midia_particular mp "
				+ "WHERE mp.data BETWEEN :dataInicio AND :dataFim) as qtd " +
				"ON qtd.fk_situacao = s.id where s.para_midias_particulares = true GROUP BY s.descricao";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}

	public Collection<QuantitativoDTO> countMidiasDiversasByDataCadastroBetweenGroupBySituacao(Periodo periodoData) {
		
		String sql = 	
			"SELECT " +
			    " COUNT(qtd.fk_situacao) AS count_, s.descricao AS desc_ " +
			"FROM situacao s LEFT OUTER JOIN  " +
			"(select md.* from midia_diversa md "
			+ "WHERE md.data BETWEEN :dataInicio AND :dataFim) as qtd " +
			"ON qtd.fk_situacao = s.id where s.para_midias_diversas = true GROUP BY s.descricao";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}
	
	public Collection<QuantitativoDTO> sumModelosObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(Periodo periodoData, String tipo) {
		
		String sql = 	
			"SELECT " +
			    " COALESCE(SUM(sum.numero_modelos),0) AS count_, s.descricao AS desc_ " +
			"FROM situacao s LEFT OUTER JOIN  " +
			"(SELECT ss.numero_modelos, ss.fk_situacoes as situacao FROM ship_synop ss where dados = :tipo "
			+ "and ss.data_entrada BETWEEN :dataInicio AND :dataFim) as sum " + 
			"ON sum.situacao = s.id where s.para_ship_synop = true GROUP BY s.descricao";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
		query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
		query.setParameter("tipo", tipo);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}
	
	public Collection<QuantitativoDTO> sumInformacaoObservacoesMeteorologicasByDataCadastroBetweenGroupBySituacao(Periodo periodoData, String tipo) {
		
		String sql = 	
				"SELECT " +
				    " COALESCE(SUM(sum.numero_informacoes),0) AS count_, s.descricao AS desc_ " +
				"FROM situacao s LEFT OUTER JOIN  " +
				"(SELECT ss.numero_informacoes, ss.fk_situacoes as situacao FROM ship_synop ss where dados = :tipo "
				+ "and ss.data_entrada BETWEEN :dataInicio AND :dataFim) as sum " + 
				"ON sum.situacao = s.id where s.para_ship_synop = true GROUP BY s.descricao";
			
			Query query = entityManager.createNativeQuery(sql);
			
			query.setParameter("dataInicio", periodoData.getDataInicio(), TemporalType.DATE);
			query.setParameter("dataFim", periodoData.getDataFim(), TemporalType.DATE);
			query.setParameter("tipo", tipo);
		
		List<Object[]> resultList = query.getResultList();
		
		Collection<QuantitativoDTO> collection = new ArrayList<QuantitativoDTO>();
		
		BigDecimal total = BigDecimal.ZERO;
		for (Object[] resultItem : resultList) {
			BigInteger quantidade = (BigInteger)resultItem[0];
			total = total.add(new BigDecimal(quantidade));
		}
		
		for (Object[] resultItem : resultList) {
			
			BigInteger quantidade = (BigInteger)resultItem[0];
			
			BigDecimal porcentagem = total.compareTo(BigDecimal.ZERO) > 0 ? 
					new BigDecimal(quantidade).multiply(new BigDecimal(100)).divide(total, 2, RoundingMode.FLOOR) : 
						BigDecimal.ZERO;
			
			collection.add(new QuantitativoDTO(quantidade.longValue(), (String)resultItem[1], porcentagem));
		}
		
		return collection;
	}

}
