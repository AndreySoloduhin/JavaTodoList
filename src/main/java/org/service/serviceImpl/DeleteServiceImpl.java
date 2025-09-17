package org.service.serviceImpl;

import org.repository.NoteRepository;
import org.service.DeleteService;

public class DeleteServiceImpl implements DeleteService {

	private final NoteRepository noteRepository;

	public DeleteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public void deleteNote(Long noteId) {
		noteRepository.deleteById(noteId);
	}
}
