package xyz.bookmemo.entity;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/** The Account_ class is a metamodel class that represents the Account class */
@StaticMetamodel(PersistentLogins.class)
public class PersistentLogins_ {

  public static volatile SingularAttribute<PersistentLogins, String> series;
  public static volatile SingularAttribute<PersistentLogins, String> userName;
  public static volatile SingularAttribute<PersistentLogins, String> token;
  public static volatile SingularAttribute<PersistentLogins, Date> lastUsed;
}
