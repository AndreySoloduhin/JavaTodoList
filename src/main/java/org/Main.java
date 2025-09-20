package org;

import org.controller.NoteController;
import org.repository.NoteRepository;
import org.service.serviceImpl.*;

public class Main {
	public static void main(String[] args) {
		NoteRepository noteRepository = new NoteRepository();

		NoteServiceImpl noteService = new NoteServiceImpl(noteRepository);

		NoteController controller = new NoteController(
				noteRepository,
				noteService
		);

		controller.start();
	}
}