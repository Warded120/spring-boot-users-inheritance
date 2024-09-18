package com.ivan.usercrud.service;

import com.ivan.usercrud.entity.Role;
import com.ivan.usercrud.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleEntityServiceImpl implements RoleEntityService {

    RoleRepository roleRepository;

    @Autowired
    public RoleEntityServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
