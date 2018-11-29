package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Section;

import repositories.SectionRepository;

@Service
@Transactional
public class SectionService {
	@Autowired
	private SectionRepository sectionRepository;
	public Collection<Section>findAll(){
		return sectionRepository.findAll();
	}
	
	
}
