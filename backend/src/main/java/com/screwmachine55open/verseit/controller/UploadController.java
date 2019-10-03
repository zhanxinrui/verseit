package com.screwmachine55open.verseit.controller;

import com.screwmachine55open.verseit.constant.Constant;
import com.screwmachine55open.verseit.util.FileUtils;
import com.screwmachine55open.verseit.util.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * data 2018-06-28   02:47
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */

@Api("上传模块")
@RestController
@RequestMapping(Constant.BASE_URL + "/upload")
public class UploadController extends BaseController{


    /**
     * 上传文件到七牛云存储
     * @param multipartFile
     * @return
     * @throws IOException
     */
//    @RequestMapping( path = {"/avatar", "/avatar/**"},consumes = {"multipart/form-data"}, method = {RequestMethod.POST, RequestMethod.PUT})
//    public Result<String> uploadImg(@RequestParam("file") MultipartFile multipartFile)  {
//        FileInputStream inputStream = null;
//        String path = null;
//        try {
//            inputStream = (FileInputStream) multipartFile.getInputStream();
//            path = QiniuUtil.uploadImg(inputStream);
//            if(StringUtils.isNoneEmpty(path)){
//                 return Result.ok(path);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        return Result.error(path,"上传失败");
//
//
//    }
    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping( path = {"/avatar", "/avatar/**"},consumes = {"multipart/form-data"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public Result<String> uploadImg(@RequestParam("file") MultipartFile file)  {
        // 要上传的目标文件存放路径
        String localPath = Constant.PIC_ADDR;
        // 上传成功或者失败的提示
        String msg = "";
        String picPath = FileUtils.getFileName(file.getOriginalFilename());
        if (FileUtils.upload(file, localPath,picPath )) {
            // 上传成功，给出页面提示
            msg = "上传成功！";
            System.out.println("picPath:"+localPath+picPath);
            return Result.ok(localPath+picPath);
        } else {
            return Result.error(null, "上传失败");
        }

    }
//
//    /**
//     * 显示单张图片
//     * @return
//     */
//    @RequestMapping("show")
//    public ResponseEntity showPhotos(String fileName){
//
//        try {
//            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
