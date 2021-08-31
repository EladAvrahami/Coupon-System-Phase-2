package com.jb.Groupon.Services;
import com.jb.Groupon.Enums.ClientType;
import com.jb.Groupon.Exceptions.LoginExeption;
import com.jb.Groupon.Repositories.CompanyRepo;
import com.jb.Groupon.Repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {


    @Autowired
    private AdminService adminService ;
    @Autowired
    private  CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    private  ClientService clientService;
    private CompanyRepo companyRepo;
    private CustomerRepo customerRepo;


    public ClientService login(String email, String password, ClientType clientType) throws LoginExeption {
        String failedLoginMsg = "the email and password do not match any client please try again";
        String loginSuccess = "you are now logged in as ";

        switch (clientType) {
            case admin:
                if (!adminService.login(email, password)) {
                    throw new LoginExeption (failedLoginMsg);

                }
                System.out.printf (loginSuccess + "'%s'\n", email);
                return adminService;

            case company:
                if (!companyService.login(email, password)) {
                    throw new LoginExeption (failedLoginMsg);
                }
                System.out.printf (loginSuccess + "'%s'\n", email);
                return  companyService;

            case customer:
                if (!customerService.login(email,password)) {
                    throw new LoginExeption (failedLoginMsg);
                }
                System.out.printf (loginSuccess + "'%s'\n", email);
                return  customerService;

            default:
                System.out.println ("thank you");


        }

    return clientService;
    }
}
