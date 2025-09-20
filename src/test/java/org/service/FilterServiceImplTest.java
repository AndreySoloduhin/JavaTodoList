package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.NoteRepository;
import org.enums.Status;
import org.service.serviceImpl.NoteServiceImpl;

import static org.enums.Status.TODO;

@ExtendWith(MockitoExtension.class)
public class FilterServiceImplTest {

	@InjectMocks
	private NoteServiceImpl noteService;

	@Mock
	NoteRepository noteRepository;

	@Test
	void testFilterNotesByStatus(){
		Status status = TODO;

		noteService.filterNotes(status);

		Mockito.verify(noteRepository, Mockito.times(1)).findByStatus(status);
	}
}
