package com.jb.Groupon.Controllers;

import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Enums.Category;
import com.jb.Groupon.Exceptions.CompanyException;
import com.jb.Groupon.Services.AdminService;
import com.jb.Groupon.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coupons3") //http://locahost:8080/coupons
@RequiredArgsConstructor
/**
 * a class that contains the methods of the Company Controller Rest Api (post get Put Delete)
 */
public class CompanyController {

    private final CompanyService companyService;
    private final AdminService adminService;

    @PostMapping("addCoupon")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCoupon(@RequestBody Coupon coupon) throws CompanyException {
        companyService.addNewCoupon (coupon);
    }

    @DeleteMapping("deleteCoupon/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable int id)throws CompanyException {
        companyService.deleteCoupon (id);
        System.out.println("Coupon " + id + " was deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("updateCoupon")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) throws CompanyException {
        try {
            companyService.updateCoupon (coupon);
        } catch (CompanyException e) {
            //return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
            throw new CompanyException ("Data was already reported as updated");
        }
    }

    //GET ALL COUPONS PER COMPANY
    @GetMapping("getCouponsByCompany") // http://localhost:8080/search?id=1
    public ResponseEntity<?> getCouponsByCompany(){
        return ResponseEntity.ok (companyService.getAllCouponsOfLoginCompany ());
    }
    //GET ALL COUPONS PER COMPANY BY CATEGORY
    @GetMapping("getCouponsByCategory") // http://localhost:8080/search?id=1
    public ResponseEntity<?> getCouponsByCategory(@RequestParam Category category){
        return ResponseEntity.ok (companyService.getAllCouponsByCategoryPerCompany(category));
    }
    //GET ALL COUPONS PER COMPANY BY MAX PRICE
    @GetMapping("getCouponsByMaxPrice") // http://localhost:8080/search?id=1
    public ResponseEntity<?> getCouponsByMaxPrice(@RequestParam  int maxPrice){
        return ResponseEntity.ok (companyService.findAllCouponsLowerOrEqlThanPrice (maxPrice));
    }
}


