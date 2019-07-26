package com.hunsley.cache.model;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CACHABLE")
public class CacheableBean {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "NAME")
  private String name;

  @Column(name = "DESC")
  private String desc;

  public CacheableBean() {}

  public CacheableBean(
      @NotNull @Max(value = 255) String name,
      @Max(value = 255) String desc) {
    this.name = name;
    this.desc = desc;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CacheableBean)) {
      return false;
    }
    CacheableBean cacheableBean = (CacheableBean) o;
    return id == cacheableBean.id &&
        name.equals(cacheableBean.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
