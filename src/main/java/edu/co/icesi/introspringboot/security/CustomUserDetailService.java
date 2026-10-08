package edu.co.icesi.introspringboot.security;


import edu.co.icesi.introspringboot.entity.Role;
import edu.co.icesi.introspringboot.entity.User;
import edu.co.icesi.introspringboot.entity.Permission;
import edu.co.icesi.introspringboot.repo.PermissionRepository;
import edu.co.icesi.introspringboot.repo.RoleRepository;
import edu.co.icesi.introspringboot.service.PermissionService;
import edu.co.icesi.introspringboot.service.RoleService;
import edu.co.icesi.introspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Obtiendo la entidad User
        User user = userService.findByUsername(username);
        List<Role> roles = roleRepository.findByUserRoles_User_Username(username);
        List<Permission> permissions = permissionRepository.findDistinctByRolePermissions_Role_UserRoles_User_Username(username);
        //List<Role> -> List<SimpleGrantedAuthority>
        List <SimpleGrantedAuthority> authoritiesRole = roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())
        ).toList();

        List <SimpleGrantedAuthority> authoritiesPermission = permissions.stream().map(
                permission -> new SimpleGrantedAuthority(permission.getName())
        ).toList();


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authoritiesRole.forEach(element -> authorities.add(element));
        authoritiesPermission.forEach(element -> authorities.add(element));


        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // Cambiar el usuario
                .password(user.getPassword()) // Especificar la contraseña
                .authorities(authorities)
                .build();
    }
}
