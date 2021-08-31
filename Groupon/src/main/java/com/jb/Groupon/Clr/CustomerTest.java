package com.jb.Groupon.Clr;

import com.jb.Groupon.Art.MyUtil;
import com.jb.Groupon.Art.PrintNiceMassage;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Enums.ClientType;
import com.jb.Groupon.Exceptions.LoginExeption;
import com.jb.Groupon.Services.CustomerService;
import com.jb.Groupon.Services.LoginManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@RequiredArgsConstructor
@Data
public class CustomerTest implements CommandLineRunner {

    // private CustomerService customerService;
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(PrintNiceMassage.CUSTOMER_ART);


        System.out.println("LOGIN AS CUSTOMER FAILED BY PASSWORD");
        try {
            CustomerService customerService = (CustomerService) loginManager.login("shto@walla.com", "drone123", ClientType.customer);

        } catch (LoginExeption loginExeption) {
            System.out.println(loginExeption.getMessage());
        }


        System.out.println("LOGIN AS CUSTOMER FAILED BY EMAIL");
        try {
            CustomerService customerService = (CustomerService) loginManager.login("hto@walla.com", "drone1234", ClientType.customer);

        } catch (LoginExeption loginExeption) {
            System.out.println(loginExeption.getMessage());
        }



      System.out.println("======LOGIN AS CUSTOMER SUCCESSFULLY======");
    CustomerService customerService = (CustomerService) loginManager.login("shlomi@walla.com", "shlomi1234", ClientType.customer);



        System.out.println("======PURCHASE COUPON SUCCESSFULLY");
        customerService.purchaseCoupon(2);
        customerService.purchaseCoupon(4);






        /*
        System.out.println("======PURCHASE COUPON FAILED BY COUPON NOT EXIST======");////TODO exeption
        customerService.purchaseCoupon(1);
*/
/*
        System.out.println("======PURCHASE COUPON FAILED BY COUPON ALREADY PURCHASE====== ");
        customerService.purchaseCoupon(2);//will work only if coupon 2 already purchased
 */
/*
        System.out.println("======PURCHASE COUPON FAILED BY COUPON AMOUNT===== ");
        customerService.purchaseCoupon(3);

*/
        /*
        System.out.println("======PURCHASE COUPON FAILED BY COUPON DATE EXPIRED===== ");
        customerService.purchaseCoupon(1);
         */

/*
        System.out.println("======GET ALL CUSTOMER COUPONS=====");
       System.out.println( customerService.getAllCouponsOfLoginCustomer());
*/

        System.out.println("====GET ALL CUSTOMER COUPONS BY CATEGORY");
        customerService.getAllCouponsOfLICustomerByCategory(Category.food);


        //System.out.println("====GET ALL CUSTOMER COUPONS BY CATEGORY FAILED");//TODO exeptions
        //customerService.getAllCouponsOfLICustomerByCategory(Category.flights);


        System.out.println("GET ALL COUPONS CUSTOMER BUY UP TO X PRICE ");
        MyUtil.printRow();
        customerService.getAllCouponsByMaxPricePerCustomer(434);

        //System.out.println("GET ALL COUPONS CUSTOMER BUY UP TO X PRICE NOT FOUND ");//TODO exeptions for all coupon not found
        //customerService.getAllCouponsByMaxPricePerCustomer(50);

        System.out.println("GET ALL CUSTOMER LI DETAILS");
        System.out.println(customerService.getAllCustomerDetails());




    }


}
