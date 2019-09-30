package top.zero3737.pdf.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PdfController {

    @Autowired
    HttpServletResponse 响应;

    @RequestMapping("pdf")
    public void pdf() throws Exception {

        响应.setContentType("application/pdf");

        String 二级标题 = MessageFormat.format("使用单位：上饶县中学    （单位：元）    打印时间：{0}    印单号：{1}",
                new SimpleDateFormat("yyyy/MM/dd" ).format(new Date()), "6666-6666666");

        // 定义表头信息
        String[] 表头 = {"日期", "商户", "消费机编号", "消费笔数", "消费金额", "应收管理费", "实际金额", "管理费率"};
        Document document = new Document();

        // 设置 PDF 大小为 A4，PageSize.A4.rotate() 方法定义是否选装
        document.setPageSize(PageSize.A4);
        // 创建书写器（Writer）对象
        PdfWriter writer = PdfWriter.getInstance(document, 响应.getOutputStream());
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
        // 打开 PDF
        document.open();
        // 设置标题，Paragraph 类默认换行，字体必须要设置正确，不然可能中文不显示
        Paragraph 标题 = new Paragraph("itext 测试示例", FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", 20));
        // 居中对齐
        标题.setAlignment(Element.ALIGN_CENTER);
        // 设置下边距，参数为浮点型
        标题.setSpacingAfter(15f);
        document.add(标题);
        // Phrase 类默认不换行
        document.add(new Phrase(二级标题, FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", 14)));
        // 设置表格单元格列数,每个单元格默认等比划分宽度
        PdfPTable 表格 = new PdfPTable(表头.length);
        // 设置表格宽度
        表格.setWidthPercentage(100f);
        // 设置表头信息
        for (String 字符串 : 表头) {
            // 将内容嵌入到单元个中
            PdfPCell 单元格 = new PdfPCell(new Paragraph(字符串, FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", 12)));
            // 设置背景颜色
            单元格.setBackgroundColor(BaseColor.GRAY);
            // 设置单元格固定高度
            单元格.setFixedHeight(30f);
            // 设置单元格水平居中
            单元格.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 设置单元格垂直居中
            单元格.setVerticalAlignment(Element.ALIGN_MIDDLE);
            表格.addCell(单元格);
        }
        for (int i = 0; i < 800; i++) {
            PdfPCell 单元格 = new PdfPCell(new Paragraph("content", FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", 12)));
            单元格.setFixedHeight(20f);
            单元格.setHorizontalAlignment(Element.ALIGN_CENTER);
            单元格.setVerticalAlignment(Element.ALIGN_MIDDLE);
            表格.addCell(单元格);
        }
        document.add(表格);
        // 关闭 PDF
        document.close();

    }

}
