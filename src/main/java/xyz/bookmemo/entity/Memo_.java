package xyz.bookmemo.entity;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The above class is a metamodel class that represents the entity class Memo
 */
@StaticMetamodel(Memo.class)
public class Memo_ {

  public static volatile SingularAttribute<Memo, Long> memoId;
  public static volatile SingularAttribute<Memo, String> title;
  public static volatile SingularAttribute<Memo, String> content;
  public static volatile SingularAttribute<Memo, String> category;
  public static volatile SingularAttribute<Memo, String> bookName;
  public static volatile SingularAttribute<Memo, Account> account;
  public static volatile SingularAttribute<Memo, Date> createdDate;
  public static volatile SingularAttribute<Memo, Date> updatedDate;
}
