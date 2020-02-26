package mk.ukim.finki.emt.sports_shop.config;

import mk.ukim.finki.emt.sports_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ProductAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if ("".equals(username) || "".equals(password)) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        UserDetails user = userService.loadUserByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("Username invalid");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Boolean passwordsEqual = passwordEncoder.matches(password,encodedPassword);
        if (!passwordsEqual) {
            throw new UsernameNotFoundException("Password invalid");
        }

        return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
