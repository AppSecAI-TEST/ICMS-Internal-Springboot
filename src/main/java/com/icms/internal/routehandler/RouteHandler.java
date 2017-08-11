package com.icms.internal.routehandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Infocepts India in 2017.
 */

@Controller
public class RouteHandler
{
    @RequestMapping ({ "/Login", "/CampusConnect/**" })
    public String index() {
        return "forward:/index.html";
    }
}
