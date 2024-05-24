package com.codesoft.edu.service.impl;

import com.codesoft.edu.model.ToDo;
import com.codesoft.edu.repository.ToDoRepository;
import com.codesoft.edu.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;


    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @Override
    public ToDo create(ToDo todo) {
        return toDoRepository.save(todo);
    }

    @Override
    public ToDo readById(long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    @Override
    public ToDo update(ToDo todo) {
        if (todo != null && toDoRepository.existsById(todo.getId())) {
            return toDoRepository.save(todo);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        return toDoRepository.findAllByUserId(userId);
    }
}
