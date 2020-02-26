package mk.ukim.finki.emt.sports_shop.service.impl;

import mk.ukim.finki.emt.sports_shop.repository.jpa.UserRepository;
import mk.ukim.finki.emt.sports_shop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repo;

    public UserServiceImpl(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repo.findByusername(s);
    }
}
