package com.proyecto.springboot.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.proyecto.springboot.app.models.entity.Factura;
import com.proyecto.springboot.app.models.entity.ItemFactura;

@Component("factura/ver.xlsx")
public class FacturaXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"factura_.xlsx\"");
		Factura factura = (Factura)model.get("factura");
		
		MessageSourceAccessor messages = getMessageSourceAccessor();
		
		Sheet sheet = workbook.createSheet("Factura Spring");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue(messages.getMessage("text.factura.ver.datos.cliente"));
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre()+" "+factura.getCliente().getApellido());
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());
		
		//Otra forma encadenando los metodos
		sheet.createRow(4).createCell(0).setCellValue(messages.getMessage("text.factura.ver.datos.factura"));
		sheet.createRow(5).createCell(0).setCellValue(messages.getMessage("text.cliente.factura.folio")+": "+factura.getId());
		sheet.createRow(6).createCell(0).setCellValue(messages.getMessage("text.cliente.factura.descripcion")+": "+factura.getDescription());
		sheet.createRow(7).createCell(0).setCellValue(messages.getMessage("text.cliente.factura.fecha")+": "+factura.getCreateAt());
		
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.getIndex());
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(messages.getMessage("text.factura.form.item.nombre"));
		header.createCell(1).setCellValue(messages.getMessage("text.factura.form.item.precio"));
		header.createCell(2).setCellValue(messages.getMessage("text.factura.form.item.cantidad"));
		header.createCell(3).setCellValue(messages.getMessage("text.factura.form.item.total"));
		
		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);
		
		int rownum = 10;
		for (ItemFactura item : factura.getItems()) {
			Row rowItem = sheet.createRow(rownum ++);
			
			cell = rowItem.createCell(0);
			cell.setCellValue(item.getProducto().getNombre());
			cell.setCellStyle(tbodyStyle);
			
			cell = rowItem.createCell(1);
			cell.setCellValue(item.getProducto().getPrecio());
			cell.setCellStyle(tbodyStyle);
			
			cell = rowItem.createCell(2);
			cell.setCellValue(item.getCantidad());
			cell.setCellStyle(tbodyStyle);
			
			cell = rowItem.createCell(3);
			cell.setCellValue(item.calcularImporte());
			cell.setCellStyle(tbodyStyle);
		}
		
		Row rowTotal = sheet.createRow(rownum);
		cell = rowTotal.createCell(2);
		cell.setCellValue(messages.getMessage("text.factura.form.total")+": ");
		cell.setCellStyle(tbodyStyle);
		
		cell = rowTotal.createCell(3);
		cell.setCellValue(factura.getTotal().toString());
		cell.setCellStyle(tbodyStyle);
		
	}

}
