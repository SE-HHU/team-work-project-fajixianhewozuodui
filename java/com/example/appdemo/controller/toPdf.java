package com.example.appdemo.controller;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.listener.TextChunk;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class toPdf {

    public static int count;
    public String year_month_day ;
    public String month;
    public String day;

    public toPdf() {
        year_month_day = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        month = year_month_day.substring(5,7);
        day = year_month_day.substring(8,10);
        count++;
    }

    /**
     * 生成一个pdf至指定路径，pdf中分两列生成题目或答案
     * @param Exes
     * @throws Exception
     */
    public void generatePdf(ArrayList<String> Exes,ArrayList<String> Answers,int grade,String save_path_exes,String save_path_ans) throws Exception {
        drawPdf(save_path_exes,Exes,grade);
        drawPdf(save_path_ans,Answers,grade);
    }

    public void generatePdf_forTest(ArrayList<String> Exes,int grade,String save_path_exes) throws Exception{
        drawPdf(save_path_exes,Exes,grade);
    }

    private void drawPdf(String save_path,ArrayList<String> Content,int grade) throws Exception{
        int num = Content.size(); //数量
        int one_col = num/2;
        int index = 1;

        int size = num == 30 ? 23 : 18;
        float _float = num == 30 ? 2f : 1.6f;

        //对应标题年级
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"Grade1");
        map.put(2,"Grade2");
        map.put(3,"Grade3");
        map.put(4,"Grade4");
        map.put(5,"Grade5");
        map.put(6,"Grade6");
        //获取时间


        PdfWriter pdfWriter = new PdfWriter(save_path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        PageSize pageSize = PageSize.A4;
        PdfPage page = pdfDocument.addNewPage(pageSize);
        PdfCanvas canvas1 = new PdfCanvas(page);

        //画板中心为左上角
        canvas1.concatMatrix(1, 0, 0, 1, 0, pageSize.getHeight());
        //生成标题
        Paragraph title = new Paragraph(String.valueOf(num) + "    " + String.valueOf(map.get(grade)))
                .setFont(PdfFontFactory.createFont(FontConstants.HELVETICA_BOLDOBLIQUE)).setFontSize(20);
        document.showTextAligned(title,240,-30,TextAlignment.LEFT);
        //生成第一列
        canvas1.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD),size)
                .setLeading(size * _float)
                .moveText(70,-60);
        for (int i = 0;i < one_col; i++) {
            canvas1.newlineShowText(String.valueOf(index) + "." + Content.get(i) + "=");
            index++;
        }
        canvas1.endText();
        //生成第二列
        canvas1.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD),size)
                .setLeading(size * _float)
                .moveText(360,-60);
        for (int i = one_col;i < num; i++) {
            canvas1.newlineShowText(String.valueOf(index) + "." + Content.get(i) + "=");
            index++;
        }
        canvas1.endText();
        //生成底部
        canvas1.beginText()
                .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER),14)
                .setLeading(14 * 1.2f)
                .moveText(0,-780);
        canvas1.newlineShowText("--------------------------------------------------------------" +
                "-------------------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------------");
        canvas1.newlineShowText("                                                       " + year_month_day);
        //
        canvas1.endText();
        document.close();
        pdfDocument.close();
        pdfWriter.close();
    }
}
