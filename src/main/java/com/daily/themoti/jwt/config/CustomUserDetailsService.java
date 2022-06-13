package com.daily.themoti.jwt.config;

import com.daily.themoti.user.constant.UserRole;
import com.daily.themoti.user.domain.User;
import com.daily.themoti.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> new User(null, null, null, null, null));
        Set<UserRole> userRole = new HashSet<>();
        userRole.add(user.getRole());
        return new CustomUserDetails(
                String.valueOf(user.getId()),
                String.valueOf(user.getEmail()),
                userRole.stream().map(role -> role.getKey())
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
        );
    }
}
