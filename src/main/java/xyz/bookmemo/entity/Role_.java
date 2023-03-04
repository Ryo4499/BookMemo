package xyz.bookmemo.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * "Role_ is a metamodel class that represents the Role entity."
 *
 * The @StaticMetamodel annotation tells JPA that Role_ is a metamodel class. The Role_ class is
 * annotated with the @StaticMetamodel annotation because it is a metamodel class
 */
@StaticMetamodel(Role.class)
public class Role_ {

  public static volatile SingularAttribute<Role, Long> id;
  public static volatile SingularAttribute<Role, String> authority;
  public static volatile SetAttribute<Role, Account> accounts;
}
