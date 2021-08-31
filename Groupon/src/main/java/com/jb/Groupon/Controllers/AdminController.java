package com.jb.Groupon.Controllers;


import com.jb.Groupon.Beans.Company;
import com.jb.Groupon.Beans.Customer;
import com.jb.Groupon.Exceptions.AdminExceptions;
import com.jb.Groupon.Services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * this class contain admin controller in order to start swagger
 */

@RestController
@RequestMapping("admin") //http://localhost:8080/Admin
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("createCompany") //http://localhost:8080/Admin/createCompany
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCompany(@RequestBody Company company) {
        adminService.addNewCompany(company);
    }

    //Update company
    @PutMapping("updateCompany")//http://localhost:8080/Admin/updateCompany
    @ResponseStatus(code = HttpStatus.OK)
    public void updateCompany(@RequestBody Company company) throws AdminExceptions {
        try{
            adminService.updateCompany(company);
        } catch (AdminExceptions err){
            throw new AdminExceptions("Company is not in the system");
        }
    }


    @DeleteMapping("deleteCompany/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable int id) throws AdminExceptions {
        try {
            adminService.deleteCompany(id);
        } catch (AdminExceptions e) {
            throw new AdminExceptions("Company not found");
        }
    }

    // GET ALL COMPANIES
    @GetMapping("allCompanies") // http://localhost:8080/admin/allCompanies
    public ResponseEntity<?> getAllCompanies(){
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }


    //GET COMPANY BY ID
    @GetMapping("company/{id}") //http://localhost:8080/admin/company?id=randomNum //TODO check how it works on browser
    public ResponseEntity<?> getCompanyById(@PathVariable int id) {
        try {
            Company company = adminService.getCompanyById(id);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (AdminExceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //ADD CUSTOMER
    @PostMapping("createCustomer") //http://localhost:8080/Admin/createCustomer
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) {
        adminService.addNewCustomer(customer);
    }

    //Update CUSTOMER
    @PutMapping("updateCustomer")//http://localhost:8080/Admin/updateCustomer
    @ResponseStatus(code = HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer customer) throws AdminExceptions {
        try{
            adminService.updateCustomer(customer);
        } catch (AdminExceptions err){
            throw new AdminExceptions("Customer is not in the system");
        }
    }

    //delete Customer
    @DeleteMapping("deleteCustomer/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable int id) throws AdminExceptions {
        try {
            adminService.deleteCustomer(id);
        } catch (AdminExceptions e) {
            throw new AdminExceptions("Customer not found");
        }
    }

    //GET ALL CUSTOMERS
    @GetMapping("allCustomers") // http://localhost:8080/admin/allCustomers
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }




}
