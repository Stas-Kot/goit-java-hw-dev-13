package com.goit.feature.mvc.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getList() {
        ModelAndView result = new ModelAndView("notes/list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @PostMapping("/delete")
    public RedirectView delete(@RequestParam(name = "id") String noteId) {
        noteService.deleteById(noteId);
        return new RedirectView("list");
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(name = "id") String id) {
        ModelAndView result = new ModelAndView("notes/edit");
        result.addObject("note", noteService.getById(id));

        return result;
    }

    @PostMapping("/edit")
    public RedirectView saveUpdatedNote(Note note) {
        noteService.update(note);
        return new RedirectView("list");
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("notes/create");
    }


    @PostMapping("/create")
    public RedirectView saveCreatedNote(Note note) {
        noteService.add(note);
        return new RedirectView("list");
    }
}
