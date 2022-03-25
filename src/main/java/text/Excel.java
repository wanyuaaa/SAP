package text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	public static void main(String[] args) throws IOException {

		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());

		String parentPath = "E:\\pic_dir\\";
		String context = "院外来款查询";
		File downloadFile = new File(parentPath, context + ".xls");

		if (downloadFile.exists()) {
			downloadFile.delete();
		}

		downloadFile.createNewFile();
		OutputStream os = new FileOutputStream(downloadFile);

		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(os);
			// 生成名为"第一页"的工作表，参数0表示这是第一
			WritableSheet sheet = book.createSheet(context, 0);

			// sheet.setColumnView(1, 20);
			// sheet.setColumnView(2, 20);
			// sheet.setColumnView(3, 20);
			// sheet.setColumnView(4, 15);

			/**
			 * 设置表格表头样式
			 */
			WritableFont font1 = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			font1.setColour(Colour.BLACK);// 表格字体颜色
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setBackground(Colour.GRAY_25);// 表格背景颜色
			format1.setBorder(Border.ALL, BorderLineStyle.THIN);// 表格线条加黑
			format1.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format1.setWrap(true);// 单元格的文字按照单元格的列宽来自动换行显示

			/**
			 * 设置表格内容样式
			 */
			// 设置字体为宋体,16号字,加粗,颜色为黑色
			WritableFont font2 = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD);
			font2.setColour(Colour.BLACK);// 表格字体颜色
			WritableCellFormat format2 = new WritableCellFormat(font2);
			// format2.setBackground(Colour.GRAY_25);// 表格背景颜色
			format2.setBorder(Border.ALL, BorderLineStyle.THIN);// 表格线条加黑
			format2.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format2.setWrap(true);// 单元格的文字按照单元格的列宽来自动换行显示

			// 设置字体为宋体,16号字,加粗,颜色为黑色
			WritableFont font3 = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			font3.setColour(Colour.BLACK);// 表格字体颜色
			WritableCellFormat format3 = new WritableCellFormat(font2);
			format3.setBackground(Colour.GRAY_25);// 表格背景颜色
			format3.setBorder(Border.ALL, BorderLineStyle.THIN);// 表格线条加黑
			format3.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format3.setWrap(true);// 单元格的文字按照单元格的列宽来自动换行显示

			Label labelA = new Label(0, 0, "出差补助报销表", format1);
			sheet.addCell(labelA);
			sheet.mergeCells(0, 0, 8, 1);
			Label labelB = new Label(0, 2, "ID", format2);
			sheet.addCell(labelB);
			sheet.mergeCells(0, 2, 0, 3);
			Label labelC = new Label(1, 2, "//ID", format2);
			sheet.addCell(labelC);
			sheet.mergeCells(1, 2, 3, 3);
			Label labelD = new Label(4, 2, "申请人员", format2);
			sheet.addCell(labelD);
			sheet.mergeCells(4, 2, 5, 3);
			Label labelE = new Label(6, 2, "//申请人员", format2);
			sheet.addCell(labelE);
			sheet.mergeCells(6, 2, 8, 3);
			Label labelF = new Label(0, 4, "创建时间", format2);
			sheet.addCell(labelF);
			sheet.mergeCells(0, 4, 0, 5);
			Label labelG = new Label(1, 4, "//申请创建时间", format2);
			sheet.addCell(labelG);
			sheet.mergeCells(1, 4, 3, 5);
			Label labelH = new Label(0, 6, "出发日期", format2);
			sheet.addCell(labelH);
			sheet.mergeCells(0, 6, 0, 7);
			Label labelI = new Label(1, 6, "//出发日期", format2);
			sheet.addCell(labelI);
			sheet.mergeCells(1, 6, 3, 7);
			Label labelJ = new Label(0, 8, "返回日期", format2);
			sheet.addCell(labelJ);
			sheet.mergeCells(0, 8, 0, 9);
			Label labelK = new Label(1, 8, "//返回日期", format2);
			sheet.addCell(labelK);
			sheet.mergeCells(1, 8, 3, 9);
			Label labelL = new Label(4, 4, "出差地点", format2);
			sheet.addCell(labelL);
			sheet.mergeCells(4, 4, 5, 5);
			Label labelM = new Label(4, 6, "出差事由", format2);
			sheet.addCell(labelM);
			sheet.mergeCells(4, 6, 5, 9);
			Label labelN = new Label(6, 4, "//出差地点", format2);
			sheet.addCell(labelN);
			sheet.mergeCells(6, 4, 8, 5);
			Label labelO = new Label(6, 6, "//出差事由", format2);
			sheet.addCell(labelO);
			sheet.mergeCells(6, 6, 8, 9);
			Label labelP = new Label(0, 10, "补助标准（元）", format2);
			sheet.addCell(labelP);
			sheet.mergeCells(0, 10, 2, 11);
			Label labelQ = new Label(3, 10, "//补助标准", format2);
			sheet.addCell(labelQ);
			sheet.mergeCells(3, 10, 4, 11);
			Label labelR = new Label(5, 10, "天数（天）", format2);
			sheet.addCell(labelR);
			sheet.mergeCells(5, 10, 6, 11);
			Label labelS = new Label(7, 10, "//天数（天）", format2);
			sheet.addCell(labelS);
			sheet.mergeCells(7, 10, 8, 11);
			Label labelT = new Label(0, 12, "合计金额（大写）", format2);
			sheet.addCell(labelT);
			sheet.mergeCells(0, 12, 2, 13);
			Label labelU = new Label(3, 12, "//合计金额（大写）", format2);
			sheet.addCell(labelU);
			sheet.mergeCells(3, 12, 8, 13);
			Label labelV = new Label(0, 14, "经办人", format3);
			sheet.addCell(labelV);
			sheet.mergeCells(0, 14, 1, 15);
			Label labelW = new Label(2, 14, "职务", format3);
			sheet.addCell(labelW);
			sheet.mergeCells(2, 14, 3, 15);
			Label labelX = new Label(4, 14, "审核记录", format3);
			sheet.addCell(labelX);
			sheet.mergeCells(4, 14, 6, 15);
			Label labelY = new Label(7, 14, "审核时间", format3);
			sheet.addCell(labelY);
			sheet.mergeCells(7, 14, 8, 15);
			Label labelZ = new Label(0, 24, "山西欣思威科贸有限公司;" + datetime, format3);
			sheet.addCell(labelZ);
			sheet.mergeCells(0, 24, 8, 25);
			Label labelA1 = new Label(0, 16, "刘田", format2);
			sheet.addCell(labelA1);
			sheet.mergeCells(0, 16, 1, 17);
			Label labelA2 = new Label(2, 16, "主管", format2);
			sheet.addCell(labelA2);
			sheet.mergeCells(2, 16, 3, 17);
			Label labelA3 = new Label(4, 16, "//审核记录", format2);
			sheet.addCell(labelA3);
			sheet.mergeCells(4, 16, 6, 17);
			Label labelA4 = new Label(7, 16, "//审核时间", format2);
			sheet.addCell(labelA4);
			sheet.mergeCells(7, 16, 8, 17);
			Label labelB1 = new Label(0, 18, "连雅琳", format2);
			sheet.addCell(labelB1);
			sheet.mergeCells(0, 18, 1, 19);
			Label labelB2 = new Label(2, 18, "会计", format2);
			sheet.addCell(labelB2);
			sheet.mergeCells(2, 18, 3, 19);
			Label labelB3 = new Label(4, 18, "//审核记录", format2);
			sheet.addCell(labelB3);
			sheet.mergeCells(4, 18, 6, 19);
			Label labelB4 = new Label(7, 18, "//审核时间", format2);
			sheet.addCell(labelB4);
			sheet.mergeCells(7, 18, 8, 19);
			Label labelC1 = new Label(0, 20, "康军", format2);
			sheet.addCell(labelC1);
			sheet.mergeCells(0, 20, 1, 21);
			Label labelC2 = new Label(2, 20, "总经理", format2);
			sheet.addCell(labelC2);
			sheet.mergeCells(2, 20, 3, 21);
			Label labelC3 = new Label(4, 20, "//审核记录", format2);
			sheet.addCell(labelC3);
			sheet.mergeCells(4, 20, 6, 21);
			Label labelC4 = new Label(7, 20, "//审核时间", format2);
			sheet.addCell(labelC4);
			sheet.mergeCells(7, 20, 8, 21);
			Label labelD1 = new Label(0, 22, "孙丽萍", format2);
			sheet.addCell(labelD1);
			sheet.mergeCells(0, 22, 1, 23);
			Label labelD2 = new Label(2, 22, "财务", format2);
			sheet.addCell(labelD2);
			sheet.mergeCells(2, 22, 3, 23);
			Label labelD3 = new Label(4, 22, "//审核记录", format2);
			sheet.addCell(labelD3);
			sheet.mergeCells(4, 22, 6, 23);
			Label labelD4 = new Label(7, 22, "//审核时间", format2);
			sheet.addCell(labelD4);
			sheet.mergeCells(7, 22, 8, 23);

			book.write();
			book.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		os.close();
	}
}
