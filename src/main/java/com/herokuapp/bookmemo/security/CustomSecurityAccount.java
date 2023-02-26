package com.herokuapp.bookmemo.security;

import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.herokuapp.bookmemo.entity.Account;
import lombok.Getter;
import lombok.Setter;

public class CustomSecurityAccount extends Account implements UserDetails {

  private static final long serialVersionUID = 6518052067649649105L;

  @Getter
  @Setter
  private Set<GrantedAuthority> authorities;

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

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }
}
