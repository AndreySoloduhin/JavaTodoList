package org.service.serviceImpl;

import org.repository.Note;
import org.repository.NoteRepository;
import org.service.ListService;

import java.util.List;

public class ListServiceImpl implements ListService {
	NoteRepository noteRepository;

	public ListServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}
}
