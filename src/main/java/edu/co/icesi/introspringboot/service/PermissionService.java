package edu.co.icesi.introspringboot.service;

import edu.co.icesi.introspringboot.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    List<Permission> findAll();

    Optional<Permission> findById(Integer id);

    Permission save(Permission permission);

    void deleteById(Integer id);

    List<Permission> findByUsername(String username);

}
