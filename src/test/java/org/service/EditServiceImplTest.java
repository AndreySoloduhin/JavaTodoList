package org.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.repository.Note;
import org.repository.NoteRepository;
import org.service.serviceImpl.EditServiceImpl;

import java.time.LocalDate;

import static org.repository.Status.*;

@ExtendWith(MockitoExtension.class)
public class EditServiceImplTest {

	@InjectMocks
	private EditServiceImpl editService;

	@Mock
	private NoteRepository noteRepository;

	private static final int EDIT_NOTE_NAME = 1;
	private static final int EDIT_NOTE_CONTENT = 2;
	private static final int EDIT_NOTE_DATE = 3;
	private static final int EDIT_NOTE_STATUS = 4;

	@Test
	void testEditContentByName() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_NAME = "editName";

		editService.editNote(note, EDIT_NOTE_NAME, NEW_TEST_NAME);

		Assertions.assertEquals(NEW_TEST_NAME, note.getName());
	}

	@Test
	void testEditContentByContent() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_CONTENT = "editContent";

		editService.editNote(note, EDIT_NOTE_CONTENT, NEW_TEST_CONTENT);

		Assertions.assertEquals(NEW_TEST_CONTENT, note.getContent());
	}

	@Test
	void testEditContentByDate() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_DATE = "2013-10-10";

		editService.editNote(note, EDIT_NOTE_DATE, NEW_TEST_DATE);

		Assertions.assertEquals(LocalDate.parse(NEW_TEST_DATE), note.getDate());
	}

	@Test
	void testEditContentByStatus() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_STATUS = "done";

		editService.editNote(note, EDIT_NOTE_STATUS, NEW_TEST_STATUS);

		Assertions.assertEquals(DONE, note.getStatus());
	}

	@Test
	void testEditContentByIncorrectDate() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_INCORRECT_DATE = "06-09-2005";

		Assertions.assertThrows(IllegalArgumentException.class,
				() -> {editService.editNote(note, EDIT_NOTE_DATE, NEW_TEST_INCORRECT_DATE);});
	}

	@Test
	void testEditContentByIncorrectStatus() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String NEW_TEST_INCORRECT_STATUS = "ready";

		Assertions.assertThrows(IllegalArgumentException.class,
				() -> {editService.editNote(note, EDIT_NOTE_DATE, NEW_TEST_INCORRECT_STATUS);});
	}

	@Test
	void testEditContentByIncorrectItem() {
		Note note = new Note(1L, "name1", "text1", LocalDate.of(2002, 06, 05), TODO);

		final String DATE = "06-09-2005";
		final int INCORRECT_ITEM_NOTE = 5;

		Assertions.assertThrows(IllegalArgumentException.class, () -> {editService.editNote(note, INCORRECT_ITEM_NOTE, DATE);});
	}
}
