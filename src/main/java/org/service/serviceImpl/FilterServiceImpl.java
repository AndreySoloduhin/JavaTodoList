package org.service.serviceImpl;

import org.repository.Note;
import org.repository.NoteRepository;
import org.repository.Status;
import org.service.FilterService;

import java.util.List;

public class FilterServiceImpl implements FilterService {

	private final NoteRepository noteRepository;

	public FilterServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public List<Note> filterNotes(Status status) {
		return noteRepository.findByStatus(status);
	}
}
