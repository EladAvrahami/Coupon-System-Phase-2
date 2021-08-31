package com.jb.Groupon.Jobs;

import com.jb.Groupon.Beans.Coupon;
import com.jb.Groupon.Repositories.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;


/**
 * this class contain all jobs the program will do automatically at specifies time
 * its going to delete all expired coupon according to execution running time
 */

@Configuration
@EnableScheduling
public class DailyJob {
    @Autowired
    CouponRepo couponRepo;

    /**
     *this job will search on the  coupon repository if there are coupons that there date expired and delete them
     */

    /**
     * Scheduled annotation help us to delete all the coupons that expired automatically on specific time use with cron expression
     * we also could use @daily or @midnight –to run once a day
     * we got help from https://crontab.guru/ & https://www.baeldung.com/cron-expressions
     * this job will happen every day at 00:01
     *  *SECENDS *MIN *HOUR *DAY *MON *YEAR
     */

    @Scheduled(cron = "* 01 00 * * *")
    public  void scheduledCouponDeleteJob() {
        System.out.println("Scheduled is working ********************************************");
        List<Coupon> couponList = couponRepo.findAll ();
        for (Coupon item : couponList) {
            java.util.Date currentDate = new Date(); //new Date(new java.util.Date().getTime())
            //checks if the coupon is expired
            if (currentDate.after(item.getEndDate())) {
                //deletes the purchase history of the coupon in the sql table and deletes the coupon from the sql table
                couponRepo.deleteById (item.getId());

                System.out.println("Coupon was expired and deleted from DB : " + item.getTitle());
            }


        }

    }
}

