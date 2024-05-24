package com.codesoft.edu.repository;

import com.codesoft.edu.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State getStateByName(String state);
    List<State> findAllByOrderByNameAsc();
}
