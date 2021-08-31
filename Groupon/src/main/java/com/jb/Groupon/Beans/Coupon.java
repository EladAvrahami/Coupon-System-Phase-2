package com.jb.Groupon.Beans;
import com.jb.Groupon.Enums.Category;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * this class contain all the parameters coupon obj should have
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Coupon {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(updatable = false)
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
    //@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
   // @JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
//	@ManyToMany(mappedBy = "coupons")
    ///@Singular
    //@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    //private List<Customer> customers;


    /**
     * i generate new toSting in order to print coupons lists nicer
     * @return print Obj coupon with a line drop
     */
    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", companyID=" + companyID +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}'+"\n";
    }
}
