
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class HandyWorker extends Actor {

	private String					make;
	private Finder					finder;
	private Collection<Application>	application;
	private Collection<Note>		notes;
	private Collection<Endorse>		endorseReceiver;
	private Collection<Endorse>		endorseSender;


	@OneToMany(mappedBy = "handyWorkerReceiver")
	public Collection<Endorse> getEndorseReceiver() {
		return this.endorseReceiver;
	}

	public void setEndorseReceiver(final Collection<Endorse> endorseReceiver) {
		this.endorseReceiver = endorseReceiver;
	}

	@OneToMany(mappedBy = "handyWorkerSender")
	public Collection<Endorse> getEndorseSender() {
		return this.endorseSender;
	}

	public void setEndorseSender(final Collection<Endorse> endorseSender) {
		this.endorseSender = endorseSender;
	}

	@NotBlank
	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}
	@Valid
	@ManyToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}
	@OneToMany
	public Collection<Application> getApplication() {
		return this.application;
	}

	public void setApplication(final Collection<Application> application) {
		this.application = application;
	}

	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

}
