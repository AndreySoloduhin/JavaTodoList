package org.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
	private Long id;
	private String name;
	private String content;
	private LocalDate date;
	private Status status;


}
