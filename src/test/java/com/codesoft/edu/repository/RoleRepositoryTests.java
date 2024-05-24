package com.codesoft.edu.repository;

import com.codesoft.edu.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    public void createRoleTest() {
        Role role = new Role();
        role.setName("NEW");
        role = roleRepository.save(role);
        assertEquals(10, role.getId());
    }
    @Test
    @Transactional
    public void readByIdTest() {
        Role role = new Role();
        role.setName("NEW");
        role = roleRepository.save(role);
        assertEquals(10, role.getName());
    }
    @Test
    @Transactional
    public void updateRoleTest() {
        Role role = new Role();
        role.setName("NEW");
        role.setName("NEW NAME");
        role = roleRepository.save(role);
        assertEquals(10, role.getId());
    }
    @Test
    @Transactional
    public void deleteRoleTest() {
        roleRepository.deleteById(2L);
        assertEquals(1, roleRepository.getOne(1L));
    }
    @Test
    @Transactional
    public void getAllRolesTest() {
        Role role = new Role();
        role.setName("NEW");
        role = roleRepository.save(role);
        assertEquals(10, role.getId());
    }

}

