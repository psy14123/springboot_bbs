package cn.psy.homework11.controller;

import io.lettuce.core.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 用来生成四位随机数
 */
@Controller
public class CodeController {
    private Logger logger = Logger.getLogger(String.valueOf(CodeController.class));
    final static int four = 4;
    final static int eleven = 11;
    final static String nine = "9";
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    /**
     * 生成四位随机数作为手机的验证码
     * @param tel
     * @return String
     */
    @RequestMapping("/createCode")
    @ResponseBody
    public String createCode(@RequestParam("tel") String tel){
        String code=null;
        int intFlag = (int)(Math.random() * 1000);
        String flag = String.valueOf(intFlag);
        if (four == flag.length() && flag.substring(0, 1).equals(nine)){
           code = flag;
        }
        else
        {intFlag = intFlag + 1000;
        code = String.valueOf(intFlag);}//生成随机四位验证码
        System.out.println("此次验证码为"+code+"有效期为一分钟!");
        stringRedisTemplate.opsForValue().set(tel, code,60,TimeUnit.SECONDS);//将电话与验证码放入redis中，有效期一分钟

        return code;
    }




    /**
     * 生成图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/validateImage")
    @ResponseBody
    public void ValidateGenerate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);//创建图像缓冲区

        Graphics g = bi.getGraphics(); //通过缓冲区创建一个画布

        Color c = new Color(255, 255, 255); //创建颜色
        /*根据背景画了一个矩形框
         */
        g.setColor(c);//为画布创建背景颜色

        g.fillRect(0, 0, 68, 22); //fillRect:填充指定的矩形
        // X和Y用于指定矩形左上角也就是相对于原点的位置，width和height用于指定矩形的宽和高。

        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();//转化为字符型的数组
        Random r = new Random();
        int len = ch.length;
        int index; //index用于存放随机数字
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);//产生随机数字
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));  //设置颜色随机
            g.drawString(ch[index] + "", (i * 15) + 3, 18);//画数字以及数字的位置
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("piccode", sb.toString()); //将数字保留在session中，便于后续的使用
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }



    /**
     * 图形验证码判断
     * @param validateCode
     * @param request
     * @return
     */
    @RequestMapping("/ValidateImage")
    @ResponseBody
    public String ValidateImage(@RequestParam(value = "validateCode")String validateCode,HttpServletRequest request){

        HttpSession session=request.getSession();
        String piccode=(String) session.getAttribute("piccode");
        if(validateCode.equals(piccode)) {
            String jsonStr = "{\"errorCode\":\"1\",\"errorMessage\":\"success\"}";
            return jsonStr;
        }
        else {
            String jsonStr = "{\"errorCode\":\"0\",\"errorMessage\":\"phone or password is error\"}";
            return jsonStr;
        }
    }
}
