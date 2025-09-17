package org.service.serviceImpl;

import lombok.NonNull;
import org.repository.Note;
import org.repository.NoteRepository;
import org.service.AddService;

public class AddServiceImpl implements AddService {

	private final NoteRepository noteRepository;

	public AddServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public void addNote(@NonNull Note note) {
		noteRepository.save(note);
	}
}
