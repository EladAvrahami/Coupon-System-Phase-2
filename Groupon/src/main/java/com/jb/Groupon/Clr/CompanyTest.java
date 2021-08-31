package com.jb.Groupon.Clr;

import com.jb.Groupon.Art.MyUtil;
import com.jb.Groupon.Art.PrintNiceMassage;
import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Enums.ClientType;
import com.jb.Groupon.Exceptions.LoginExeption;
import com.jb.Groupon.Repositories.CouponRepo;
import com.jb.Groupon.Services.CompanyService;
import com.jb.Groupon.Services.CustomerService;
import com.jb.Groupon.Services.LoginManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(2)
@RequiredArgsConstructor
@Data
public class CompanyTest implements CommandLineRunner {

    private CompanyService companyService;
    private final LoginManager loginManager;
    private final CouponRepo couponRepo;

    @Override
    public void run(String... args) throws Exception {

        //*********************START COMPANY SERVICE TEST******************************

        System.out.println(PrintNiceMassage.COMPANY_ART);

        System.out.println("LOGIN AS COMPANY FAILED BY EMAIL");
        try {
            CompanyService companyService = (CompanyService) loginManager.login("elad35@gmail3", "elad1234", ClientType.company);

        } catch (LoginExeption loginExeption) {
            System.out.println(loginExeption.getMessage());
        }


        System.out.println("======LOGIN AS CUSTOMER SUCCESSFULLY======");
        CompanyService companyService = (CompanyService) loginManager.login("elad35@gmail", "elad1234", ClientType.company);
        MyUtil.printRow();
        System.out.println("  ========HELO " +companyService.getCompanyLoggedIn().getName()+"!!!  ==========");
        MyUtil.printRow();

        System.out.println("======ADD NEW COUPON======");

            Coupon coupon6 = Coupon.builder()
                    .companyID(3)//air
                    .title("bom bom")
                    .description("beautiful world")
                    .startDate(Date.valueOf("2021-06-13"))
                    .endDate(Date.valueOf("2022-06-13"))
                    .amount(100)
                    .price(85.5)
                    .image("beautifulWorld.img")
                    .category(Category.vacation)
                    .build();
            companyService.addNewCoupon(coupon6);
            System.out.println(coupon6);
        MyUtil.printRow();


        System.out.println("======ADD NEW COUPON FAILED======"); //TODO exception

            Coupon coupon220=Coupon.builder()
                    .companyID(3)
                    .title("bom bom")
                    .description("beautiful world")
                    .startDate(Date.valueOf("2021-06-13"))
                    .endDate(Date.valueOf("2022-06-13"))
                    .amount(100)
                    .price(85.5)
                    .image("World.img")
                    .category(Category.vacation)
                    .build();
            companyService.addNewCoupon(coupon220);
            System.out.println(coupon220);



        System.out.println("==============update coupon=============");

        Coupon anyCoupon = couponRepo.getOne(5);
        anyCoupon.setAmount(7);
        //anyCoupon.setCompanyID (2);
        anyCoupon.setDescription("50% for the second drone");
        anyCoupon.setStartDate(Date.valueOf("2021-05-05"));
        anyCoupon.setEndDate(Date.valueOf("2022-08-05"));
        anyCoupon.setImage("drone.png");
        anyCoupon.setCategory(Category.electricity);
        anyCoupon.setPrice(150.00);
        anyCoupon.setTitle("WOW plus");
        System.out.println(companyService.getCompanyLoggedIn());
        companyService.updateCoupon(anyCoupon);

        System.out.println(anyCoupon);



        System.out.println("==============update coupon NOT SUCCESSFULLY=============");//TODO exception//BOOOOOOOOOOOMMMMM

        Coupon anyCoupon1=couponRepo.getOne(4);
        anyCoupon.setAmount(7);
        //anyCoupon.setCompanyID (2);
        anyCoupon.setDescription ("50% for the second drone");
        anyCoupon.setStartDate (Date.valueOf ("2021-05-05"));
        anyCoupon.setEndDate (Date.valueOf ("2022-08-05"));
        anyCoupon.setImage ("drone.png");
        anyCoupon.setCategory (Category.electricity);
        anyCoupon.setPrice (1500.00);
        anyCoupon.setTitle ("WOW VAHECHI");
        companyService.updateCoupon(anyCoupon1);
        System.out.println(anyCoupon1);


        

        System.out.println("============All Company COUPONS BEFORE DELETE ============");
        System.out.println(companyService.getAllCouponsOfLoginCompany());
/*
        System.out.println("============DELETE COUPON=============");
        companyService.deleteCoupon(1);
        System.out.println("Company Coupons after delete:\n" + companyService.getAllCouponsOfLoginCompany());
*/


/*
        System.out.println("============DELETE COUPON FAILED=============");
        companyService.deleteCoupon(3);
       */




       System.out.println("=====================GET ALL COMPANY COUPONS===========");
        System.out.println(companyService.getAllCouponsOfLoginCompany());



        // System.out.println("===================== FAILED GET ALL COMPANY COUPONS===========");//TODO check if fall ?
        //NO NEED TO CHECK (WORK ONLY AFTER CORRECT LOGIN)



/*
        System.out.println("=========GET ALL COMPANY COUPONS BY CATEGORY==========");
        System.out.println(companyService.getAllCouponsByCategoryPerCompany(Category.food));
*/


        //  System.out.println("=========FAILED GET ALL COMPANY COUPONS BY CATEGORY==========");
        //   if (companyService.getAllCouponsByCategoryPerCompany(Category.electricity)==null){//TODO exeption coupon not found by category
        //      System.out.println("");


/*
        //https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods.query-creation
        System.out.println("=========GET ALL COMPANY COUPONS LESS OR EQUALS TO PRICE==========");
        System.out.println(companyService.findAllCouponsLowerOrEqlThanPrice(434));

 */



        System.out.println("========GET ALL COMPANY DETAILS===========");
        System.out.println(companyService.getAllCompanyDetails());





    }
}
