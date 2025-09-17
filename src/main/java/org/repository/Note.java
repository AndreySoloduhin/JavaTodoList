package org.repository;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Note {
	private Long id;
	private String name;
	private String content;
	private LocalDate date;
	private Status status;


}
