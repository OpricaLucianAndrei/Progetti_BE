package it.epicode.gestioneEventi.service;

import it.epicode.gestioneEventi.Dto.UserLoginDto;
import it.epicode.gestioneEventi.entity.User;
import it.epicode.gestioneEventi.exceptions.UnauthorizedException;
import it.epicode.gestioneEventi.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserandCreateToken(UserLoginDto utenteLoginDto) {
        User utente = userService.getUtenteByEmail(utenteLoginDto.getEmail());

        if(passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {

            return jwtTool.createToken(utente);
        } else {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}
