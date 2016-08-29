package com.alarm.controller;

import com.alarm.utils.TimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AlarmController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("currentTime", TimeUtils.getCurrentTime());
        return "index";

    }

    @RequestMapping(value = "/setAlarm", method = RequestMethod.GET)
    public String setAlarm(@RequestParam("alarmTime") String alarmTime, ModelMap modelMap) {
        String currentTime = TimeUtils.getCurrentTime();
        //in case if time is reached, we navigate to alarm page
        if (currentTime.equals(alarmTime)) {
            return "alarm";
        }

        //in another case we come back to setting page to try again in 30 seconds
        modelMap.addAttribute("currentTime", currentTime);
        modelMap.addAttribute("alarmTime", alarmTime);
        return "index";
    }

}