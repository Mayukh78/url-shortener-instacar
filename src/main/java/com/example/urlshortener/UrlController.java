package com.example.urlshortener;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UrlController {

     @Autowired
     UrlDetailsRepository urlDetailsRepository;

    @GetMapping("/")
    public ModelAndView get()
    {

        return new ModelAndView("index");
    }
     @GetMapping("/{code}")
     public ModelAndView get(@PathVariable String code)
     {
        //if code doesn't exist return not found
        if(urlDetailsRepository.findByCode(code) == null)
            return new ModelAndView("not_found");
        //if Expiry time is lesser than current time then delete the record and return Link Expired
        else if(urlDetailsRepository.findByCode(code).getYm().compareTo(YearMonth.now()) == -1)
        {
            urlDetailsRepository.deleteByCode(code);
            return new ModelAndView("LinkExpired");
        }
        else
            return new ModelAndView("redirect:"+ urlDetailsRepository.findByCode(code).getUrl());
     }

     @PostMapping(path = "/shorten", consumes = "application/x-www-form-urlencoded")
     public ModelAndView UrlShorten(@RequestParam Map<String,String> Params){
         RequestDetails requestDetails=new RequestDetails();

         //below code block populate request detail object from parameter
         if(Params.get("url")!=null)
             requestDetails.setUrl(Params.get("url"));
         if(Params.get("customCode")!=null)
             requestDetails.setCustom_code(Params.get("customCode"));
         try {
             if (Params.get("expiry") != null)
                 requestDetails.setYm(YearMonth.parse(Params.get("expiry")));
         }
         catch (DateTimeParseException e)
         {
             //if exception occurred default value would be used
         }

         //check if the url already exists,if it exists return the short code
         UrlDetails urlDetails = urlDetailsRepository.findByUrl(requestDetails.getUrl());
         if(urlDetails != null)
         {
             ModelAndView modelAndView= new ModelAndView("code_success");
             String url="http://localhost:8080/"+urlDetails.getCode();
             modelAndView.addObject("url",url);
             return modelAndView;
         }

         CodeDetails shortenedCode = new CodeDetails();
         String custom_code = requestDetails.getCustom_code();

         //check if custom code exist
         if (urlDetailsRepository.findByCode(custom_code) != null)
             return new ModelAndView("code_exists");
         //if no custom code was provided
         else if(custom_code.equals("NONE")) {
             shortenedCode.codeGeneration(); //this generate a random code
             String code= shortenedCode.getCode();
             while (urlDetailsRepository.findByCode(code) != null) {
                 shortenedCode.codeGeneration();
                 code = shortenedCode.getCode();
             }
         }
         else
             shortenedCode.setCode(custom_code);

         UrlDetails obj = new UrlDetails();
         obj.setUrl(requestDetails.getUrl());
         obj.setCode(shortenedCode.getCode());
         obj.setYm(requestDetails.getYm());

         urlDetailsRepository.save(obj);

         String url="http://localhost:8080/"+obj.getCode();
         ModelAndView modelAndView =new ModelAndView("code_success");
         modelAndView.addObject("url",url);
         return modelAndView;
    }

}
