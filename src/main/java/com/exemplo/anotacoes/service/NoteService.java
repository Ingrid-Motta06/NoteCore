package com.exemplo.anotacoes.service;

import com.exemplo.anotacoes.model.Note;
import com.exemplo.anotacoes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listarTodas() {
        return noteRepository.findAll();
    }

    public Note buscarPorId(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anotação não encontrada com o ID: " + id));
    }

    public Note criar(Note note) {
        return noteRepository.save(note);
    }

    public Note atualizar(Long id, Note noteAtualizada) {
        Note noteExistente = buscarPorId(id);
        noteExistente.setTitulo(noteAtualizada.getTitulo());
        noteExistente.setCorpo(noteAtualizada.getCorpo());
        noteExistente.setCategoria(noteAtualizada.getCategoria());
        noteExistente.setAutor(noteAtualizada.getAutor());
        noteExistente.setTags(noteAtualizada.getTags());
        return noteRepository.save(noteExistente);
    }

    public void deletar(Long id) {
        Note note = buscarPorId(id);
        noteRepository.delete(note);
    }
}
