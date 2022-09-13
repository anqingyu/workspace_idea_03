package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Accessors(chain = true) // 提供链式访问
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User implements Serializable {
    private static final long serialVersionUID = -916357210051789799L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @Column(name = "password", nullable = false, length = 10)
    private String password;
    @Column(name = "age", nullable = false)
    private Integer age;
}
