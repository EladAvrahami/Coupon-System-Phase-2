package com.jb.Groupon.Repositories;

import com.jb.Groupon.Beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Company repository help us get data about the company using the 50% 40% or 10%
 */
public interface CompanyRepo extends JpaRepository <Company,Integer>{
    Company findByEmailAndPassword(String email, String password);

    Company findByNameOrEmail(String name, String email);
    Company findCompanyById(int id);





}
