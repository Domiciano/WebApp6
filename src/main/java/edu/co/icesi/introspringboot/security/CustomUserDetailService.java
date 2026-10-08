package edu.co.icesi.introspringboot.security;


import edu.co.icesi.introspringboot.entity.User;
import edu.co.icesi.introspringboot.entity.Permission;
import edu.co.icesi.introspringboot.service.PermissionService;
import edu.co.icesi.introspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Obtiendo la entidad User
        User user = userService.findByUsername(username);
        String[] authorities = permissionService.findByUsername(username).stream()
                .map(Permission::getName)
                .toArray(String[]::new);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // Cambiar el usuario
                .password(user.getPassword()) // Especificar la contraseña
                .authorities(authorities) // Las authorities representan los roles o permisos que tiene el usuario
                .build();
    }
}
