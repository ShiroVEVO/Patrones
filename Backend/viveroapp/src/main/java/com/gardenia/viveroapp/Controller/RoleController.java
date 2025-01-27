package com.gardenia.viveroapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gardenia.viveroapp.DTO.RoleDTO;
import com.gardenia.viveroapp.Service.RoleService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        try {
            List<RoleDTO> roles = roleService.getAllRoles();
            return new ResponseEntity<List<RoleDTO>>(roles, null, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<List<RoleDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<RoleDTO>(roleService.addRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO role) {
        return new ResponseEntity<RoleDTO>(roleService.updateRole(role), HttpStatus.OK);
    }
}
