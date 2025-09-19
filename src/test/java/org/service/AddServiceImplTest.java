package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.Note;
import org.repository.NoteRepository;
import org.service.serviceImpl.AddServiceImpl;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.repository.Status.TODO;

@ExtendWith(MockitoExtension.class)
public class AddServiceImplTest {

	@InjectMocks
	AddServiceImpl addService;

	@Mock
	NoteRepository noteRepository;

	@Test
	void testAddNote() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		addService.addNote(note);
		verify(noteRepository, times(1)).save(note);
	}
}
