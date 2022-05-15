package io.github.fzdwx.lambada.lang;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/5/15 10:34
 */
public class IpUtil {

    public static String LOCAL_IP;
    // public static String NET_IP;


    static {
        LOCAL_IP = local();
        // NET_IP = netIp();
    }

    @SneakyThrows
    private static String local() {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @SneakyThrows
    private static String netIp() {
        String command = "curl -L ip.tool.lu";
        String[] commandSplit = command.split(" ");
        List<String> lcommand = new ArrayList<String>(Arrays.asList(commandSplit));

        ProcessBuilder processBuilder = new ProcessBuilder(lcommand);
        processBuilder.redirectErrorStream(true);
        Process p = processBuilder.start();
        InputStream is = p.getInputStream();
        BufferedReader bs = new BufferedReader(new InputStreamReader(is));

        p.waitFor();
        if (p.exitValue() != 0) {
            //说明命令执行失败
            //可以进入到错误处理步骤中
        }
        String line = null;
        while ((line = bs.readLine()) != null) {
            if (line.startsWith("当前IP:")) {
                return line.replace("当前IP:", "").trim();
            }
        }
        return "";
    }
}