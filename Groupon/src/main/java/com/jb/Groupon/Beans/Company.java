package com.jb.Groupon.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class contain all the parameters company obj should have
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(updatable = false, length = 40)
    private String name;
    @Column(updatable = false)
    private String email;
    @Column(updatable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)//, orphanRemoval = true ,mappedBy = "companyID"
    private List <Coupon> coupons = new ArrayList<>();

    /**
     * i generate new toSting in order to print companies lists nicer with line drop.
     * @return print Obj company with a line drop
     */
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}'+"\n";
    }
}
