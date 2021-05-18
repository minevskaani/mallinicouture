package io.infosec;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


public class Mac {
    public static List<byte[]> getMACAddressInBytes() {
        List<byte[]> result = new ArrayList<>();
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
            Enumeration<NetworkInterface> nis =  NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                byte[] ha = ni.getHardwareAddress();
                if (ha != null) {

                    result.add(ha);
                }
            }
            //return networkInterface.getHardwareAddress();
            return result;
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getMACAddress() {
        List<byte[]> macAddress = getMACAddressInBytes();
        String[] result = new String[macAddress.size()];

        for (int i = 0; i < result.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int byteIndex = 0; byteIndex < macAddress.get(i).length; byteIndex++) {
                sb.append(String.format("%02X%s", macAddress.get(i)[byteIndex], (byteIndex < macAddress.get(i).length - 1) ? "-" : ""));
            }
            result[i] = sb.toString();
        }
        Arrays.sort(result);
        return result;
    }

    public static String getHardwareData() {
        String[] macs = getMACAddress();
        return Arrays.deepToString(macs);
    }
}
