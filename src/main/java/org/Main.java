package org;

import org.controller.NoteController;
import org.repository.NoteRepository;
import org.service.*;
import org.service.serviceImpl.*;

public class Main {
	public static void main(String[] args) {
		// создаём репозиторий
		NoteRepository noteRepository = new NoteRepository();

		// создаём сервисы и передаём им репозиторий
		AddService addService = new AddServiceImpl(noteRepository);
		ListService listService = new ListServiceImpl(noteRepository);
		DeleteService deleteService = new DeleteServiceImpl(noteRepository);
		EditService editService = new EditServiceImpl(noteRepository);
		FilterService filterService = new FilterServiceImpl(noteRepository);
		SortService sortService = new SortServiceImpl(noteRepository);

		// создаём контроллер и передаём в него всё
		NoteController controller = new NoteController(
				noteRepository,
				addService,
				listService,
				deleteService,
				editService,
				filterService,
				sortService
		);

		// запускаем
		controller.start();
	}
}