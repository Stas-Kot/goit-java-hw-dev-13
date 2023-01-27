package com.goit.feature.mvc.notes;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Note {
    private String id;
    private String title;
    private String content;

    public Note() {
    };

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
