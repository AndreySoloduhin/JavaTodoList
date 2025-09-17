package org.service;

import org.repository.Note;

import java.util.List;

public interface SortService {

	List<Note> sortNotes(String field, boolean ascending);
}
