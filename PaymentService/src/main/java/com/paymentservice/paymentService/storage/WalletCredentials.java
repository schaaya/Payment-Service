package com.paymentservice.paymentService.storage;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "WalletCredentials")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Configuration
@ComponentScan
@Scope()
public class WalletCredentials {

  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Id
  @Column
  private String phoneNumber;

  @Column
  private String password;

  @Nullable
  @Column
  private String mailId;

  @Column
  private String dateOfBirth;


  @Column(name = "created", columnDefinition = "datetime(3) default current_timestamp(3)", updatable = false, insertable = false)
  @Generated(value = GenerationTime.INSERT)
  private Date created;

  @Column(name = "updated", columnDefinition = "datetime(3) default current_timestamp(3)", updatable = false, insertable = false)
  @Generated(value = GenerationTime.ALWAYS)
  private Date updated;
}
