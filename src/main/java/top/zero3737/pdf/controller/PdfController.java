package top.zero3737.pdf.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class PdfController {

    @Autowired
    HttpServletResponse response;

    @RequestMapping("pdf")
    public String pdf() throws Exception {

        response.setContentType("application/pdf");

        Document document = new Document();
        // 定义表头信息
        String[] tableHeader = {"日期", "商户", "消费机编号", "消费笔数", "消费金额", "应收管理费", "实际金额", "管理费率"};

        // 设置 PDF 大小为 A4，rotate 方法定义是否选装
        document.setPageSize(new Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth()).rotate());
        // 创建书写器（Writer）对象
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
        // 打开 PDF
        document.open();
        // 设置标题
        document.add(new Paragraph("Hello World", FontFactory.getFont("宋体", 20)));
        // 设置表格单元格列数
        PdfPTable table = new PdfPTable(tableHeader.length);
        // 设置表头信息
//        tableHeader.forEach(str -> {
////            PdfPCell pdfPCell = new PdfPCell(new Paragraph(str, FontFactory.getFont("宋体", 10)));
//            table.addCell(str);
//        });
        for (int i = 0; i < tableHeader.length; i++) {
            String str = tableHeader[i];

            table.addCell("he人");
        }
        document.add(table);
        // 关闭 PDF
        document.close();

        return "Hello PDF!";

    }

}
