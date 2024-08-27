package com.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/FurnAddAndUpdate")
public class FurnAddAndUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name="";
        String business="";
        String price="";
        String sales="";
        String stock="";
        String uploadRealPath="assets/images/product-image/default.jpg";
        String furnId="";
        String pageNum="";
        request.setCharacterEncoding("utf-8");
        if(ServletFileUpload.isMultipartContent(request)){
            DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setHeaderEncoding("utf-8");
            List<FileItem> list;
            try {
                list=servletFileUpload.parseRequest(request);
            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }
            //遍历
            for (FileItem fileItem : list) {
                //如果信息是普通格式
                if(fileItem.isFormField()){
                    String fieldName = fileItem.getFieldName();
                    switch (fieldName){
                        case "name":
                            name=fileItem.getString("utf-8");
                            break;
                        case "business":
                            business=fileItem.getString("utf-8");
                            break;
                        case "price":
                            price=fileItem.getString("utf-8");
                            break;
                        case "sales":
                            sales=fileItem.getString("utf-8");
                            break;
                        case "stock":
                            stock=fileItem.getString("utf-8");
                            break;
                        case "furnId":
                            furnId=fileItem.getString("utf-8");
                            break;
                        case "uploadRealPath":
                            uploadRealPath=fileItem.getString("utf-8");
                            break;
                        case "pageNum":
                            pageNum=fileItem.getString("utf-8");
                            break;
                    }
                }//如果是文件格式
                else{
                    String fileName=fileItem.getName();
                    //如果没有文件的话，也就是说拿不到文件名，说明没有文件

                    if("".equals(fileName)){
                        continue;
                    }
                    //获取文件上床的目录
                    String realPath=request.getServletContext().getRealPath("/");
                    String uploadPath=realPath+"assets/images/product-image/";
                    //如果文件夹不存在，那就创建
                    File file = new File(uploadPath);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    //将得到的文件写入到指定目录中
                    fileName = Math.round(Math.random() * 100000) + "_" + fileName;
                    try {
                        fileItem.write(new File(uploadPath+fileName));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    //在数据库中的furn的信息是这样的
                    uploadRealPath="assets/images/product-image/"+fileName;
//                    System.out.println("已写入文件~~");
                }
            }
        }
//        System.out.println("name="+name+"|business="+business+"|price="+price+"|sales="+sales+
//                "\n|stock="+stock+"|id="+furnId+"|uploadRealPath"+uploadRealPath+"|pageNum="+pageNum);

        String action="";
        String sameParameter="&name="+name+"&business="+business+"&price="+price+"" +
                "&sales="+sales+"&stock="+stock+"&imgPath="+uploadRealPath;
        //发送新的一个请求到furnServlet
        //根据id是否为空来判断要跳转到哪个方法
        if("".equals(furnId)){
            action="addFurn";
            request.getRequestDispatcher("/FurnServlet?action="+action+sameParameter+
                    "&uploadRealPath="+uploadRealPath).forward(request,response);
        }else{
            action="updateFurn";
            request.getRequestDispatcher("/FurnServlet?action="+action+sameParameter+
                    "&id="+furnId+"&uploadRealPath="+uploadRealPath+"&pageNum="+pageNum).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}