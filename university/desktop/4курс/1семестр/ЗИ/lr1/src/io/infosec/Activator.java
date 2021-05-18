package io.infosec;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Base64;

import static io.infosec.Config.license;


public class Activator {

    public static void main(String[] args) {
        String mac = Mac.getHardwareData();
        mac = Base64.getEncoder().encodeToString(mac.getBytes());

        try {
            PrintWriter pw = new PrintWriter(license);
            pw.print(mac);
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
