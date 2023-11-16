package me.pro0xy.bankemulation.service;

import me.pro0xy.bankemulation.db.entity.Role;
import me.pro0xy.bankemulation.db.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pro0xy
 * <p>Created on 16.11.2023 at 03:38</p>
 */

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").orElse(null);
    }

    public Role getAdminRole() {
        return roleRepository.findByName("ROLE_ADMIN").orElse(null);
    }
}
