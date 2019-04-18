package cn.whu.gugugu.controller;

import cn.whu.gugugu.domain.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SponsorController {
    @RequestMapping(value = "/party/launch", method = RequestMethod.POST)
    public BaseResponse launchParty(@RequestParam(value = "name")String name,
                                    @RequestParam(value = "latitude")String latitude,
                                    @RequestParam(value = "fee")String fee,
                                    @RequestParam(value = "time")String time,
                                    @RequestParam(value = "longtitude")String longtitude,
                                    @RequestParam(value = "party_id")String partyId) {

        return null;
    }
}
