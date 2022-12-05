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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Configuration
@ComponentScan
@Table(name = "request_details")
public class RequestDetails {

  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "client_request_id")
  private String clientRequestId;

  @Id
  @Column(name = "request_id")
  private String requestId;

  @Column(name = "session_id")
  private String sessionId;

  @Column(name = "command_type")
  private String commandType;

  @Column(name = "created", columnDefinition = "datetime(3) default current_timestamp(3)", updatable = false, insertable = false)
  @Generated(value = GenerationTime.INSERT)
  private Date created;

  @Column(name = "updated", columnDefinition = "datetime(3) default current_timestamp(3)", updatable = false, insertable = false)
  @Generated(value = GenerationTime.ALWAYS)
  private Date updated;


}
