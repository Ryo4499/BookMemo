package com.herokuapp.bookmemo4444.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

public class Account_ {
	public static volatile SingularAttribute<Account, Long> id;
	public static volatile SingularAttribute<Account, String> accountName;
	public static volatile SingularAttribute<Account, String> email;
	public static volatile SingularAttribute<Account, String> password;
	public static volatile ListAttribute<Account, Memo> memos;
	public static volatile SetAttribute<Account, Role> roles;
}
