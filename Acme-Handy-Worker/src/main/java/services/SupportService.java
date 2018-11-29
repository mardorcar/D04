
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Support;
import domain.Warranty;

import repositories.SupportRepository;
import security.LoginService;
import security.UserAccount;
import utilities.CommonUtilities;

@Service
@Transactional
public class SupportService {

	//Managed repository..............................................
	@Autowired
	private SupportRepository	supportRepository;
	//Supporting services.............................................
	
	public Collection<Support>findAll(){
		Collection<Support>supports;
		supports=this.supportRepository.findAll();
		return supports;
	}
	public Support findOne(int supportid){
		Support support;
		support=this.findOne(supportid);
		return support;
	}
	public void delete(Support support) {
		Assert.notNull(support);
		boolean isadmin ;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		isadmin= CommonUtilities.isAdmin(userAccount);
		Assert.isTrue(isadmin,
				"No puedes eliminar un Support porque no eres Admin");
		
	}
	public Support create(Support support) {

		UserAccount userAccount;
		boolean isAdmin ;

		userAccount = LoginService.getPrincipal();
		isAdmin = CommonUtilities.isAdmin(userAccount);

		
		this.supportRepository.save(support);

		return support;
	}
}
