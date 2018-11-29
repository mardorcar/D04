
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.WarrantyRepository;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	warrantyRepository;


	public Warranty findOne(final int warrantyId) {
		final Warranty warranty;
		warranty = this.warrantyRepository.findOne(warrantyId);
		return warranty;
	}

}
