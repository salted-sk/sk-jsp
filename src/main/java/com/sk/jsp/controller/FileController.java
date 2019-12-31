package com.sk.jsp.controller;

import com.base.common.AbstractController;
import com.base.util.Constant;
import com.base.util.FileUtil;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 上传图片
 *
 * @author zhangqiao
 * @since 2019-01-21
 */
@Controller
@RequestMapping("/file")
public class FileController extends AbstractController {

    private static final Log logger = LogFactory.getLog(FileController.class);

    /**
     * 上传图片
     *
     * @param fileData 源文件
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/upload/img", method = RequestMethod.POST)
    public void upload(MultipartFile fileData, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String expandedName = fileData.getContentType();
        if (expandedName.equals("image/pjpeg")
                || expandedName.equals("image/jpeg")) {
            expandedName = ".jpg";
        } else if (expandedName.equals("image/png")
                || expandedName.equals("image/x-png")) {
            expandedName = ".png";
        } else if (expandedName.equals("image/gif")) {
            expandedName = ".gif";
        } else if (expandedName.equals("image/bmp")) {
            expandedName = ".bmp";
        } else {
            return;
        }
        if (!fileData.isEmpty()) {
            String realFilePath = request.getServletContext().getRealPath("/") + Constant.IMG_URL;
            try {
                File file = new File(realFilePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                Integer math = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;//产生1000-9999的随机数
                String fileName = math.toString() + System.currentTimeMillis() + expandedName;
                File destFile = new File(realFilePath, fileName);
                fileData.transferTo(destFile);
                Gson gson = new Gson();
                response.setContentType("text/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(gson.toJson(fileName != null ? fileName : new String()));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (Exception e) {
                logger.error("文件存储失败。" + e);
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "/delImg", method = RequestMethod.POST)
    public ModelAndView getRoleList (HttpServletRequest request, String icon) {
        String realFilePath = request.getServletContext().getRealPath("/") + Constant.IMG_URL;
        FileUtil.deleteFile(realFilePath + icon);
        return new ModelAndView(JSON_VIEW).addObject(Constant.RETURN, Constant.SUCCESS_MESSAGE);
    }

}
