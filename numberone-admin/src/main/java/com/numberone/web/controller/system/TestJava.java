package com.numberone.web.controller.system;

public class TestJava {

    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        //String cmd = "ping www.baidu.com";
        String cmd = "C:\\Users\\元哥\\Desktop\\福伟项目文件\\c语言文件\\rsa1";
        Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象
        System.out.println(ClassLoader.getSystemResource(""));
        /*Process p = null;// 启动另一个进程来执行命令
        try {
            p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
            String lineStr;
            //获得命令执行后在控制台的输出信息
            while ((lineStr = inBr.readLine()) != null) System.out.println(lineStr);
            // 打印输出信息  
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)
                    //p.exitValue()==0表示正常结束，1：非正常结束  
                    System.err.println("命令执行失败!");
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }
}
