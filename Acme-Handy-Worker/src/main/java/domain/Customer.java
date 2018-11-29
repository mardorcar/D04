
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	private Collection<Complaint>	complaints;
	private Collection<Note>		notes;
	private Collection<Endorse>		endorseReceiver;
	private Collection<Endorse>		endorseSender;
	private Collection<Task>		tasks;


	@OneToMany
	public Collection<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(final Collection<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(mappedBy = "customerReceiver")
	public Collection<Endorse> getEndorseReceiver() {
		return this.endorseReceiver;
	}

	public void setEndorseReceiver(final Collection<Endorse> endorseReceiver) {
		this.endorseReceiver = endorseReceiver;
	}

	@OneToMany(mappedBy = "customerSender")
	public Collection<Endorse> getEndorseSender() {
		return this.endorseSender;
	}

	public void setEndorseSender(final Collection<Endorse> endorseSender) {
		this.endorseSender = endorseSender;
	}

	@OneToMany
	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}
	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

}
