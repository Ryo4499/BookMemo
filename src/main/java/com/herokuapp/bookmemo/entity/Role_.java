package com.herokuapp.bookmemo.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
  public static volatile SingularAttribute<Role, Long> id;
  public static volatile SingularAttribute<Role, String> authority;
  public static volatile SetAttribute<Role, Account> accounts;
}
