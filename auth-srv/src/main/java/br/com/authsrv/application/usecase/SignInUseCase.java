package br.com.authsrv.application.usecase;

import br.com.authsrv.application.domain.auth.entity.UserAccount;
import br.com.authsrv.application.domain.auth.exceptions.InvalidCredentialsException;
import br.com.authsrv.application.port.in.ISignInUseCase;
import br.com.authsrv.application.port.in.dto.SignInInput;
import br.com.authsrv.application.port.in.dto.SignInOutput;
import br.com.authsrv.application.port.out.IUserAccountRepository;
import br.com.authsrv.application.port.out.ITokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignInUseCase implements ISignInUseCase {

    private final IUserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ITokenProvider tokenProvider;

    public SignInUseCase(IUserAccountRepository repository, PasswordEncoder passwordEncoder, ITokenProvider tokenProvider) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public SignInOutput signIn(SignInInput input) {
        UserAccount account = repository.findByUsername(input.username())
                .orElseThrow(InvalidCredentialsException::new);
        if (!passwordEncoder.matches(input.password(), account.passwordHash())) {
            throw new InvalidCredentialsException();
        }
        String token = tokenProvider.generateToken(account);
        return new SignInOutput(token, "Bearer", tokenProvider.getExpirationSeconds());
    }
}
