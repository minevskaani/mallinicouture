package io.infosec;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import static io.infosec.Config.license;


public class Main {

    static boolean validate() {
        try {
            Scanner sc = new Scanner(license);
            String license = sc.next();
            license = new String(Base64.getDecoder().decode(license), "UTF-8");

            if (license.equals(Mac.getHardwareData())) {
                return true;
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        if (!validate()) {
            System.err.println("Please activate the program!");
            System.exit(1);
        }

        System.out.println("Program is working...!");
    }
}
