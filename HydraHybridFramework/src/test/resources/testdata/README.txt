# ================================================
# Hydra Hybrid Framework - Sample Excel Test Data
# ================================================
# This file demonstrates the structure for Excel-based data-driven testing
# Create an Excel file with the following structure:
#
# Row 1 (Headers):  | username | password | firstName | lastName |
# Row 2 (Data 1):   | user1 | pass123 | John | Doe |
# Row 3 (Data 2):   | user2 | pass456 | Jane | Smith |
#
# Usage in Test:
# List<Map<String, String>> testData = ExcelUtils.getExcelData("path/to/excel.xlsx", "SheetName");
# for (Map<String, String> data : testData) {
#     String username = data.get("username");
#     String password = data.get("password");
#     // Use in test
# }
# ================================================
