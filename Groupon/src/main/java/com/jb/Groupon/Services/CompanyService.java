package com.jb.Groupon.Services;

import com.jb.Groupon.Beans.Company;
import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Exceptions.CompanyException;
import com.jb.Groupon.Repositories.CompanyRepo;
import com.jb.Groupon.Repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Scope("prototype")
public class CompanyService implements ClientService{

    @Autowired
    CouponRepo couponRepo;
    @Autowired
    CompanyRepo companyRepo;
    /**
     * Obj of company that login successfully.
     */
    Company companyLoggedIn;


    /**
     * @return companyLoggedIn will get all the company details from login method
     */
    public Company getCompanyLoggedIn() {
        return companyLoggedIn;
    }

    /**
     * boolean method hat check if company was found on companyRepo give to companyLoggedIn its value and return true.
     * @param email company Obj email
     * @param password company Obj password
     * @return boolean answer true if login success
     */
    @Override
    public boolean login(String email, String password) {
            try {
                if (!((companyRepo.findByEmailAndPassword (email, password)) ==null)) {
                    companyLoggedIn = companyRepo.findByEmailAndPassword (email, password);
                    return true;
                }
            } catch (Exception err) {
                System.out.println ("login wrong - please try again" + err.getMessage ());
            }
            return false;
        }

/*
    public boolean isLoggedIn() {
            if (companyLoggedIn != null) {
                //  Throw Login EXCEPTION Comapny no loggedin
                // add this condition to each method for better security?
            return false;
            }
            return true;
    }

 */


    /**
     * check if there isn't a coupon with the same title and company id in all DB save the coupon
     * @param coupon new obj of company coupon
     */
    public void addNewCoupon(Coupon coupon){
       // isLoggedIn();
        try {
            if ((couponRepo.findByTitleAndCompanyID(coupon.getTitle(),coupon.getCompanyID())) == null &&
                    (!couponRepo.existsById(coupon.getId ())))
                couponRepo.save(coupon);
        } catch (Exception err) {
            System.out.println("coupon title already exist");
        }
    }



    /**
     * check if the coupon exists on repository ,check if the coupon companyID=companyLI id (and check if login)
     * @param coupon obj of company coupon in order to use builder and update its values
     * @throws CompanyException
     */
    public void updateCoupon(Coupon coupon) throws CompanyException{
       try {
           if ((!couponRepo.existsById(coupon.getId())) || (companyLoggedIn.getId()!=couponRepo.getOne(coupon.getId()).getCompanyID())){
               throw new  CompanyException("Coupon ID not found");
           }
           couponRepo.saveAndFlush(coupon);
       } catch (Exception err) {
           System.out.println(err.getMessage());
           //System.err.println(err.getMessage());//will be mark with the color red
       }
    }


    /**
     * check if coupon not exists by id or if the id of the company logged in is not equal to the id of the companyID value
     * of the coupon that we want to delete print coupon not found ,else delete the coupon-
     * we will do this in order to not to give any company the power to change other coupon on our system.
     * @param couponId the id of the coupon i want to delete
     * @throws CompanyException
     */
    public void deleteCoupon(int couponId) throws CompanyException {

            if ((!couponRepo.existsById(couponId)) || (companyLoggedIn.getId() != couponRepo.getOne(couponId).getCompanyID())) {
                throw new CompanyException("coupon not found");
            }
            couponRepo.deleteById(couponId);
    }


    /**
     *
     * @return all the coupons of the company that logged in
     */
    public List<Coupon> getAllCouponsOfLoginCompany(){
//        List<Coupon> list = new ArrayList<>();
//        list = couponRepo.findAllCouponByCompanyID(companyLoggedIn.getId());
        return couponRepo.findAllCouponByCompanyID(companyLoggedIn.getId());
    }


    /**
     *
     * @param category of the coupons  category type we would like to see
     * @return all the coupon from the same category type
     */
    public List<Coupon> getAllCouponsByCategoryPerCompany(Category category) {
        return couponRepo.findAllCouponsByCompanyIDAndCategory (companyLoggedIn.getId(), category);
    }


    /**
     * get all company coupons until max price
     * @param price is the coupon heights price that coupons will be suggested for the client of the company
     * @return list of all the company (that did login) coupons until max price that was chosen by user
     * we got help from: https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods.query-creation
     */
    public List<Coupon>findAllCouponsLowerOrEqlThanPrice(double price){
        return couponRepo.findAllCouponsByCompanyIDAndPriceLessThanEqual(companyLoggedIn.getId(),price);
    }


    /**
     * if companyLoggedIn not exists in company repo return not found
     * else we create new variable Company typ called companyDetails and use 50% company repository power to get all company details
     * than we will set companyDetails coupon by get them from coupon repo power
     * @return company obj with company and its coupon list
     * @throws CompanyException
     */
    public Company getAllCompanyDetails()throws CompanyException{
        if (!companyRepo.existsById(companyLoggedIn.getId())){
            throw new CompanyException("Company not found");
        }
       Company companyDetails= companyRepo.findById(companyLoggedIn.getId()).get() ;
        companyDetails.setCoupons(couponRepo.findAllCouponByCompanyID(companyLoggedIn.getId()));
        return companyDetails;
    }




}
