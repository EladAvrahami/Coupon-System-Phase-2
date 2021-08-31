package com.jb.Groupon.Services;


import com.jb.Groupon.Art.TablePrinter;
import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Beans.Customer;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Exceptions.CouponException;
import com.jb.Groupon.Exceptions.CustomerExeption;
import com.jb.Groupon.Repositories.CouponRepo;
import com.jb.Groupon.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Scope("prototype")
public class CustomerService implements ClientService {


    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CouponRepo couponRepo;
    /**
     * Obj of customer that login successfully.
     */
    Customer customerLoggedIn;


    /**
     * if customer was found on customerRepo give to customerLoggedIn its value and return true.
     *
     * @param email customer email
     * @param password customer password
     * @return boolean answer true if login success
     */

    @Override
    public boolean login(String email, String password) {
        try {
            if (!((customerRepo.findByEmailAndPassword(email, password)) == null)) {
                customerLoggedIn = customerRepo.findByEmailAndPassword(email, password);
                return true;
            }
        } catch (Exception err) {
            System.out.println("login wrong - please try again" + err.getMessage());
        }
        return false;
    }


    /**
     * before purchase coupon to customer coupons list, check if he is exist,or already bought by customer
     * ,or is in stock,or if coupon date expired?
     *
     * @param couponID the coupon id that customer would like to buy
     * @throws CustomerExeption according to filer
     */
    public void purchaseCoupon(int couponID) throws CustomerExeption {
        try {
            Customer customerLI = (customerRepo.getOne(customerLoggedIn.getId()));
            Coupon couponToPurchase = couponRepo.getOne(couponID);
            //cant purchase the same coupon more than once
            if (customerLI.getCoupons().contains(couponToPurchase)) {
                throw new CouponException("unfortunately you already have this coupon, maybe try to buy something else");
            }
            if (couponToPurchase.getAmount() == 0) {
                throw new CouponException("sorry,not enough in stock");
            }
            if (couponToPurchase.getEndDate().before(new Date())) {
                throw new CouponException("the coupon has expired");
            }
            if (couponToPurchase == null) {
                throw new CouponException("coupon not found");
            } else {
                List<Coupon> coupons = (customerRepo.getOne(customerLoggedIn.getId())).getCoupons();
                coupons.add(couponRepo.getOne(couponID));
                customerLI.setCoupons(coupons);
                customerRepo.saveAndFlush(customerLI);
                couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
                couponRepo.saveAndFlush(couponToPurchase);
                System.out.println("coupon number " + couponID + " was purchased by:" + " customer number " + customerLoggedIn.getId());
            }

        } catch (Exception err) {
            System.out.println("CustomerService.purchaseCoupon: " + err.getMessage());
        }
    }


    /**
     * @return all customer that logged in coupons purchases history
     */
    public List<Coupon> getAllCouponsOfLoginCustomer() {
        return customerRepo.findCustomerById(customerLoggedIn.getId()).getCoupons();
    }



    /**
     * get all companyLoggedIn coupons by category and print as list
     * @param category the common category for all the coupons we want to find
     */
    public void getAllCouponsOfLICustomerByCategory(Category category) {
        try {
            for (Coupon coupon : customerRepo.getOne(customerLoggedIn.getId()).getCoupons()) {
                if (coupon.getCategory() == category)
                    System.out.println(coupon);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }


    /**
     *
     * customer will get all the coupons he purchased up to a certain price he sets
     * shrt for loop check if the coupon price
     * @param maxPrice price customer sets
     */
    public List<Coupon> getAllCouponsByMaxPricePerCustomer( double maxPrice) {
        Customer customerLI = (customerRepo.getOne (customerLoggedIn.getId ()));
        try {
            TablePrinter.print(customerLI.getCoupons());
            return customerLI.getCoupons ();
        } catch (Exception err) {
            System.out.println (err.getMessage ());
        }

        return null;
    }



    /**
     * get all customer that logged in details
     * @return customer logged in details and coupon he was purchased
     * @throws CustomerExeption
     */
    public Customer getAllCustomerDetails()throws CustomerExeption {
        if (!customerRepo.existsById(customerLoggedIn.getId())){
            throw new CustomerExeption("customer not found");
        }
        Customer customerDetails= customerRepo.findById(customerLoggedIn.getId()).get() ;
       // customerDetails.setCoupons(couponRepo.findAllCouponByCustomerID(customerLoggedIn.getId()));
        return customerDetails;
    }





}
