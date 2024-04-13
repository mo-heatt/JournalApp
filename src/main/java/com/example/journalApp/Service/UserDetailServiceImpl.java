package com.example.journalApp.Service;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.example.journalApp.Repository.UserRepository;
import com.example.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if(user != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword()).build();
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
                    return userDetails;
        }
        throw  new UsernameNotFoundException("User not found with Username: "+ username);
    }
}
