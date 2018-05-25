/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.oldtest.qrcode;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author liusk
 * @version $Id: CodeTest.java, v 0.1 2018/5/24 16:35 liusk Exp $
 */
public class CodeTest {

    /*****************************对公方法************************/
    /**生成二维码并存储到指定路径
     * @param content 需要转化为二维码的文本内容
     * @param imgPath 存储二维码的路径
     * @param imgType 图片类型
     */
    public void encoder(String content, String imgPath, String imgType) {
        this.encoderQRCode(content, imgPath, imgType, 7);
    }

    /**生成二维码并放入输出流里
     * @param content 需要转化为二维码的文本内容
     * @param output 存储二维码的输出流
     * @param imgType 图片类型
     */
    public void encoder(String content, OutputStream output, String imgType) {
        this.encoderQRCode(content, output, imgType, 7);
    }

    /**解析二维码
     * @param imgPath 二维码图片路径
     * @return
     */
    public String decoder(String imgPath) {
        return this.decoderQRCode(imgPath);
    }

    /**解析二维码
     * @param input 二维码的输入流
     * @return
     */
    public String decoder(InputStream input) {
        return this.decoderQRCode(input);
    }

    /********************************私有方法*********************/
    private void encoderQRCode(String content, String imgPath, String imgType, int size) {
        try {
            BufferedImage bufImg = this.qrcodeCommon(content, imgType, size);
            File imgFile = new File(imgPath);
            ImageIO.write(bufImg, imgType, imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void encoderQRCode(String content, OutputStream output, String imgType, int size) {
        try {
            BufferedImage bufImg = this.qrcodeCommon(content, imgType, size);
            ImageIO.write(bufImg, imgType, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**解析二维码
     * @param imgPath
     * @return
     */
    private String decoderQRCode(String imgPath) {
        File imageFile = new File(imgPath);
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(imageFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new QuickResponseCodeImage(bufImg)), "utf-8");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return content;
    }

    /**解析二维码
     * @param input
     * @return
     */
    private String decoderQRCode(InputStream input) {
        BufferedImage bufImg = null;
        String content = null;
        try {
            bufImg = ImageIO.read(input);
            QRCodeDecoder decoder = new QRCodeDecoder();
            content = new String(decoder.decode(new QuickResponseCodeImage(bufImg)), "utf-8");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return content;
    }

    /*********************************私有的公共方法*****************/
    /**生成二维码的私有公共方法
     * @param content 被转化的文本信息
     * @param imgType 图片的类型
     * @param size 图片的尺寸
     * @return
     */
    private BufferedImage qrcodeCommon(String content, String imgType, int size) {
        BufferedImage bufImg = null;
        try {
            Qrcode qrcodeHandler = new Qrcode();
            qrcodeHandler.setQrcodeErrorCorrect('M');
            qrcodeHandler.setQrcodeEncodeMode('B');
            qrcodeHandler.setQrcodeVersion(size);
            byte[] contentBytes = content.getBytes("utf-8");
            int imgSize = 67 + 12 * (size - 1);
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2D = bufImg.createGraphics();
            g2D.setBackground(Color.WHITE);
            g2D.clearRect(0, 0, imgSize, imgSize);
            g2D.setColor(Color.BLACK);
            int pixoff = 2;
            if (contentBytes.length > 0 && contentBytes.length < 800) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            g2D.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                throw new Exception(
                        "QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
            }
            /*//单纯的向二维码中插入Logo
            Image img = ImageIO.read(new File("G:/liusk.jpg"));
            g2D.drawImage(img, 55, 55, null);*/

            /*BufferedImage img = ImageIO.read(new File("G:/liusk.jpg"));
            int widthLogo = img.getWidth();
            int heightLogo = img.getHeight();
            int x = (bufImg.getWidth() - widthLogo) / 2;
            int y = (bufImg.getHeight() - heightLogo) / 2;

            g2D.drawImage(img, x, y, widthLogo, heightLogo, null);
            g2D.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g2D.setStroke(new BasicStroke());
            g2D.setColor(Color.WHITE);
            g2D.drawRect(x, y, widthLogo, heightLogo);*/

            g2D.dispose();
            bufImg.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImg;
    }

    /***************************测试生成二维码************************/
    public static void main(String[] args) {

        CodeTest handler = new CodeTest();

        String imgPath = "G:/twoDimensionCode.jpg";

        String encoderContent = "❤我喜欢你❤";
        //String encoderContent = "http://www.baidu.com";
        handler.encoder(encoderContent, imgPath, "jpg");
        System.out.println("========encoder success");

        /*String decoderContent = handler.decoder(imgPath);
        System.out.println(decoderContent);
        System.out.println("========decoder success!!!");*/
    }

}
