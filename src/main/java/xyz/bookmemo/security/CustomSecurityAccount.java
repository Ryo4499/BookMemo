package xyz.bookmemo.security;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.bookmemo.entity.Account;

public class CustomSecurityAccount extends Account implements UserDetails {

  private static final long serialVersionUID = 6518052067649649105L;

  @Getter @Setter private Set<GrantedAuthority> authorities;

  public CustomSecurityAccount(Account account, Set<GrantedAuthority> set) {
    setAuthorities(set);
    setId(account.getId());
    setAccountName(account.getAccountName());
    setEmail(account.getEmail());
    setPassword(account.getPassword());
    setMemos(account.getMemos());
  }

  public CustomSecurityAccount(Account account) {
    setRoles(account.getRoles());
    setId(account.getId());
    setAccountName(account.getAccountName());
    setEmail(account.getEmail());
    setPassword(account.getPassword());
    setMemos(account.getMemos());
  }

  /**
   * This function returns true if the account is not expired.
   *
   * @return The method isAccountNonExpired() is returning true.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /** > This function returns true if the account is not locked, false otherwise */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * The user's credentials have not expired.
   *
   * @return The method isCredentialsNonExpired() is being returned.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * This function returns true if the user is enabled, false otherwise.
   *
   * @return True
   */
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * The getUsername() function returns the email address of the user
   *
   * @return The email address of the user.
   */
  @Override
  public String getUsername() {
    return getEmail();
  }
}
