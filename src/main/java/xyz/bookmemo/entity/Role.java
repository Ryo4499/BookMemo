package xyz.bookmemo.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/** A role is a set of permissions that can be assigned to an account */
@Data
@Entity
@Table(name = "roles")
public class Role implements Serializable {

  private static final long serialVersionUID = 4779750262031913699L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false, unique = true)
  private String authority;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<Account> accounts;
}
