package com.jb.Groupon.Clr;

import com.jb.Groupon.Art.MyUtil;
import com.jb.Groupon.Art.PrintNiceMassage;
import com.jb.Groupon.Beans.Company;
import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Beans.Customer;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Enums.ClientType;
import com.jb.Groupon.Exceptions.AdminExceptions;
import com.jb.Groupon.Exceptions.LoginExeption;
import com.jb.Groupon.Repositories.CompanyRepo;
import com.jb.Groupon.Repositories.CouponRepo;
import com.jb.Groupon.Repositories.CustomerRepo;
import com.jb.Groupon.Services.AdminService;
import com.jb.Groupon.Services.CompanyService;
//import com.jb.Groupon.Services.CustomerService;
import com.jb.Groupon.Services.CustomerService;
import com.jb.Groupon.Services.LoginManager;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;

/**
 * on the CLR we will test all our program
 */
@Component
@Order(1)
@RequiredArgsConstructor
@Data
public class AdminTest implements CommandLineRunner {

    private final AdminService adminService;
    //private CompanyService companyService;
   // private CustomerService customerService;
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;
    private final CustomerRepo customerRepo;
    private final LoginManager loginManager;



    @Override
    public void run(String... args) throws Exception {

        Coupon coupon1 = Coupon.builder()
                .title("yam yam")
                .price(434.00)
                .image("dfdfa")
                .category(Category.food)
                .companyID(1)
                .startDate(Date.valueOf("2021-06-07"))
                .endDate(Date.valueOf("2022-2-07"))
                .amount(8)
                .description("delicious!!")
                .build();
        couponRepo.save(coupon1);

        Coupon coupon2 = Coupon.builder()
                .description("nice hotel")
                .amount(0)
                .startDate(Date.valueOf("2021-06-07"))
                .endDate(Date.valueOf("2022-09-07"))
                .companyID(2)
                .image("image")
                .price(2500.00)
                .title("Agamim Hotel")
                .category(Category.vacation)
                .build();
        couponRepo.save(coupon2);

        Coupon coupon3 = Coupon.builder()
                .description("nice hotel")
                .amount(0)
                .startDate(Date.valueOf("2021-06-07"))
                .endDate(Date.valueOf("2022-09-07"))
                .companyID(2)
                .image("image")
                .price(2500.00)
                .title("AMAZON")
                .category(Category.vacation)
                .build();
        couponRepo.save(coupon3);

        Coupon coupon4 = Coupon.builder()
                .description("nice hotel")
                .amount(6)
                .startDate(Date.valueOf("2021-06-07"))
                .endDate(Date.valueOf("2022-09-07"))
                .companyID(2)
                .image("image")
                .price(2500.00)
                .title("BRRRRRRRRRRRR")
                .category(Category.vacation)
                .build();
        couponRepo.save(coupon4);

        Coupon coupon5 = Coupon.builder()
                .amount(7)
                .companyID(1)
                .description("WOW")
                .startDate(Date.valueOf("2021-05-05"))
                .endDate(Date.valueOf("2021-06-05"))
                .image("ggg")
                .category(Category.clothes)
                .price(34.00)
                .title("WOW VAHECHI")
                .build();
       couponRepo.save(coupon5);










       System.out.println("======== admin login not successfully by password ======");
        try {
        AdminService adminService = (AdminService) loginManager.login ("admin@gmail.com", "admin124", ClientType.admin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        MyUtil.printRow();

        System.out.println("======== admin login not successfully by email ======");
        try {
            AdminService adminService = (AdminService) loginManager.login ("admin@gmail", "admin123", ClientType.admin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        MyUtil.printRow();

        System.out.println("======== admin login successfully======");
        AdminService adminService = (AdminService) loginManager.login("admin@gmail.com", "admin123", ClientType.admin);
        MyUtil.printRow();

        //("====================ADMIN SERVICE ACTIONS: ===============================")
        System.out.println(PrintNiceMassage.ADMIN_ART);


        System.out.println("======ADD COMPANY SUCCESSFULLY=====");
        Company company1 = Company.builder()
                .name("NADAcompany")
                .email("NADAcompany@gmail")
                .password("NADA1234")
                .build();
        adminService.addNewCompany(company1);
        System.out.println(company1);

        Company company2 = Company.builder()
                .name("ChenCompany")
                .email("CHEN35@gmail")
                .password("CHEN1234")
                .build();
        adminService.addNewCompany(company2);
        System.out.println(company2);

        Company company3 = Company.builder()
                .name("Aeronautics Ltd")
                .email("Aeronautics@gmail.com")
                .password("Aeronautics123")
                .build();
        adminService.addNewCompany(company3);
        System.out.println(company3);
        MyUtil.printRow();




      System.out.println("======ADD COMPANY NOT SUCCESSFULLY- COMPANY ALREADY EXIST=====");
        Company company40=Company.builder()
                .name("eladCompany")
                .email("elad35@gmail")
                .password("elad1234")
                .coupons(Arrays.asList())
                .build();
        adminService.addNewCompany(company40);
        MyUtil.printRow();


       /* System.out.println("======update company======");//TODO WHAY NOT CHANGE ?
        Company companyUpdate=companyRepo.getOne(2);
        System.out.println("before changes:\n"+companyUpdate);
        companyUpdate.setEmail("king@gmail");
        companyUpdate.setPassword("123345");
        //companyUpdate.setCoupons(Arrays.asList());
        adminService.updateCompany(companyUpdate);
        companyUpdate=companyRepo.getOne(2);
        System.out.println("after changes:\n"+companyUpdate);
        MyUtil.printRow();
        */

        System.out.println("======COMPANY UPDATE - SUCCESSFUL======");//TODO WHAY NOT WORKING
        Company company=companyRepo.getOne(2);
        System.out.println (" this is a demonstration of an admin updating a company's email and password - successfully");
        System.out.println("before changes:\n"+company);
        company.setEmail ("test@walla.com");
        company.setPassword ("test new password");
        System.out.println("after changes:\n"+adminService.getCompanyById(2));
        MyUtil.printRow ();

        System.out.println("COMPANY UPDATE -  NOT SUCCESSFUL (BY ID)");
       // System.out.println (" this is a demonstration of an admin trying to update a company's id -  not successfully");
        company.setId (6);
        adminService.updateCompany (company);
        company.setId (1);
        MyUtil.printRow ();

        System.out.println("COMPANY UPDATE -  NOT SUCCESSFUL (BY NAME)");
       // System.out.println (" this is a demonstration of an admin trying to update a company's name -  not successfully");
        System.out.println ("this is the company before the change:");
        adminService.getCompanyById (company.getId ());
        company.setName ("name test");
        adminService.updateCompany (company);
        System.out.println ("this is the company after the change: you can see the name stayed the same");
        adminService.getCompanyById (company.getId ());
        MyUtil.printRow ();


        //System.out.println(adminService.getCompanyById(1));
        //System.out.println(couponRepo.getOne(1));



        System.out.println("======delete company======");
      // ************************ how to delete also all coupons the company create& customer purchased
       adminService.deleteCompany(1);
        MyUtil.printRow();


        //System.out.println(couponRepo.getOne(1)); //HERE JUST TO CHECK IF COUPONS DELETED TOO ...



        System.out.println("======delete company NOT SUCCESSFULLY======");
        adminService.deleteCompany(10);
         MyUtil.printRow();



        System.out.println("======get all companies======");
        System.out.println(adminService.getAllCompanies());
        MyUtil.printRow();



        System.out.println("======get company by id======");
        System.out.println(adminService.getCompanyById(2));
        MyUtil.printRow();




        System.out.println("=======ADD NEW CUSTOMERS======");
        Customer customer1 = Customer.builder()

                .firstName("Zeev")
                .lastName("Mindali")
                .email("zeev@walla.com")
                .password("zeevdrone1234")
                .build();
        adminService.addNewCustomer(customer1);
        System.out.println(customer1+"was added !");


        Customer customer2 = Customer.builder()
                .firstName("shlomi")
                .lastName("blabla")
                .email("shlomi@walla.com")
                .password("shlomi1234")
                .build();
        adminService.addNewCustomer(customer2);
        System.out.println(customer2 +"was added !");

        Customer customer3 = Customer.builder()
                .firstName("YARON")
                .lastName("ALON")
                .email("ALON@walla.com")
                .password("ALON1234")
                .build();
        adminService.addNewCustomer(customer3);
        System.out.println(customer3 +"was added !");
        MyUtil.printRow();


        System.out.println("======update CUSTOMER======");
        Customer anyCustomer = customerRepo.getOne(1);
        System.out.println(" before changes:\n"+anyCustomer);
        anyCustomer.setFirstName("ZEEV");
        anyCustomer.setLastName("the fox");
        anyCustomer.setEmail("zeev@gmail.com");
        anyCustomer.setPassword("drone1234");
        adminService.updateCustomer(anyCustomer);
        System.out.println("after changes:\n"+ anyCustomer);
        MyUtil.printRow();





/*
        System.out.println(" Faild update CUSTOMER -customer not found");//TODO EXEPTION
        Customer anyCustomer2=customerRepo.getOne(10);
        anyCustomer.setFirstName("ZEEV");
        anyCustomer.setLastName("the fox");
        anyCustomer.setEmail("king@gmail");
        anyCustomer.setPassword("123345");
        adminService.updateCustomer(anyCustomer2);
        System.out.println(anyCustomer2);

 */




       System.out.println("DELETE CUSTOMER");//TODO need to check that all customer coupon purch deleted too
            adminService.deleteCustomer(1);
            System.out.println("Customer deleted successfully ");



        System.out.println("======DELETE CUSTOMER NOT SUCCESSFULLY======");
        try {
            adminService.deleteCustomer(10);
        } catch (AdminExceptions adminExceptions) {
            System.out.println(adminExceptions.getMessage());
        }
        MyUtil.printRow();


        System.out.println("======GET ALL CUSTOMERS======");
        System.out.println(adminService.getAllCustomers());



        System.out.println("======GET CUSTOMER BY ID ======");
        System.out.println( adminService.getCustomerById(2));


        System.out.println("======GET CUSTOMER BY ID -FAILED BY COMPANY NOT FOUND ======");

        try {
            System.out.println( adminService.getCustomerById(50));
        } catch (AdminExceptions adminExceptions) {
            System.err.println(adminExceptions.getMessage());
        }


}
}
