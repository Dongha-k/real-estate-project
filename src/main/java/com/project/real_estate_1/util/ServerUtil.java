package com.project.real_estate_1.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerUtil {
    public static String getIp(){
        String result = null;
        try {
            result = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            result = "";
        }
        return result;
    }
}
