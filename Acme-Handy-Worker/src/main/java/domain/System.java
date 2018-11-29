
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class System extends DomainEntity {

	private String	name;
	private String	banner;
	private String	bannerheader;
	private String	welcomePageMsg;
	private String	phoneCode;
	private String	spamword;
	private String	vatPercentage;
	private String	CreditCards;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@NotBlank
	public String getBannerheader() {
		return this.bannerheader;
	}

	public void setBannerheader(final String bannerheader) {
		this.bannerheader = bannerheader;
	}
	@NotBlank
	public String getWelcomePageMsg() {
		return this.welcomePageMsg;
	}

	public void setWelcomePageMsg(final String welcomePageMsg) {
		this.welcomePageMsg = welcomePageMsg;
	}
	@NotBlank
	@Size(min = 1)
	//@Pattern(regexp = "+CC")
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(final String phoneCode) {
		this.phoneCode = phoneCode;
	}
	@NotBlank
	@Size(min = 1)
	public String getSpamword() {
		return this.spamword;
	}

	public void setSpamword(final String spamword) {
		this.spamword = spamword;
	}
	@NotBlank
	@Size(min = 1)
	public String getVatPercentage() {
		return this.vatPercentage;
	}

	public void setVatPercentage(final String vatPercentage) {
		this.vatPercentage = vatPercentage;
	}
	@NotBlank
	@Size(min = 1)
	public String getCreditCards() {
		return this.CreditCards;
	}

	public void setCreditCards(final String creditCards) {
		this.CreditCards = creditCards;
	}

}
