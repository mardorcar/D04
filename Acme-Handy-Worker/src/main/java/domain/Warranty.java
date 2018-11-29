
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Warranty extends DomainEntity {

	private String	title;
	private String	terms;
	private String	laws;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getTerms() {
		return this.terms;
	}

	public void setTerms(final String terms) {
		this.terms = terms;
	}
	@NotBlank
	@Size(min = 1)
	public String getLaws() {
		return this.laws;
	}

	public void setLaws(final String laws) {
		this.laws = laws;
	}
}
