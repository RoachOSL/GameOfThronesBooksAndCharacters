package com.gameofthronesbooksandcharacters.services;

import com.gameofthronesbooksandcharacters.databaseutils.CustomUserDao;
import com.gameofthronesbooksandcharacters.datamodel.CustomUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserSecurityService implements UserDetailsService {
    private final CustomUserDao dao = new CustomUserDao();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser customUser = dao.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.withUsername(customUser.getEmail())
                .password(customUser.getPassword())
                .authorities("USER").build();
    }

}
