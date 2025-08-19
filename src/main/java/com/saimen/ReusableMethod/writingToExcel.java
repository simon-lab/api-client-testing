package com.saimen.ReusableMethod;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writingToExcel {

    public static Color changeColor(String red, String green, String blue) {
        int r = Integer.parseInt(red, 16); // 211
        int g = Integer.parseInt(green, 16); // 237
        int b = Integer.parseInt(blue, 16); // 191

        Color newColor = new Color(r, g, b);

        return newColor;
    }

    public static void main(List<List<Object>> reportDataList, String namaFile) throws IOException {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Disbursement Report");
            CellStyle headerStyling = workbook.createCellStyle();
            CellStyle dataStyling = workbook.createCellStyle();
            CellStyle passedStatusStyling = workbook.createCellStyle();
            CellStyle failedStatusStyling = workbook.createCellStyle();
            CellStyle skippedStatusStyling = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            Font dataFont = workbook.createFont();

            headerFont.setFontName("Calibri");
            headerFont.setFontHeightInPoints((short) 11);
            headerFont.setBold(true);

            headerStyling.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyling.setAlignment(HorizontalAlignment.CENTER);
            headerStyling.setWrapText(true);
            headerStyling.setFont(headerFont);
            headerStyling.setBorderTop(BorderStyle.MEDIUM);
            headerStyling.setBorderBottom(BorderStyle.MEDIUM);
            headerStyling.setBorderLeft(BorderStyle.MEDIUM);
            headerStyling.setBorderRight(BorderStyle.MEDIUM);

            dataStyling.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyling.setAlignment(HorizontalAlignment.CENTER);
            dataStyling.setWrapText(true);
            dataStyling.setFont(dataFont);
            dataStyling.setBorderTop(BorderStyle.THIN);
            dataStyling.setBorderBottom(BorderStyle.THIN);
            dataStyling.setBorderLeft(BorderStyle.THIN);
            dataStyling.setBorderRight(BorderStyle.THIN);

            passedStatusStyling.cloneStyleFrom(dataStyling);
            Color passedColor = changeColor("d3", "ed", "bf");
            XSSFColor xssfPassedColor = new XSSFColor(passedColor, null);
            passedStatusStyling.setFillForegroundColor(xssfPassedColor);
            passedStatusStyling.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            failedStatusStyling.cloneStyleFrom(dataStyling);
            Color failedColor = changeColor("ff", "cf", "ca");
            XSSFColor xssfFailedColor = new XSSFColor(failedColor, null);
            failedStatusStyling.setFillForegroundColor(xssfFailedColor);
            failedStatusStyling.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            skippedStatusStyling.cloneStyleFrom(dataStyling);
            Color skippedColor = changeColor("e6", "e6", "e6");
            XSSFColor sxxfSkippedColor = new XSSFColor(skippedColor, null);
            skippedStatusStyling.setFillForegroundColor(sxxfSkippedColor);
            skippedStatusStyling.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            int columnLenght = 0;

            for (int r = 0; r < reportDataList.size(); r++) {
                XSSFRow row = sheet.createRow(r);
                List<Object> dataRow = reportDataList.get(r);

                for (int c = 0; c < dataRow.size(); c++) {
                    XSSFCell cell = row.createCell(c);
                    Object value = dataRow.get(c);

                    if (value instanceof String)
                        cell.setCellValue((String) value);
                    if (value instanceof Integer)
                        cell.setCellValue((Integer) value);
                    if (value instanceof Boolean)
                        cell.setCellValue((Boolean) value);

                    if (r == 0) {
                        cell.setCellStyle(headerStyling);
                    } else if (c == 4 && value instanceof String && "Passed".equalsIgnoreCase((String) value)) {
                        cell.setCellStyle(passedStatusStyling);
                    } else if (c == 4 && value instanceof String && "Failed".equalsIgnoreCase((String) value)) {
                        cell.setCellStyle(failedStatusStyling);
                    } else if (c == 4 && value instanceof String && "Skipped".equalsIgnoreCase((String) value)) {
                        cell.setCellStyle(skippedStatusStyling);
                    } else {
                        cell.setCellStyle(dataStyling);
                    }

                    columnLenght = columnLenght + 1;
                }
            }

            for (int i = 0; i < columnLenght; i++) {

                if (i < 1) {
                    sheet.setColumnWidth(i, 1000);
                } else if (i < 5) {
                    sheet.setColumnWidth(i, 4000);
                } else {
                    sheet.setColumnWidth(i, 9000);
                }
            }

            FileOutputStream outstream = new FileOutputStream(
                    "./output/excelReport/" + namaFile + ".xlsx");
            workbook.write(outstream);
        }
        System.out.println("File Report Tersimpan");
    }

}
