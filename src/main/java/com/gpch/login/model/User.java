package com.gpch.login.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "비밀번호는 최소 5글자여야 합니다.")
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "성을 입력해주세요")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
//
//    @OneToMany
//    @JoinTable(name = "board", joinColumns = @JoinColumn(name = "board_id"), inverseJoinColumns = @JoinColumn(name = "email"))
//    private List<Board> boardList;

}
