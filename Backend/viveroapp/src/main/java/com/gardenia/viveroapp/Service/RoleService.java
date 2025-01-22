package com.gardenia.viveroapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenia.viveroapp.Model.Role;
import com.gardenia.viveroapp.Model.DTO.RoleDTO;
import com.gardenia.viveroapp.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            RoleDTO roleDTO = new RoleDTO(role.getIdrole(), role.getName());
            rolesDTO.add(roleDTO);
        }
        return rolesDTO;
    }

    public RoleDTO addRole(RoleDTO roleDTO) {
        roleRepository.save(new Role(roleDTO.getName()));
        return roleDTO;
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    public RoleDTO updateRole(Integer id, RoleDTO role) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role savedRole = optionalRole.get();
            savedRole.setName(role.getName());
            savedRole = roleRepository.save(savedRole);
            RoleDTO newRole = new RoleDTO(savedRole.getIdrole(), savedRole.getName());
            return newRole;
        } else {
            return null;
        }
    }
}
