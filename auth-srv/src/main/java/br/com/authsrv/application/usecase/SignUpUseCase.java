package br.com.authsrv.application.usecase;

import br.com.authsrv.application.domain.auth.entity.UserAccount;
import br.com.authsrv.application.domain.auth.exceptions.UserAlreadyExistsException;
import br.com.authsrv.application.port.in.ISignUpUseCase;
import br.com.authsrv.application.port.in.dto.SignUpInput;
import br.com.authsrv.application.port.in.dto.SignUpOutput;
import br.com.authsrv.application.port.out.IUserAccountRepository;
import br.com.authsrv.application.port.out.IUserRegistrationPort;
import br.com.authsrv.application.port.out.dto.RegisterProfileInput;
import br.com.authsrv.application.port.out.dto.RegisterProfileOutput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase implements ISignUpUseCase {

    private final IUserAccountRepository repository;
    private final IUserRegistrationPort registrationPort;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCase(IUserAccountRepository repository, IUserRegistrationPort registrationPort, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.registrationPort = registrationPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpOutput signUp(SignUpInput input) {
        if (repository.existsByUsername(input.username())) {
            throw new UserAlreadyExistsException(input.username());
        }
        RegisterProfileOutput profile = registrationPort.register(
                new RegisterProfileInput(input.name(), input.document(), input.profileType()));
        UserAccount account = new UserAccount(null, input.username(), passwordEncoder.encode(input.password()),
                profile.profileId(), profile.name(), profile.document(), profile.profileType());
        UserAccount saved = repository.save(account);
        return new SignUpOutput(saved.accountId(), saved.username(), profile.profileId());
    }
}
