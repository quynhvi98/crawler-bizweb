package com.higgsup.bizwebcrawler.controller.common;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Created by viquy 1:33 PM 9/25/2017
 */
public class FileTemplate {
    public static StringWriter mailContent(String email, String password) {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        Template t = ve.getTemplate("./src/main/resources/mailContent.vm","utf-8");
        VelocityContext vc = new VelocityContext();
        vc.put("email", email);
        vc.put("password", password);
        StringWriter contentEmail = new StringWriter();
        t.merge(vc, contentEmail);
        return contentEmail;
    }
}
