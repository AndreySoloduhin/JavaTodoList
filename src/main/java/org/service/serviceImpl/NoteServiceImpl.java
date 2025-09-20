package org.service.serviceImpl;

import lombok.NonNull;
import org.models.Note;
import org.repository.NoteRepository;
import org.enums.Status;
import org.service.NoteService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class NoteServiceImpl implements NoteService {

	private final NoteRepository noteRepository;

	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	public void addNote(@NonNull Note note) {
		validateNote(note);
		noteRepository.save(note);
	}

	public void deleteNote(Long noteId) {
		if (noteRepository.findById(noteId).isEmpty()) {
			throw new IllegalArgumentException("нет задачи с " + noteId + " id");
		}
		noteRepository.deleteById(noteId);
	}

	public void editNote(Note note, int field, String newValue) {
		validateNote(note);
		if(newValue == null || newValue.isEmpty()) {
			throw new IllegalArgumentException("Данные для замены не могут быть пустые");
		}
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

	public List<Note> filterNotes(Status status) {
		if (status == null) {
			throw new IllegalArgumentException("Статус задачи не установлен");
		}
		return noteRepository.findByStatus(status);
	}

	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	public List<Note> sortNotes(String field, boolean ascending) {
		if(field == null || field.isEmpty()) {
			throw new IllegalArgumentException("Поле для сортировки не может быть пустым");
		}

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

	private void validateNote(Note note) {
		if (note.getName() == null || note.getName().isEmpty()) {
			throw new IllegalArgumentException("Имя задачи не может быть пустым");
		}
		if (note.getContent() == null || note.getContent().isEmpty()) {
			throw new IllegalArgumentException("Контент задачи не может быть пустым");
		}
		if (note.getDate() == null) {
			throw new IllegalArgumentException("Дата не может быть пустой");
		}
		if (note.getStatus() == null) {
			throw new IllegalArgumentException("Статус задачи не установлен");
		}
	}
}
