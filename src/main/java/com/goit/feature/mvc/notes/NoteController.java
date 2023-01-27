package com.goit.feature.mvc.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getList() {
        ModelAndView result = new ModelAndView("notes/list");
        System.out.println("noteService.listAll() in NoteController = " + noteService.listAll().toString());
        result.addObject("notes", noteService.listAll().values());
        return result;
    }

    @PostMapping("/delete")
    public void delete(@RequestParam(name = "id") String noteId,
                       HttpServletResponse resp) {
//            noteService.deleteById(noteId);
        System.out.println("noteId = " + noteId);
        try {
            resp.sendRedirect("http://localhost:8080/note/list");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("notes/edit");
        result.addObject("note", noteService.getById(id));

        return result;
    }

    @PostMapping("/edit")
    public void saveUpdatedNote(@RequestParam(name = "title") String noteTitle,
                                @RequestParam(name = "content") String noteContent,
                                @RequestParam(name = "id") String noteId,
                                HttpServletResponse resp) {
        Note note = new Note(noteId, noteTitle, noteContent);
        noteService.update(note);

        try {
            resp.sendRedirect("http://localhost:8080/note/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/create")
    public ModelAndView newNote() {
        ModelAndView result = new ModelAndView("notes/create");
        return result;
    }


    @PostMapping("/create")
    public void create(@RequestParam(name = "title") String noteTitle,
                       @RequestParam(name = "content") String noteContent,
                       HttpServletResponse resp) {
        Note note = new Note(noteTitle, noteContent);
        noteService.add(note);

        try {
            resp.sendRedirect("http://localhost:8080/note/list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
