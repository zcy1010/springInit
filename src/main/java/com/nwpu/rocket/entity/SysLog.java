package com.nwpu.rocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zcy
 * @Date 2021/4/17 21:45
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
@DynamicUpdate
public class SysLog implements Serializable {
    /**
     * 序列版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日志id，自增
     */
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 日志对应的用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    /**
     * 调用方法的ip地址
     */
    @Column(name = "log_ip")
    private String ip;

    /**
     * 用户条用方法的类型 自己注释的内容
     */
    @Column(name = "log_operation")
    private String operation;

    /**
     * 用户调用controller中的那个方法
     */
    @Column(name = "log_method")
    private String method;


    /**
     * 请求参数
     */
    @Column(name = "log_params")
    private String params;

    /**
     * 方法执行时间
     */
    @Column(name = "log_time")
    private Long time;

    /**
     * 注册时间
     */
    @Column(name = "log_gmt_create")
    private Date registerTime;

    /**
     * 修改时间
     */
    @Column(name = "log_gmt_modified")
    private Date modifiedTime;

}
