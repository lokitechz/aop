package vn.com.msb.aop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AccountID")
    private Integer accountId;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username")
    private String userName;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "DepartmentID")
    private Integer departmentId;

    @Column(name = "PositionID")
    private Integer positionId;

    @Column(name = "CreateDate")
    private Date createDate;

}
