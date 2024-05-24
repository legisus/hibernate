package com.codesoft.edu.service.impl;

import com.codesoft.edu.model.Role;
import com.codesoft.edu.repository.RoleRepository;
import com.codesoft.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public Role create(Role role) {
        String name = role.getName();
        Optional<Role> findByNameOpt = roleRepository.findByName(name);
        if (!findByNameOpt.isPresent()) {
            roleRepository.save(role);
        } else {
            return role;
        }
        return role;
    }

    @Transactional
    @Override
    public Role readById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Role update(Role role) {
        String name = role.getName();
        Optional<Role> existRoleOpt = roleRepository.findByName(name);
        if (existRoleOpt.isPresent()) {
            return roleRepository.save(role);
        } else {
            return create(role);
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
