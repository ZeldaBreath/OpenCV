package src.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import src.util.VideoFrameInterceptorUtil;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class MainController {
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/getVideoFrame")
    public void getVideoFrame(Long time, HttpServletResponse response) throws IOException {
        String videoPath = "C:\\Users\\13255\\Videos\\" + "video.mp4";
        File video = null;
        try {
            video = ResourceUtils.getFile(videoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        //返回base64编码
        PrintWriter writer = response.getWriter();
        writer.write("data:image/jpeg;base64," + VideoFrameInterceptorUtil.getVideoFrame(video, time));
        System.out.println("data:image/jpeg;base64," + VideoFrameInterceptorUtil.getVideoFrame(video, time));
        writer.flush();
        writer.close();
        //返回二进制流
//        OutputStream out = response.getOutputStream();
//        out.write(Base64.getDecoder().decode(VideoFrameInterceptorUtil.getVideoFrame(video, time * 1000000)));
//        out.flush();
//        out.close();
    }
}
