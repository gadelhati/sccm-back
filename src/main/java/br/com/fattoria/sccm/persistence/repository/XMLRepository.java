package br.com.fattoria.sccm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import br.com.fattoria.sccm.persistence.model.XML;

@Repository
@RepositoryRestResource(collectionResourceRel = "xml", path="xml")
public interface XMLRepository extends CrudRepository<XML, Long> {
	
	/*
	${FILE_IDENTIFIER}
	${DATA_GERACAO_XML} - 2019-05-27T11:12:21
	${TITULO} - #102019PC1802 LH-006/2019
	${DATA_RECEBIMENTO} - 2019-02-22
	${RESUMO}
	${NAVIO}
	${COORDENADOR_PROJETO}
	${INSTITUICAO}
	${CAMINHO_PC} - http://idem.chm.mb/geonetwork/srv/api/records/c07faf49-dd6d-4ffd-9e24-462552f20fe8/attachments/PC1802.png
	${FOR_PALAVRA_CHAVE}
	${PALAVRA_CHAVE}
	${FIM_FOR_PALAVRA_CHAVE}
	${LIMITE_OESTE}
	${LIMITE_LESTE}
	${LIMITE_SUL}
	${LIMITE_NORTE}
	${DATA_INICIO}
	${DATA_FINAL}*/
	
}
