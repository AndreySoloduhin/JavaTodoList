package org.service.serviceImpl;

import lombok.NonNull;
import org.repository.Note;
import org.repository.NoteRepository;
import org.repository.Status;
import org.service.EditService;

import java.time.LocalDate;

public class EditServiceImpl implements EditService {

	private final NoteRepository noteRepository;

	public EditServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public void editNote(Note note, int field, String newValue) {
		switch (field) {
			case 1 -> note.setName(newValue);
			case 2 -> note.setContent(newValue);
			case 3 -> {
				try {
					LocalDate date = LocalDate.parse(newValue);
					note.setDate(date);
				} catch (Exception e) {
					throw new IllegalArgumentException("Некорректный формат даты, нужно yyyy-MM-dd");
				}
			}
			case 4 -> {
				try {
					Status status = Status.valueOf(newValue.toUpperCase());
					note.setStatus(status);
				} catch (IllegalArgumentException e) {
					throw new IllegalArgumentException("Некорректный статус! Используйте: TODO, IN_PROGRESS, DONE");
				}
			}
			default -> throw new IllegalArgumentException("Неверный пункт для редактирования: " + field);
		}
	}
}
