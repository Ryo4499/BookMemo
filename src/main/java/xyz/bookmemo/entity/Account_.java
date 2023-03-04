package xyz.bookmemo.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The Account_ class is a metamodel class that represents the Account class
 */
@StaticMetamodel(Account.class)
public class Account_ {

  public static volatile SingularAttribute<Account, Long> id;
  public static volatile SingularAttribute<Account, String> accountName;
  public static volatile SingularAttribute<Account, String> email;
  public static volatile SingularAttribute<Account, String> password;
  public static volatile ListAttribute<Account, Memo> memos;
  public static volatile SetAttribute<Account, Role> roles;
}
