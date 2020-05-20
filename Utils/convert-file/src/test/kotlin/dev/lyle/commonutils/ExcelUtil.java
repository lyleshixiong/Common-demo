package dev.lyle.commonutils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public final class ExcelUtil {

	/**
	 * 生成 exsel 工具類
	 * 
	 * @param filename
	 * @param tmptitle
	 * @param dataTitles
	 * @param list
	 * @throws IOException
	 * @author xiaopan 2012-12-18
	 */

	public final static void reportExcel(String filename, String tmptitle,
			String[] dataTitles, List<Object[]> list) throws IOException, WriteException {
		File file = new File(filename);
		OutputStream os = new FileOutputStream(file);
		WritableWorkbook wbook = null;
		WritableSheet wsheet = null;
		wbook = Workbook.createWorkbook(os);

		int k = 0;
		int row = 0;
		WritableFont wfont = new WritableFont(WritableFont.createFont("宋体"),
				20, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);

		WritableCellFormat wcfFC = new WritableCellFormat(wfont);

		wfont = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		wcfFC = new WritableCellFormat(wfont);
		try {
			wcfFC.setBorder(Border.NONE, BorderLineStyle.NONE);
		} catch (WriteException e) {

			e.printStackTrace();
		}
		Label label;

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				if (i % 50000 == 0) {

					wbook.createSheet(tmptitle + k, k);
					wsheet = wbook.getSheet(k);
					k++;
					mergeCellsAndInsertData(wsheet, 3, 0, 8, 0, tmptitle, wcfFC);
					for (int m = 0; m < dataTitles.length; m++) {

						// label = new Label(m, 1, dataTitles[m], wcfFC);//
						// 生成工作表标题
						label = new Label(m, 0, dataTitles[m], wcfFC);// 生成工作表标题

						try {
							wsheet.addCell(label);
						} catch (RowsExceededException e) {

							e.printStackTrace();
						} catch (WriteException e) {

							e.printStackTrace();
						}
					}

				}

				// wsheet = wbook.getSheet(k);
				row = i % 50000;
				Object[] ob = list.get(i);
				for (int l = 0; l < ob.length; l++) {
					// insertOne(wsheet, l, row + 2,
					// ob[l] == null ? "" : ob[l].toString(), wcfFC);
					insertOne(wsheet, l, row + 1,
							ob[l] == null ? "" : ob[l].toString(), wcfFC);
				}
			}
			wbook.write();
			wbook.close();
			os.close();
			return;
		}

		return;
	}

	/**
	 * 合并单元格，并插入数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param col_start
	 *            开始列
	 * @param row_start
	 *            开始行
	 * @param col_end
	 *            停止列
	 * @param row_end
	 *            停止行
	 * @param data
	 *            插入数据
	 * @param format
	 *            风格
	 */
	private final static void mergeCellsAndInsertData(WritableSheet sheet,
			Integer col_start, Integer row_start, Integer col_end,
			Integer row_end, Object data, WritableCellFormat format) {
		try {
			// sheet.mergeCells(col_start, row_start, col_end, row_end);//
			// 左上角到右下角
			insertOneCellData(sheet, col_start, row_start, data, format);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 简单插入单元格
	 * 
	 * @param sheet
	 *            工作表
	 * @param col
	 *            列
	 * @param row
	 *            行
	 * @param date
	 *            数据
	 * @param form
	 *            风格
	 */

	private final static void insertOne(WritableSheet sheet, Integer col,
			Integer row, String date, WritableCellFormat form) {
		try {
			Label label = new Label(col, row, date, form);
			sheet.addCell(label);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 插入单元格数据
	 * 
	 * @param sheet
	 *            工作表
	 * @param col
	 *            列号
	 * @param row
	 *            行号
	 * @param data
	 *            数据
	 */
	private final static void insertOneCellData(WritableSheet sheet,
			Integer col, Integer row, Object data, WritableCellFormat format) {
		try {
			if (data instanceof Double) {

				jxl.write.Number labelNF = new jxl.write.Number(col, row,
						(Double) data, format);
				sheet.addCell(labelNF);
			} else if (data instanceof Boolean) {
				jxl.write.Boolean labelB = new jxl.write.Boolean(col, row,
						(Boolean) data, format);
				sheet.addCell(labelB);
			} else if (data instanceof Date) {
				jxl.write.DateTime labelDT = new jxl.write.DateTime(col, row,
						(Date) data, format);
				sheet.addCell(labelDT);
				setCellComments(labelDT, "这是个创建表的日期说明！");
			} else {
				Label label = new Label(col, row, data.toString(), format);
				sheet.addCell(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 给单元格加注释
	 * 
	 * @param label
	 *            单元格
	 * @param comments
	 *            注释内容
	 */
	private final static void setCellComments(Object label, String comments) {
		WritableCellFeatures cellFeatures = new WritableCellFeatures();
		cellFeatures.setComment(comments); // 设置注释说明
		if (label instanceof jxl.write.Number) {
			jxl.write.Number num = (jxl.write.Number) label;
			num.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.Boolean) {
			jxl.write.Boolean bool = (jxl.write.Boolean) label;
			bool.setCellFeatures(cellFeatures);
		} else if (label instanceof jxl.write.DateTime) {
			jxl.write.DateTime dt = (jxl.write.DateTime) label;
			dt.setCellFeatures(cellFeatures);
		} else {
			Label _label = (Label) label;
			_label.setCellFeatures(cellFeatures);
		}
	}

	public final static void reportExcel(String filename,
			List<String> sheetNames, List<String[]> titles,List<List<Object[]>> data)
			throws Exception {
		File file = new File(filename);
		OutputStream os = new FileOutputStream(file);
		WritableWorkbook wbook = null;
		WritableSheet wsheet = null;
		wbook = Workbook.createWorkbook(os);

		int k = 0;
		int row = 0;
		WritableFont wfont = new WritableFont(WritableFont.createFont("宋体"),
				20, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);

		WritableCellFormat wcfFC = new WritableCellFormat(wfont);

		wfont = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		wcfFC = new WritableCellFormat(wfont);
		try {
			wcfFC.setBorder(Border.NONE, BorderLineStyle.NONE);
		} catch (WriteException e) {

			e.printStackTrace();
		}
		Label label;
		if (data != null && data.size() > 0) {
			for (int j = 0; j < data.size(); j++) {
				String tmpSheetName = sheetNames.get(j);
				List<Object[]> list = data.get(j);
				String[] title = titles.get(j);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						if (i % 50000 == 0) {
							wbook.createSheet(tmpSheetName + k, k);
							wsheet = wbook.getSheet(k);
							k++;
							mergeCellsAndInsertData(wsheet, 3, 0, 8, 0, title,
									wcfFC);
							for (int m = 0; m < title.length; m++) {
								label = new Label(m, 1, title[m], wcfFC);// 生成工作表标题
								try {
									wsheet.addCell(label);
								} catch (RowsExceededException e) {
									e.printStackTrace();
								} catch (WriteException e) {
									e.printStackTrace();
								}
							}
						}
						row = i % 50000;
						Object[] ob = list.get(i);
						for (int l = 0; l < ob.length; l++) {
							insertOne(wsheet, l, row + 2, ob[l] == null ? ""
									: ob[l].toString(), wcfFC);
						}
					}
				}
			}
		}
		wbook.write();
		wbook.close();
		os.close();
		return;
	}

}
