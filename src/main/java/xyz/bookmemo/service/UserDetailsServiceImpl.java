package xyz.bookmemo.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Role;
import xyz.bookmemo.repository.AccountRepository;
import xyz.bookmemo.security.CustomSecurityAccount;

@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final AccountRepository accountRepository;

  public UserDetailsServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  /**
   * It takes in a username, finds the account associated with that username, and returns a
   * CustomSecurityAccount object that contains the account and the authorities associated with that
   * account
   *
   * @param email The email address of the user.
   * @return A CustomSecurityAccount object.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final Account account = accountRepository.findByEmail(email);
    if (account == null)
      throw new UsernameNotFoundException("Username and or password was incorrect.");
    return new CustomSecurityAccount(account, getAuthorities(account));
  }

  /**
   * It takes an account and returns a set of authorities
   *
   * @param account The account object that we are going to use to create the UserDetails object.
   * @return A set of GrantedAuthority objects.
   */
  private Set<GrantedAuthority> getAuthorities(Account account) {
    final Set<GrantedAuthority> authorities = new HashSet<>();
    for (final Role authrity : account.getRoles()) {
      final GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authrity.getAuthority());
      authorities.add(grantedAuthority);
    }
    return authorities;
  }
}
