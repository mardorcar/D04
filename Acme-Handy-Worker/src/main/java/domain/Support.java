
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Access(AccessType.PROPERTY)
@Entity
public class Support extends DomainEntity {

	private String		banner;
	private String		targetpage;
	private CreditCard	creditcard;
	private Tutorial	tutorial;
	private Sponsor		sponsor;


	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@NotBlank
	@URL
	public String getTargetpage() {
		return this.targetpage;
	}

	public void setTargetpage(final String targetpage) {
		this.targetpage = targetpage;
	}

	@Valid
	public CreditCard getCriditcard() {
		return this.creditcard;
	}

	public void setCriditcard(final CreditCard creditcard) {
		this.creditcard = creditcard;
	}

	@Valid
	@ManyToOne(optional = false)
	public Tutorial getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	@Valid
	@ManyToOne(optional = false)
	public Sponsor getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

}
