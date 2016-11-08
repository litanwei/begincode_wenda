package net.begincode.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.begincode.utils.ImageUtil;

/*
 * 通过url img.src
 * 从服务器获取img图片
 */
@RequestMapping("/image")
@Controller
public class ImgController {
	@RequestMapping(value="/vcode")
	public void getVcode(HttpServletResponse response,HttpSession session) throws IOException{
		Object img[]=ImageUtil.createImage();
		session.setAttribute("imageVcode", img[0]);
		BufferedImage image = (BufferedImage) img[1];  
		response.setContentType("image/png"); 
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "png", os);  
	}
}
