package com.nwpu.rocket.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;


/**
 * 用户实体类
 *
 * @author zy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {
    /**
     * 序列版本号
     */
    private static final long serialVersionUID = 1L;


    /**
     * 用户id，自增
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    @JsonIgnore
    private List<SysLog> logList;

    /**
     * 用户账号
     */
    @Excel(name="账号")
    @Column(name = "user_account")
    private String account;

    /**
     * 用户密码
     */
    @JsonIgnore
    @Column(name = "user_password")
    private String password;

    /**
     * 明文密码
     */
    @Column(name = "user_password_clear_text")
    @Excel(name="明文密码")
    private String passwordClearText;
    /**
     * 用户角色（权限）
     */
    @Column(name = "user_role")
    private String roles;

    /**
     * 用户名
     */
    @Excel(name="姓名")
    @Column(name = "user_name")
    private String name;

    /**
     * 用户的电话
     */
    @Excel(name="电话")
    @Column(name = "user_phone")
    private String phone;

    /**
     * 用户的email
     */
    @Excel(name="邮箱")
    @Column(name = "user_email")
    private String email;

    /**
     * 用户状态是否被锁定，数据库默认1，可用
     */
    @Column(name = "user_status")
    private Integer status;

    /**
     * 该用户是否有效，数据库默认1，有效
     */
    @Column(name = "user_is_enabled")
    private Integer enabled;

    /**
     * 用户注册时间
     */
    @Column(name = "user_gmt_create")
    private Date registerTime;

    /**
     * 用户注册时间
     */
    @Column(name = "user_gmt_modified")
    private Date modifiedTime;




    /**
     * 用户状态：启用
     */
    @Transient
    public static final int STATUS_ON = 1;


    /**
     * 用户状态：关闭
     */
    @Transient
    public static final int STATUS_OFF = 0;

    /**
     * 用户角色：学校
     */
    @Transient
    public static final String ROLE_SCHOOL = "ROLE_SCHOOL";

    /**
     * 用户角色：申请者
     */
    @Transient
    public static final String ROLE_APPLICANT = "ROLE_APPLICANT";

    /**
     * 用户角色：管理员
     */
    @Transient
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 用户角色：专家
     */
    @Transient
    public static final String ROLE_EXPERT = "ROLE_EXPERT";
}