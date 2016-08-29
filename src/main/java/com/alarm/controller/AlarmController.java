package com.alarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class AlarmController {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("currentTime", dateFormat.format(Calendar.getInstance().getTime()));
        return "index";

    }

    @RequestMapping(value = "/setAlarm", method = RequestMethod.GET)
    public String setAlarm(@RequestParam("alarmTime") String alarmTime, ModelMap modelMap) {
        String currentTime = dateFormat.format(Calendar.getInstance().getTime());
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