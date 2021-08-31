package com.jb.Groupon.Repositories;

import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Company repository help us get data about the customer using the 50% 40% or 10%
 */
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Customer findByEmailAndPassword(String email, String password);

    List<Customer>  findByEmail(String email);
    Customer findCustomerById(int id);

}
