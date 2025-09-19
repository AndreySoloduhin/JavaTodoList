package org.service.serviceImpl;

import org.repository.Note;
import org.repository.NoteRepository;
import org.service.SortService;

import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {

	private final NoteRepository noteRepository;

	public SortServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public List<Note> sortNotes(String field, boolean ascending) {
		Comparator<Note> comparator;

		switch (field.toLowerCase()) {
			case "id" -> comparator = Comparator.comparing(Note::getId);
			case "name" -> comparator = Comparator.comparing(Note::getName);
			case "date" -> comparator = Comparator.comparing(Note::getDate);
			default -> throw new IllegalArgumentException("Неизвестное поле: " + field);
		}

		if (!ascending) comparator = comparator.reversed();

		return noteRepository.findAll().stream()
				.sorted(comparator)
				.toList();
	}
}
