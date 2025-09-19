package org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.NoteRepository;
import org.service.serviceImpl.ListServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ListServiceImplTest {

	@InjectMocks
	private ListServiceImpl listService;

	@Mock
	NoteRepository noteRepository;

	@Test
	void testGetAllNotes() {

		listService.getAllNotes();

		Mockito.verify(noteRepository, Mockito.times(1)).findAll();
	}
}
