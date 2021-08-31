package com.jb.Groupon.Repositories;

import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Company repository help us get data about coupons using the 50% 40% or 10%
 */
public interface CouponRepo extends JpaRepository<Coupon,Integer> {

    Coupon findByTitleAndCompanyID (String title ,Integer companyID);
    List<Coupon> findAllCouponsByCompanyIDAndCategory(int companyID, Category category);

   List<Coupon> findAllCouponByCompanyID(int companyID );

    //List<Coupon> findAllCouponByCustomerID(int customerID );


   List<Coupon> findAllCouponsByCompanyIDAndPriceLessThanEqual(int companyID, double price);



}
