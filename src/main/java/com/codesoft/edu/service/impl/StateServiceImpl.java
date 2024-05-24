package com.codesoft.edu.service.impl;

import com.codesoft.edu.model.State;
import com.codesoft.edu.service.StateService;
import com.codesoft.edu.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public State create(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State readById(long id) {
        return stateRepository.findById(id)
                .orElse(null);
    }

    @Override
    public State update(State state) {
        List<State> currentStates = stateRepository.findAll();
        for (State currentState : currentStates) {
            if (currentState.getId() == state.getId()) {
                if (!Objects.equals(currentState.getName(), state.getName()) || !Objects.equals(currentState.getTasks(), state.getTasks())) {
                    currentState.setName(state.getName());
                    currentState.setTasks(state.getTasks());
                }
            }
        }

        return stateRepository.save(state);
    }

    @Override
    public void delete(long id) {
        stateRepository.delete(stateRepository.getOne(id));
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        return stateRepository.getStateByName(name);
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.findAllByOrderByNameAsc();
    }
}
