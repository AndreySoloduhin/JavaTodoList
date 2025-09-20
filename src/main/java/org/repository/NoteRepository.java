package org.repository;

import org.enums.Status;
import org.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoteRepository {
	private final List<Note> notes = new ArrayList<>();
	private long idCounter = 0;

	public Note save(Note note) {
		note.setId(++idCounter);
		notes.add(note);
		return note;
	}

	public List<Note> findAll() {
		return notes;
	}

	public Optional<Note> findById(long id) {
		return notes.stream()
				.filter(note -> note.getId().equals(id))
				.findFirst();
	}

	public List<Note> findByStatus(Status status) {
		return notes.stream()
				.filter(note -> note.getStatus() == status)
				.toList();
	}

	public boolean deleteById(long id) {
		return notes.removeIf(Note -> Note.getId().equals(id));
	}
}
