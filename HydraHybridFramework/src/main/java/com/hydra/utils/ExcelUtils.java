package com.hydra.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.*;

/**
 * Excel Utility - For reading and writing Excel files (Data-Driven Testing)
 */
public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;

    /**
     * Read Excel file
     */
    public static void readExcel(String filePath, String sheetName) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
            logger.info("Excel file read successfully: " + filePath);
        } catch (Exception e) {
            logger.error("Failed to read Excel file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get cell value as String
     */
    public static String getCellValue(int rowNum, int colNum) {
        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                logger.warn("Cell is null at row: " + rowNum + ", column: " + colNum);
                return "";
            }

            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return String.valueOf((int) cell.getNumericCellValue());
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            }

            logger.info(
                    "Retrieved cell value at row: " + rowNum + ", column: " + colNum + " - Value: " + cell.toString());
            return cell.toString();
        } catch (Exception e) {
            logger.error("Failed to get cell value: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get row count
     */
    public static int getRowCount() {
        try {
            int rowCount = sheet.getLastRowNum();
            logger.info("Total rows in sheet: " + (rowCount + 1));
            return rowCount;
        } catch (Exception e) {
            logger.error("Failed to get row count: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Get column count
     */
    public static int getColumnCount(int rowNum) {
        try {
            row = sheet.getRow(rowNum);
            int colCount = row.getLastCellNum();
            logger.info("Total columns in row " + rowNum + ": " + colCount);
            return colCount;
        } catch (Exception e) {
            logger.error("Failed to get column count: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Write to Excel file
     */
    public static void writeExcel(String filePath, String sheetName, int rowNum, int colNum, String value) {
        try {
            if (workbook == null) {
                workbook = new XSSFWorkbook();
            }

            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            cell = row.createCell(colNum);
            cell.setCellValue(value);

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            logger.info("Data written to Excel file: " + filePath);
        } catch (Exception e) {
            logger.error("Failed to write to Excel file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all data from sheet as List of Maps
     */
    public static List<Map<String, String>> getExcelData(String filePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        try {
            readExcel(filePath, sheetName);

            // Get header row
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            // Get data rows
            for (int i = 1; i <= getRowCount(); i++) {
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    String key = getCellValue(0, j);
                    String value = getCellValue(i, j);
                    rowData.put(key, value);
                }
                data.add(rowData);
            }

            logger.info("Retrieved all Excel data from sheet: " + sheetName);
            return data;
        } catch (Exception e) {
            logger.error("Failed to get Excel data: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Close workbook
     */
    public static void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
                logger.info("Workbook closed successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to close workbook: " + e.getMessage());
        }
    }
}
