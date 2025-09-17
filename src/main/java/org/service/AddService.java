package org.service;

import lombok.NonNull;
import org.repository.Note;

public interface AddService {

	void addNote(@NonNull Note note);
}
