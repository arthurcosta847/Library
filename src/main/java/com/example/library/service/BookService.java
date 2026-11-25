package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Book create(Book book) {
        return repository.save(book);
    }

    public Book update(long id, Book updated) {
        Book existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }
        existing.setAvailable(updated.isAvailable());
        existing.setIsbn(updated.getIsbn());
        existing.setTitle(updated.getTitle());
        existing.setPublicationYear(updated.getPublicationYear());
        return repository.save(existing);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
