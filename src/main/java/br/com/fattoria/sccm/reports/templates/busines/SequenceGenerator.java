package br.com.fattoria.sccm.reports.templates.busines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.fattoria.sccm.persistence.model.Sequence;
import br.com.fattoria.sccm.persistence.repository.SequenceRepository;

@Component
public class SequenceGenerator {

	private final SequenceRepository sequenceRepository;

	private final Logger log = LoggerFactory.getLogger(SequenceGenerator.class);
	
	public SequenceGenerator(SequenceRepository sequenceRepository) {
		super();
		this.sequenceRepository = sequenceRepository;
	}
	
	public String getSequence(SequenceModel sequenceModel) {

		Sequence sequenceEntity = sequenceModel.getSequenceEntity();

		if (sequenceRepository.existsById(sequenceEntity.getChave())) {
			sequenceEntity = sequenceRepository.findById(sequenceEntity.getChave()).get();
			sequenceEntity.addSequence();
			sequenceEntity = sequenceRepository.save(sequenceEntity);
		} else {
			sequenceEntity.addSequence();
			sequenceEntity = sequenceRepository.save(sequenceEntity);
		}

		String seqNum = sequenceEntity.getSequence();
		
		log.info("sequence gerado "+seqNum);

		return seqNum;

	}


}
