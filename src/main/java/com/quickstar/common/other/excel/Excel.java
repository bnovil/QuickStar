package com.quickstar.common.other.excel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author lzq
 * @description 导出ExceL表
 * @since 2016/01/14 02:00:00
 */
public class Excel {

    /**
     * 生成excel文件(文件标题栏与文件内容一定要对应)
     *
     * @param os    OutputStream
     * @param title (excel文件标题栏)
     * @param lists (excel文件内容)
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    @SuppressWarnings("rawtypes")
    public static void writeExcel(OutputStream os, String[] title, List lists) throws IOException, WriteException {
        // 创建可以写入的Excel工作薄(默认运行生成的文件在tomcat/bin下 )
        WritableWorkbook wwb = Workbook.createWorkbook(os);
        // 生成工作表,(name:First Sheet,参数0表示这是第一页)
        WritableSheet sheet = wwb.createSheet("First Sheet", 0);

        // 开始写入第一行(即标题栏)
        for (int i = 0; i < title.length; i++) {
            // 用于写入文本内容到工作表中去
            Label label;
            label = new Label(i, 0, title[i]);
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }

        // 开始写入内容
        for (int row = 0; row < lists.size(); row++) {
            String listvalue = lists.get(row).toString();
            Label label;
            int j = 1;
            if (row > title.length)
                j++;
            label = new Label(row, j, listvalue);
            sheet.addCell(label);
        }

        // 写入数据
        wwb.write();
        // 关闭文件
        wwb.close();
        // 关闭输出流
        os.close();
    }

    /**
     * 向客户端下载文件,弹出下载框.
     *
     * @param response (HttpServletResponse)
     * @param file     (需要下载的文件)
     * @param isDel    (下载完成后是否删除该文件)
     * @throws IOException
     */
    public static void exportFile(HttpServletResponse response, File file, boolean isDel) throws IOException {
        OutputStream out;
        InputStream in;

        // 获得文件名
        String filename = URLEncoder.encode(file.getName(), "UTF-8");
        response.reset();
        // 定义输出类型(下载)
        response.setContentType("application/x-msdownload");
        response.setHeader("Location", filename);
        // 定义输出文件头
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        out = response.getOutputStream();
        in = new FileInputStream(file.getPath());

        byte[] buffer = new byte[1024];
        int i;
        while ((i = in.read(buffer)) != -1) {
            out.write(buffer, 0, i);
        }

        in.close();
        out.close();

        if (isDel) {
            //删除文件,删除前关闭所有的Stream.
            file.delete();
        }

    }

}
