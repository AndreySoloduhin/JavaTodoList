package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.NoteRepository;
import org.service.serviceImpl.NoteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ListServiceImplTest {

	@InjectMocks
	private NoteServiceImpl noteService;

	@Mock
	NoteRepository noteRepository;

	@Test
	void testGetAllNotes() {

		noteService.getAllNotes();

		Mockito.verify(noteRepository, Mockito.times(1)).findAll();
	}
}
