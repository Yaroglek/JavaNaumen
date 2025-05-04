package ru.yaroglek.naujava.practice3.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.yaroglek.naujava.practice3.app.service.UserService;
import ru.yaroglek.naujava.practice3.domain.User;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService
{
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User appUser = userService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(appUser.getPassword())
                .authorities(mapRoles(appUser))
                .build();
    }

    private GrantedAuthority mapRoles(User appUser)
    {
        return new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name());
    }
}

