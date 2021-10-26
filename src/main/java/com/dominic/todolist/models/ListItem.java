package com.dominic.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ListItem {
    private Long id;
    private Boolean done;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDue;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    public ListItem(Long id, String title, String content, LocalDate dateDue, Boolean done) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateDue = dateDue;
        this.done = done;
    }
}
