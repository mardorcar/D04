
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ComplaintRepository;
import domain.Complaint;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;


	public Collection<Complaint> findAll() {
		Collection<Complaint> complaints;
		complaints = this.complaintRepository.findAll();
		return complaints;
	}

}
