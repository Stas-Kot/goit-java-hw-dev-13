package com.goit.feature.mvc.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public Map<String, Note> listAll() {
        return noteList;
    }

    public Note add(Note note) {
//        String tempId = UUID.randomUUID().toString();
//        while(isMatch(tempId, listAll())) {
//            tempId = UUID.randomUUID().toString();
//        }
//        note.setId(tempId);
//        return note;

        String tempId = UUID.randomUUID().toString();
        while(isMatch(tempId, listAll())) {
            tempId = UUID.randomUUID().toString();
        }
        note.setId(tempId);
        listAll().put(tempId, note);
        return note;
    }

    public Note getById(String id) {
//       return listAll().stream().filter(note -> note.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
        return listAll().get(id);
    }

    public void deleteById(String id) {
//        listAll().remove(getById(id));
        if(!isMatch(id, listAll())) {
            throw new RuntimeException("Note with " + id + " doesn't exist!");
        } else {
            listAll().remove(id);
        }
    }

    public void update(Note note) {
        Note noteForUpdate = getById(note.getId());
        noteForUpdate.setTitle(note.getTitle());
        noteForUpdate.setContent(note.getContent());
    }

//    private static boolean isMatch(String tempId, List<Note> noteList) {
//        return noteList.stream()
//                .anyMatch(n -> n.getId().equals(tempId));
//    }

    private static boolean isMatch(String tempId, Map<String, Note> noteList) {
        return noteList.containsKey(tempId);
    }
}
