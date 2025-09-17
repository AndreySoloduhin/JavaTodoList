package org.controller;

import org.repository.Note;
import org.repository.NoteRepository;
import org.repository.Status;
import org.service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class NoteController {

	private final NoteRepository noteRepository;
	private final AddService addService;
	private final ListService listService;
	private final DeleteService deleteService;
	private final EditService editService;
	private final FilterService filterService;
	private final SortService sortService;

	public NoteController(NoteRepository noteRepository, AddService addService, ListService listService, DeleteService deleteService, EditService editService,
						  FilterService filterService, SortService sortService) {
		this.noteRepository = noteRepository;
		this.addService = addService;
		this.listService = listService;
		this.deleteService = deleteService;
		this.editService = editService;
		this.filterService = filterService;
		this.sortService = sortService;
	}

	Scanner scanner = new Scanner(System.in);

	public void start() {
		boolean running = true;

		while(running) {
			System.out.println("Введите команду (add, list, edit, filter, sort, delete, exit): ");
			String command = scanner.nextLine();

			switch (command) {
				case "add":
					System.out.println("Введите название: ");
					String name = scanner.nextLine();

					System.out.println("Введите заметку: ");
					String content = scanner.nextLine();

					System.out.println("Введите дату выполнения (yyyy-MM-dd): ");
					LocalDate date = null;
					while (date == null) {
						try {
							String inputDate = scanner.nextLine();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							date = LocalDate.parse(inputDate, formatter);
						} catch (Exception e) {
							System.out.println("Неверный формат даты! Нужно вводить так: yyyy-MM-dd");
						}
					}

					System.out.println("Введите статус (TODO, IN_PROGRESS, DONE): ");
					Status status = Status.valueOf(scanner.nextLine().toUpperCase());

					Note note = Note.builder()
							.name(name)
							.content(content)
							.date(date)
							.status(status)
							.build();

					addService.addNote(note);
					break;
				case "list":
					List<Note> notes = listService.getAllNotes();
					if (notes.isEmpty()) {
						System.out.println("Задач пока нет.");
					} else {
						notes.forEach(System.out::println);
					}
					break;
				case "edit":
					System.out.println("Введите id для редактирования: ");
					long id = Long.parseLong(scanner.nextLine());

					Optional<Note> optionalNote = noteRepository.findById(id);

					if (optionalNote.isEmpty()) {
						System.out.println("Задача с таким id не найдена");
						break;
					}

					note = optionalNote.get();

					System.out.println("Выберите пункт для исправления: ");
					System.out.println("1 - Имя");
					System.out.println("2 - Заметку");
					System.out.println("3 - Дату");
					System.out.println("4 - Статус");

					int field = Integer.parseInt(scanner.nextLine());

					System.out.println("Введите новое значения: ");
					String newValue = scanner.nextLine();

					editService.editNote(note, field, newValue);
					System.out.println("Задача обновлена: " + note);
					break;
				case "filter":
					System.out.println("Введите статус для фильтрации (TODO, IN_PROGRESS, DONE): ");
					String statusInput = scanner.nextLine().toUpperCase();

					try {
						Status statusFilter = Status.valueOf(statusInput);
						List<Note> filteredNotes = filterService.filterNotes(statusFilter);

						if (filteredNotes.isEmpty()) {
							System.out.println("Нет заметок со статусом " + statusFilter);
						} else {
							filteredNotes.forEach(System.out::println);
						}
					} catch (IllegalArgumentException e) {
						System.out.println("Некорректный статус!");
					}
					break;
				case "sort":
					System.out.println("Введите поле для сортировки (id, title, status): ");
					String statusForSort = scanner.nextLine();

					System.out.println("Введите порядок (asc / desc): ");
					String order = scanner.nextLine();

					boolean ascending = order.equalsIgnoreCase("asc");

					List<Note> sortedNotes = sortService.sortNotes(statusForSort, ascending);

					sortedNotes.forEach(System.out::println);
					break;
				case "delete":
					System.out.println("Введите id заметки для удаления: ");
					id = Long.parseLong(scanner.nextLine());
					deleteService.deleteNote(id);
					break;
				case "exit":
					running = false;
					break;
			}
		}
	}
}
