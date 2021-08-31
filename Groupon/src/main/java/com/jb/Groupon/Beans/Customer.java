package com.jb.Groupon.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * this class contain all the parameters customer obj should have
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(length = 40,nullable = false)
    private String firstName;
    @Column(length = 40,nullable = false)
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Coupon> coupons;


    /**
     * i generate new toSting in order to print Customer lists nicer
     * @return print Obj customer with a line drop
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}'+"\n";
    }


    /*
    //many customers can have many coupons
    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private List<Coupon> coupons;
*/
}
