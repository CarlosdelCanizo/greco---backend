package com.greco.security;

import com.greco.model.Users;
import com.greco.repository.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class CrmUserDetailsService implements UserDetailsService {
	@Autowired
	private UsersRepository usersRepository;

    /**
     * find a user by his username and load it
     * @param email
     * @return adapter between the database and what Spring Security needs inside the SecurityContextHolder
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("UserName " + email + " not found");
        }
        return new CrmUserDetails(user);
    }


}
