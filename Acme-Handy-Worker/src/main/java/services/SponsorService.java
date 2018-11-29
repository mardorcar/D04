package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Sponsor;
import repositories.SponsorRepository;
import security.LoginService;
import security.UserAccount;
import utilities.CommonUtilities;

@Service
@Transactional
public class SponsorService {
	private SponsorRepository sponsorRepository;
	// Simple CRUD METHODS..............
	public Sponsor findOne(int id){
    	  Sponsor sponsor;
    	  sponsor=sponsorRepository.findOne(id);
    	  return sponsor;
       }
	public Sponsor saveSponsor(Sponsor sponsor){
		
		sponsorRepository.save(sponsor);
		return sponsor;
	}
	public Collection<Sponsor>findAll(){
		Collection<Sponsor> sponsors;
		sponsors=this.sponsorRepository.findAll();
		return sponsors;
	}
	public void delete(Sponsor sponsor) {
		Assert.notNull(sponsor);
		boolean isadmin ;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		isadmin= CommonUtilities.isAdmin(userAccount);
		Assert.isTrue(isadmin,
				"No puedes eliminar un sponsor porque no eres Admin");}
		
	
	// Managed repository..............................................
	@Autowired
	private SponsorRepository sposorRepository;
	// Supporting services.............................................
	
}
