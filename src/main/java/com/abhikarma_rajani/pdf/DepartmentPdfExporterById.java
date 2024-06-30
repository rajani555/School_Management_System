package com.abhikarma_rajani.pdf;

import java.awt.Color;
import java.io.IOException;

import com.abhikarma_rajani.entity.Departments;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class DepartmentPdfExporterById
{
	private Departments printPdfWithId;

	public DepartmentPdfExporterById(Departments printPdfWithId) {
		super();
		this.printPdfWithId = printPdfWithId;
	}

	private void writeTableHeader(PdfPTable table)
	{
		PdfPCell cell= new PdfPCell();
		
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		Font font= FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		
		cell.setPhrase(new Phrase("DEPT ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("DEPT NAME", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("HEAD OF DEPT", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("DEPT START DATE", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("NUMBERS OF STUDENT", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table)
	{
			table.addCell(printPdfWithId.getDepartmentId());
			table.addCell(printPdfWithId.getDepartmentName());
			table.addCell(printPdfWithId.getHeadOfDepartment());
			table.addCell(printPdfWithId.getDepartmentStartDate());
			table.addCell(String.valueOf(printPdfWithId.getNumbersOfStudent())); // for long, integer, float type data!!
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException
	{
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("PARTICULAR DEPARTMENTS DETAILS :"));
		PdfPTable table= new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}
	
}
