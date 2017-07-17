package cn.huchao.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * 2017年7月4日  huchao
 *	@description
 *导出为Excel文件的工具类
 * ，暂时还没有测试正确与否，这个是用浏览器下载的Excel，需要注意当文件名为中文的时候，考虑
 * 乱码问题，和IE，火狐，等
 */
public class ExportExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);

	/**
	 * 将数据转成Excel文件
	 * 
	 * @author huchao
	 * @param dataMapList
	 * @param os
	 * @param titleNames
	 *            ，第一行，列标题
	 * @param keysName，数据map的键值，顺序要与列标题对应
	 * @param reportName，表格标题名称
	 */
	public static void exportExcel(List<Map<String, Object>> dataMapList, OutputStream os, String[] titleNames,
			String[] keysName, String reportName) {
		// 创建工作薄
		WritableWorkbook workbook = null;
		long startTime = System.currentTimeMillis();
		Integer count = 1;
		try {
			workbook = Workbook.createWorkbook(os);
			// 设置标题
			WritableSheet sheet = workbook.createSheet(reportName, 0);
			// 数据格式
			WritableCellFormat format = getFormat();
			// 设置列宽
			sheet.setColumnView(0, 8);
			for (int i = 1; i <= titleNames.length + 1; i++) {
				sheet.setColumnView(i, 27);
			}
			// 设置报表头部
			int row = setReportHeader(sheet, format, reportName, titleNames);

			for (Map<String, Object> map : dataMapList) {
				int col = 1;
				// 序号
				sheet.addCell(new Label(col++, row, count.toString(), format));
				// 具体数据内容
				for (String key : keysName) {
					if (null != map.get(key)) {
						sheet.addCell(new Label(col++, row, map.get(key).toString(), format));
					} else {
						sheet.addCell(new Label(col++, row, "", format));
					}
				}
				row++;
				count++;
			}
			long endTime = System.currentTimeMillis();
			logger.debug("downOrderLogList sheet addCell Times:" + (endTime - startTime));
		} catch (Exception e) {
			logger.error("生成 " + reportName + "报表出错", e);
		} finally {
			if (null != workbook) {
				try {
					workbook.write();
					workbook.close();
					long endTime = System.currentTimeMillis();
					logger.info("downOrderLogList close workbook Times" + (endTime - startTime));
				} catch (IOException e) {
					logger.error("downOrderLogList close workbook exception", e);
				}
				workbook = null;
			}
			if (null != os) {
				try {
					os.flush();
					os.close();
					long endTime = System.currentTimeMillis();
					logger.info("downPayTransLogList close outputStream Times" + (endTime - startTime));
				} catch (IOException e) {
					logger.error("downOrderLogList close outputStream exception", e);
				}
			}
		}
		// 创建新的一页
	}

	/**
	 * 设置报表头部信息
	 *
	 * @param sheet
	 *            sheet页
	 * @param topTitle
	 *            顶部标题
	 * @param sumInfo
	 *            汇总信息
	 * @param titleNames
	 *            标题列表
	 * @return
	 * @2017年5月27日
	 * @author huchao
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws Exception
	 */
	private static int setReportHeader(WritableSheet sheet, WritableCellFormat format, String topTitle,
			String[] titleNames) throws RowsExceededException, WriteException {
		int row = 1;
		// 合并一级标题行,顶部空一行
		sheet.mergeCells(1, row, titleNames.length, row);
		sheet.addCell(new Label(1, row, topTitle, format));
		row++;
		int col = 1;
		// 添加列标题
		for (String titleName : titleNames) {
			sheet.addCell(new Label(col++, row, titleName, format));
		}
		row++;
		return row;
	}

/**
 * 标题格式
 *  2017年7月4日  huchao
 * @return
 */
	private static WritableCellFormat getFormat() {
		/*
		 * WritableFont.createFont("宋体")：设置字体为宋体 10：设置字体大小
		 * WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗 NO_BOLD：不加粗） false：设置非斜体
		 * UnderlineStyle.NO_UNDERLINE：没有下划线
		 */
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// 设置单元格背景色：表体为白色
			bodyFormat.setBackground(Colour.WHITE);
			// 设置表头表格边框样式
			// 整个表格线为细线、黑色
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			bodyFormat.setAlignment(Alignment.CENTRE);

		} catch (WriteException e) {
			logger.error("表体单元格样式设置失败！", e);
		}
		return bodyFormat;
	}

	/**
	 * 导出下载为Excel
	 *  2017年7月4日  huchao
	 * @param response
	 * @param orderList
	 * @param titleNames
	 * @param keysName
	 * @param reportName
	 */
	public static void exportData(HttpServletResponse response, List<Map<String, Object>> orderList,
			String[] titleNames, String[] keysName, String reportName) {
		// 将结果转换成Excel文件
		// 文件名为时间戳
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = dateFormat.format(date) + ".xls";
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + response.encodeURL(fileName));
		// 转成Excel下载
		try {
			OutputStream outputStream = response.getOutputStream();
			exportExcel(orderList, outputStream, titleNames, keysName, reportName);
		} catch (Exception e) {
			logger.error("转换成Excel文件，提供下载，出错", e);
		} finally {
			// 清空trandsOrderVoList
			orderList.clear();
		}
	}

	public static void main(String[] args) {
		String[] titleNames = { "序号", "订单号", "流水号", "活动号", "支付渠道", "支付金额（元）", "支付状态", "创建时间", "完成时间", "客户姓名", "客户手机号码",
				"客户公司名称", "客户地市", "客户备注", "活动类型", "商品名", "办理人员", "人员信息" };
		String[] keysName = { "a", "b" };
	}
}
