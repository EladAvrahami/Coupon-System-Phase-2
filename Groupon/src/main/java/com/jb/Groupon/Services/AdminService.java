package com.jb.Groupon.Services;

import com.jb.Groupon.Beans.Company;
import com.jb.Groupon.Beans.Customer;
import com.jb.Groupon.Exceptions.AdminExceptions;
import com.jb.Groupon.Repositories.CompanyRepo;
import com.jb.Groupon.Repositories.CouponRepo;
import com.jb.Groupon.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements ClientService{
    /**
     * we will us autowired annotation to get data from the different repositories
     */
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    CustomerRepo customerRepo;

    /**
     * hard-coded admin e-mail and password
     * email check used by "IgnoreCase"
     */
    private final String adminEmail="admin@gmail.com";
    private final String adminPass="admin123";


    /**
     * boolean method that check if the email and password user entered equal to the hard coded one that we choose above
     * @param email admin email (used ignore case)
     * @param password admin password
     * @return
     */
    @Override
    public boolean login(String email, String password) {
        if (email.equalsIgnoreCase(adminEmail) && password.equals(adminPass) ){
            return true;
        }
        return false;

    }

    /**
     * check if there isn't company with the same name or email or that the id not already exists(can happen when there are?  )
     *if the both conditions =true save the company on company Repo
     * @param company the new company admin want to add using builder on test
     */

   public void addNewCompany(Company company){
        try {
            if ((companyRepo.findByNameOrEmail(company.getName(),company.getEmail())) == null &&
                    (!companyRepo.existsById(company.getId ())))
                companyRepo.saveAndFlush(company);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

/*
    public boolean addNewCompany(Company company)  {

        //double check so that we won't add a double company by name or ID
        Company retObject = null;
        try {
            if (!companyRepo.existsById (company.getId ()) &&
                    companyRepo.findByNameOrEmail (company.getName (), company.getEmail ()) == null) {
                retObject = companyRepo.save (company);
                companyRepo.save (company);
                System.out.println ("new company number " + company.getId () + " was added");
                retObject.getId (); // get id here
                return true;
            } else {
                System.out.println ("cant add company since it already exists");
            }
        } catch (Exception e) {
            System.out.println ("AdminService.addCompany: + " + e.getMessage ());
        }
        return false;
    }
*/

    /**
     * check if the id of the company admin want to update found on companyRepo=true it will let u set again company with different
     * values (except the values we set on the been as @Column(updatable = false))
     * @param company the company we would like to update
     * @throws AdminExceptions
     */
    public void updateCompany(Company company) throws AdminExceptions {
        if (companyRepo.existsById (company.getId ())) {
            companyRepo.save(company);
            System.out.println ("company number : " + company.getId () + " was updated");
        } else {
            System.out.println ("company number : " + company.getId () + " cannot be updated or it does not exist");
        }
    }



    /**
     * check if company exist using the power of JpaRepository and delete it and its coupons
     * @param id get id of the company admin would like to delete
     * @throws AdminExceptions
     */
    public void deleteCompany(int id) throws AdminExceptions {
        try {
            if (!companyRepo.existsById(id))
            {
                throw new AdminExceptions("Company not found");
            }
            companyRepo.deleteById(id);
            couponRepo.deleteById(id);//DELETE ALL Company coupons too
        } catch (AdminExceptions adminExceptions) {
            System.out.println(adminExceptions.getMessage());
        }

    }

    /**
     *
     * @return all companies that on DB using company repository
     */
    public List<Company> getAllCompanies(){return companyRepo.findAll();}


    /**
     * get specific company by id if it exists
     * @param id of specific company admin wants to see
     * @return all company details
     * @throws AdminExceptions
     */
    public Company getCompanyById(int id) throws AdminExceptions {
        if (!(companyRepo.existsById(id))){
            throw new AdminExceptions("Company not found");
        }
        return companyRepo.getOne(id);
    }

    /**
     * check if there isn't customer with the same email on repo
     * or that the id not already exists on customer Repo
     *if the both conditions =true save the company on company Repo
     * @param customer the new customer admin want to add using builder on test
     */
    public void addNewCustomer(Customer customer){
        try {
            if ((customerRepo.findByEmail(customer.getEmail()) == null) &&
                    (!customerRepo.existsById(customer.getId ())));
                customerRepo.save(customer);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * check if the id of the company admin want to update found on customerRepo=true it will let u build again customer withe different
     * values (except the values we set on the customer been as @Column(updatable = false))
     * @param customer the customer we would like to update
     * @throws AdminExceptions
     */

    public void updateCustomer(Customer customer) throws AdminExceptions {
        try {
            if (!customerRepo.existsById(customer.getId())){
                throw new AdminExceptions("Customer ID not found");
        }
            customerRepo.saveAndFlush(customer);
        } catch (AdminExceptions adminExceptions) {
            System.out.println(adminExceptions);
        }
    }



    /**
     *  check if customer exist using the power of JpaRepository and delete it if exists
     * @param id get id of the company admin would like to delete
     * @throws AdminExceptions
     */
    public void deleteCustomer(int id) throws AdminExceptions {
        if (!customerRepo.existsById(id)){
            throw new AdminExceptions("Customer not found");
        }
        customerRepo.deleteById(id);
        couponRepo.deleteById(id);//TODO לטפל בנפילה במידה ואין קופונים ללקוח למחוק
    }


    /**
     * get all customers that on DB using company repository
     * @return
     */
    public List<Customer> getAllCustomers(){return customerRepo.findAll();}


    /**
     * get specific customer details by id if it exists
     * @param id of specific company admin wants to see
     * @return all customer details
     * @throws AdminExceptions
     */
    public Customer getCustomerById(int id) throws AdminExceptions {
        if (!(customerRepo.existsById(id))){
            throw new AdminExceptions("Customer not found");
        }
        return customerRepo.getOne(id);
    }



}
