package cn.xujian.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
//指定关联的表
@Table(name = "tb_user")
public class User {
    @Id
//    主键回填
    @KeySql(useGeneratedKeys = true)
    private Long id;
//    用于对象属性名和数据库属性名不一致时指定映射。
    @Column(name = "user_name")
    private String user_name;

    private String password;

    private String name;

    private Integer age;

    private Integer sex;

    private Date birthday;

    private String note;

    private Date created;

    private Date updated;

    public User( String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public User() {
    }
}
