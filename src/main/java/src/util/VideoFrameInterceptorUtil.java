package src.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.util.ResourceUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class VideoFrameInterceptorUtil {
    /**
     * 获取指定时刻的视频帧
     * @param video 视频文件
     * @param us 时间戳，单位为微秒
     * @return
     */
    public static String getVideoFrame(File video, long us) {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(video);
        try {
            ff.start();
            // 截取指定时刻图片
            ff.setVideoTimestamp(us);
            if(us > ff.getLengthInTime()) us = ff.getLengthInTime()-1;
            Frame frame = ff.grabFrame();
            // 防止超过视频时长而截取不到画面
            while (frame == null) {
                ff.setVideoTimestamp(us - 1000000);
                frame = ff.grabFrame();
            }
            // 截取的帧
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();
            // 对截图进行等比例缩放(缩略图)
            int width = 1080;
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(thumbnailImage, "jpg", out);
            byte[] bytes = out.toByteArray();
            out.close();
            ff.stop();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String videoPath = "C:\\Users\\13255\\Videos\\" + "video.mp4";
        File video = null;
        try {
            video = ResourceUtils.getFile(videoPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("data:image/jpeg;base64," + getVideoFrame(video, 1000000));
    }
}
