package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.SocialProfile;
@Service
@Transactional

public class SocialProfileService {
	
		//Managed repository..............................................
		@Autowired
		private SocialProfileRepository	socialProfileRepository;
		//Supporting services.............................................

		// Simple CRUD METHODS..............
		
		public SocialProfile findOne(int id){
	  	SocialProfile socialProfile;
	  	  socialProfile=socialProfileRepository.findOne(id);
	  	  return socialProfile;
	     }
		public SocialProfile SaveSocialProfile(SocialProfile socialProfile){
			
			socialProfileRepository.save(socialProfile);
			return socialProfile;
		}
	
		public void delete (SocialProfile socialProfile){
			Assert.notNull(socialProfile);
			socialProfileRepository.delete(socialProfile);
			}
		public Collection<SocialProfile>findAll(){
			Collection<SocialProfile> socialProfiles=socialProfileRepository.findAll();
			return socialProfiles;
		}
		
		//Other business methods.................
		public SocialProfile findByActor(int id){
			SocialProfile socialProfile;
			socialProfile=socialProfileRepository.findByActor(id);
			return socialProfile;
		}
	}


