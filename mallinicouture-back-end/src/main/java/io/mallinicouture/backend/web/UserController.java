package io.mallinicouture.backend.web;

import io.mallinicouture.backend.web.payload.JWTLoginSucessResponse;
import io.mallinicouture.backend.web.payload.LoginRequest;
import io.mallinicouture.backend.domain.Client;
import io.mallinicouture.backend.security.JwtTokenProvider;
import io.mallinicouture.backend.service.MapValidationErrorService;
import io.mallinicouture.backend.service.UserService;
import io.mallinicouture.backend.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.security.Principal;

import static io.mallinicouture.backend.security.SecurityConstants.TOKEN_PREFIX;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // TODO: remove in production
    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return mapValidationErrorService.doMapping(bindingResult);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Client client, BindingResult bindingResult) {

        // Validate password match
        if (client != null) userValidator.validate(client, bindingResult);

        if (bindingResult.hasErrors()) return mapValidationErrorService.doMapping(bindingResult);

        Client savedClient = userService.saveClient(client);

        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PostMapping("/credit-card")
    public ResponseEntity<?> updateCreditCard(@Valid @RequestBody CreditCard creditCard,
                                              BindingResult bindingResult,
                                              Principal principal) {

        if (bindingResult.hasErrors()) return mapValidationErrorService.doMapping(bindingResult);

        CreditCard saved = userService.addCreditCard(principal.getName(), creditCard);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
}
