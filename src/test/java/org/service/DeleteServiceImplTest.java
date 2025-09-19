package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.Note;
import org.repository.NoteRepository;
import org.service.serviceImpl.DeleteServiceImpl;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.repository.Status.TODO;

@ExtendWith(MockitoExtension.class)
public class DeleteServiceImplTest {

	@InjectMocks
	private DeleteServiceImpl deleteService;

	@Mock
	private NoteRepository noteRepository;

	@Test
	void testDeleteNote() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);


		deleteService.deleteNote(note.getId());

		verify(noteRepository, times(1)).deleteById(note.getId());
	}
}
