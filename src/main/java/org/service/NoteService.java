package org.service;

import lombok.NonNull;
import org.models.Note;
import org.enums.Status;

import java.util.List;

public interface NoteService {

	void addNote(@NonNull Note note);

	void deleteNote(Long id);

	void editNote(@NonNull Note note, int field, String newValue);

	List<Note> getAllNotes();

	List<Note> filterNotes(Status status);

	List<Note> sortNotes(String field, boolean ascending);
}
