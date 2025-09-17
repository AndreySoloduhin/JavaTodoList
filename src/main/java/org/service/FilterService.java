package org.service;

import org.repository.Note;
import org.repository.Status;

import java.util.List;

public interface FilterService {
	List<Note> filterNotes(Status status);
}
