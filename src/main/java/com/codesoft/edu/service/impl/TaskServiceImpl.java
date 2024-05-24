package com.codesoft.edu.service.impl;

import com.codesoft.edu.model.Task;
import com.codesoft.edu.repository.TaskRepository;
import com.codesoft.edu.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task readById(long id) {
        return taskRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Task update(Task task) {
        List<Task> currentTasks = taskRepository.findAll();
        for (Task currentTask : currentTasks) {
            if (currentTask.getId() == currentTask.getId()) {
                if (!Objects.equals(currentTask.getName(), currentTask.getName()) || !Objects.equals(currentTask.getPriority(), currentTask.getPriority())) {
                    currentTask.setName(task.getName());
                    currentTask.setPriority(task.getPriority());
                }
            }
        }

        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.delete(taskRepository.getOne(id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getToDo().getId() == todoId)
                .collect(toList());
    }
}
