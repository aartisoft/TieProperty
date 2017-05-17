package com.viralandroid.tieproperty;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by T on 17-05-2017.
 */

public class Commissions implements Serializable {

    public String total_commission_amount,total_tds_amount,total_payable_amount,total_visits,deducted_visits,total_amount,paid_amount,balance_amount;
    ArrayList<Sites> sites;
    public Commissions(JsonObject jsonObject, Context context){
        total_commission_amount = jsonObject.get("total_commission_amount").getAsString();
        total_tds_amount = jsonObject.get("total_tds_amount").getAsString();
        total_payable_amount = jsonObject.get("total_payable_amount").getAsString();
        total_visits = jsonObject.get("total_visits").getAsString();
        deducted_visits = jsonObject.get("deducted_visits").getAsString();
        total_amount = jsonObject.get("total_amount").getAsString();
        paid_amount = jsonObject.get("paid_amount").getAsString();
        balance_amount = jsonObject.get("balance_amount").getAsString();
        sites = new ArrayList<>();
        for (int i=0;i<jsonObject.get("site_visits").getAsJsonArray().size();i++){
            Sites site  = new Sites(jsonObject.get("site_visits").getAsJsonArray().get(i).getAsJsonObject(),context);
            sites.add(site);

        }


    }
    public class  Sites implements Serializable{
        public String id,name,phone,address,date,time,property,amount;
        public Sites(JsonObject jsonObject,Context context){
            id = jsonObject.get("id").getAsString();
            name = jsonObject.get("name").getAsString();
            phone = jsonObject.get("phone").getAsString();
            address = jsonObject.get("address").getAsString();
            date = jsonObject.get("date").getAsString();
            time = jsonObject.get("time").getAsString();
            property = jsonObject.get("property").getAsString();
            amount = jsonObject.get("amount").getAsString();
//            if (jsonObject.has("status")){
//                status = jsonObject.get("status").getAsString();
//            }else {
//                status = "null";
//            }

        }
    }


}
