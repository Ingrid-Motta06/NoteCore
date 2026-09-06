package com.exemplo.anotacoes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.anotacoes.model.Note;
import com.exemplo.anotacoes.service.NoteService;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // GET /api/notes - Lista todas as notas
    @GetMapping
    public ResponseEntity<List<Note>> listarTodas() {
        return ResponseEntity.ok(noteService.listarTodas());
    }

    // GET /api/notes/{id} - Busca uma nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.buscarPorId(id));
    }

    // POST /api/notes - Cria uma nova nota
    @PostMapping
    public ResponseEntity<Note> criar(@RequestBody Note note) {
        Note novaNota = noteService.criar(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    // PUT /api/notes/{id} - Atualiza uma nota existente
    @PutMapping("/{id}")
    public ResponseEntity<Note> atualizar(@PathVariable Long id, @RequestBody Note note) {
        Note notaAtualizada = noteService.atualizar(id, note);
        return ResponseEntity.ok(notaAtualizada);
    }

    // DELETE /api/notes/{id} - Deleta uma nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        noteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}