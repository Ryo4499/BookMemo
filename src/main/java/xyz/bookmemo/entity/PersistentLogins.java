package xyz.bookmemo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins implements Serializable {

  @Id
  @Column(name = "series", nullable = false)
  @Size(max = 64)
  private String series;

  @Column(name = "username", nullable = false)
  @Size(max = 64)
  private String userName;

  @Column(name = "token", nullable = false)
  @Size(max = 64)
  private String token;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(
      name = "last_used",
      nullable = false,
      columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
  private Date lastUsed;
}
