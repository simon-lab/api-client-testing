package com.saimen.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saimen.api.entity.Inquiry;

@RestController
@RequestMapping("/inquiry")
public class inquiryController {

    @PostMapping("/case1")

    public String inquiryCheck(@RequestBody Inquiry inq) {

        String getPartnerRefNo = inq.getPartnerReferenceNo();

        return "Nih Reference No Nya:" + getPartnerRefNo;

    }
}
