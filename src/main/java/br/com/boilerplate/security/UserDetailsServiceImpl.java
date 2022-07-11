package br.com.boilerplate.security;

import br.com.boilerplate.model.User;
import br.com.boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String credential) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(credential);
        if (user == null) {
            throw new UsernameNotFoundException(credential);
        }

        return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getName());
    }
}
