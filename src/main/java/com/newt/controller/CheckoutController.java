package com.newt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.bean.ProductCheckoutDetails;
import com.newt.commonutils.NotificationService;
import com.newt.model.Checkout;
import com.newt.model.ProductCheckout;
import com.newt.service.CheckoutService;
import com.newt.service.ProductCheckoutService;
import com.wordnik.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;
    @Autowired
    private ProductCheckoutService prodchkoutService;
    
    private static final Logger logger = Logger.getLogger(CheckoutController.class);
    
    @ApiOperation(value = "post a customer")
    @RequestMapping(value = "/addCheckoutDetails", method = RequestMethod.POST)

    
    
    public String registerCustomer(@RequestBody ProductCheckoutDetails productcheckoutdtls) {

        if(productcheckoutdtls != null)
         {   
         Checkout checkout = new Checkout();
         checkout.setAddress1(productcheckoutdtls.getAddress1());
         checkout.setAddress2(productcheckoutdtls.getAddress2());
         checkout.setCardExpDate(productcheckoutdtls.getCardExpDate());
         checkout.setCity(productcheckoutdtls.getCity());
         checkout.setCountry(productcheckoutdtls.getCountry());
         checkout.setCreditCardNo(productcheckoutdtls.getCreditCardNo());
         checkout.setCustomerEmail(productcheckoutdtls.getCustomerEmail());
         checkout.setCustomerId(productcheckoutdtls.getCustomerId());
         checkout.setCustomerName(productcheckoutdtls.getCustomerName());
         checkout.setPincode(productcheckoutdtls.getPincode());
         checkout.setState(productcheckoutdtls.getState());
         checkoutService.save(checkout);
         for (int i=0;i<productcheckoutdtls.getProductList().size();i++) {
          ProductCheckout saveprod = new ProductCheckout();
          
          saveprod.setCustomerId(productcheckoutdtls.getCustomerId());
          saveprod.setProductId(productcheckoutdtls.getProductList().get(i).getProductId());
          saveprod.setProductName(productcheckoutdtls.getProductList().get(i).getProductName());
          saveprod.setCheckoutOrderId(checkout.getOrderId());
          prodchkoutService.save(saveprod);
      }
          return "";
          
         }
           return null;
       }
    
    
    /*    public Checkout registerCustomer(@RequestBody Checkout checkout) {
    		if(checkout != null)
    		{
    			checkout = checkoutService.save(checkout);
    			return checkout;
    			
    		}
        return null;
    }*/

 }
