package com.goit.feature.mvc.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteService {
//    private List<Note> noteList = new ArrayList<>();
    private Map<String, Note> noteList = new HashMap<>();

    {
        Note note  = new Note();
        note.setTitle("Product List");
        note.setContent("milk, water, apples");
        add(note);
        Note note1  = new Note();
        note1.setTitle("Tasks for tomorrow");
        note1.setContent("morning running, meet Bob, party at Soho");
        add(note1);
    }
//    public List<Note> listAll() {
//        return noteList;
//    }

    public List<Note> listAll() {
//        return noteList;
        return new ArrayList<>(noteList.values());
    }

    public synchronized Note add(Note note) {
//        String tempId = UUID.randomUUID().toString();
//        while(isMatch(tempId, listAll())) {
//            tempId = UUID.randomUUID().toString();
//        }
//        note.setId(tempId);
//        return note;

        String tempId = UUID.randomUUID().toString();
        while(isMatch(tempId, noteList)) {
            tempId = UUID.randomUUID().toString();
        }
        note.setId(tempId);
        noteList.put(tempId, note);
        return note;
    }

    public Note getById(String id) {
//       return listAll().stream().filter(note -> note.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
        return noteList.get(id);
    }

    public synchronized void deleteById(String id) {
        if(!isMatch(id, noteList)) {
            throw new RuntimeException("Note with " + id + " doesn't exist!");
        } else {
            noteList.remove(id);
        }
    }

    public synchronized void update(Note note) {
        noteList.put(note.getId(), note);
    }

//    private static boolean isMatch(String tempId, List<Note> noteList) {
//        return noteList.stream()
//                .anyMatch(n -> n.getId().equals(tempId));
//    }

    private static boolean isMatch(String tempId, Map<String, Note> noteList) {
        return noteList.containsKey(tempId);
    }
}
