
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private String				ticket;
	private MiscellaneousRecord	miscellaneousRecord;
	private PersonalRecord		personalRecord;
	private EducationRecord		educationRecord;
	private ProfessionalRecord	professionalRecord;
	private EndorseRecord		endoserRecord;
	private HandyWorker			handyWorker;


	@NotBlank
	//@Pattern(regexp = "yymmdd-xxxxxx")
	@Column(unique = true)
	@NotEmpty
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(final String ticket) {
		this.ticket = ticket;
	}

	@OneToOne(optional = false)
	@Valid
	public MiscellaneousRecord getMiscellaneousRecord() {
		return this.miscellaneousRecord;
	}

	public void setMiscellaneousRecord(final MiscellaneousRecord miscellaneousRecord) {
		this.miscellaneousRecord = miscellaneousRecord;
	}

	@OneToOne(optional = false)
	@Valid
	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}

	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

	@OneToOne(optional = false)
	@Valid
	public EducationRecord getEducationRecord() {
		return this.educationRecord;
	}

	public void setEducationRecord(final EducationRecord educationRecord) {
		this.educationRecord = educationRecord;
	}

	@OneToOne(optional = false)
	@Valid
	public ProfessionalRecord getProfessionalRecord() {
		return this.professionalRecord;
	}

	public void setProfessionalRecord(final ProfessionalRecord professionalRecord) {
		this.professionalRecord = professionalRecord;
	}

	@OneToOne(optional = false)
	@Valid
	public EndorseRecord getEndoserRecord() {
		return this.endoserRecord;
	}

	public void setEndoserRecord(final EndorseRecord endoserRecord) {
		this.endoserRecord = endoserRecord;
	}

	@OneToOne(optional = false)
	@Valid
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}
}
