package org.service;

import lombok.NonNull;
import org.repository.Note;

public interface EditService {

	void editNote(@NonNull Note note, int field, String newValue);
}
