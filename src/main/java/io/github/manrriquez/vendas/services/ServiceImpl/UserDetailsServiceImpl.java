package io.github.manrriquez.vendas.services.ServiceImpl;

import io.github.manrriquez.vendas.Repositories.UserRepository;
import io.github.manrriquez.vendas.exceptions.PasswordInvalidException;
import io.github.manrriquez.vendas.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public UserModel saveUser(UserModel user) {
//        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
//        user.setRoles(roles);
        return userRepository.save(user);
    }


    public UserDetails authenticated(UserModel user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());

        boolean passwordLike = encoder.matches(user.getPassword(), userDetails.getPassword());
        if (passwordLike) {
            return userDetails;
        }
        throw new PasswordInvalidException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserModel buscarUsuarioLogado() {
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByLogin(login).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USUARIO N ENCOBRADO");
        });
    }
}
