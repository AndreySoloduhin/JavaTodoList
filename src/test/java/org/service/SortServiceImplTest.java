package org.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.models.Note;
import org.repository.NoteRepository;
import org.enums.Status;
import org.service.serviceImpl.NoteServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SortServiceImplTest {

	@InjectMocks
	private NoteServiceImpl noteService;

	@Mock
	private NoteRepository noteRepository;

	@BeforeEach
	void setUp() {
		List<Note> notes = List.of(
				new Note(2L, "B-task", "c", LocalDate.of(2002,6,5), Status.TODO),
				new Note(1L, "A-task", "c", LocalDate.now(), Status.DONE)
		);
		when(noteRepository.findAll()).thenReturn(notes);
	}

	private static final String SORT_NOTE_ID = "id";
	private static final String SORT_NOTE_NAME = "name";
	private static final String SORT_NOTE_DATE = "date";
	private static final boolean SORT_NOTE_FOR_ASC = true;
	private static final boolean SORT_NOTE_FOR_DESC = false;


	@Test
	void testSortByIdAscending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_ID, SORT_NOTE_FOR_ASC);

		assertEquals(List.of(1L, 2L),
				sorted.stream().map(Note::getId).toList());
	}

	@Test
	void testSortByIdDescending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_ID, SORT_NOTE_FOR_DESC);

		assertEquals(List.of(2L, 1L),
				sorted.stream().map(Note::getId).toList());
	}

	@Test
	void testSortByNameAscending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_NAME, SORT_NOTE_FOR_ASC);

		assertEquals(List.of("A-task", "B-task"),
				sorted.stream().map(Note::getName).toList());
	}

	@Test
	void testSortByNameDescending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_NAME, SORT_NOTE_FOR_DESC);

		assertEquals(List.of("B-task", "A-task"),
				sorted.stream().map(Note::getName).toList());
	}

	@Test
	void testSortByDateAscending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_DATE, SORT_NOTE_FOR_ASC);

		assertEquals(List.of(LocalDate.of(2002,6,5), LocalDate.now()),
				sorted.stream().map(Note::getDate).toList());
	}

	@Test
	void testSortByDateDescending() {
		List<Note> sorted = noteService.sortNotes(SORT_NOTE_DATE, SORT_NOTE_FOR_DESC);

		assertEquals(List.of(LocalDate.now(), LocalDate.of(2002,6,5)),
				sorted.stream().map(Note::getDate).toList());
	}
}
