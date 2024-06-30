package com.abhikarma_rajani.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

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

public class DepartmentsPdfExporter
{
	private List<Departments> listDepartments;

	public DepartmentsPdfExporter(List<Departments> listlistDepartments)
	{
		this.listDepartments = listlistDepartments;
	}
	
	private void writeTableHeader(PdfPTable table)
	{
		PdfPCell cell= new PdfPCell();
		
		cell.setBackgroundColor(Color.RED);
		cell.setPadding(5);
		Font font= FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		
		cell.setPhrase(new Phrase("DEPARTMENTS ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("DEPARTMENTS NAME", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("HEAD OF DEPARTMENTS", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("DEPARTMENTS START DATE", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("NUMBERS OF STUDENT", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table)
	{
		for (Departments departments : listDepartments)
		{
			table.addCell(departments.getDepartmentId());
			table.addCell(departments.getDepartmentName());
			table.addCell(departments.getHeadOfDepartment());
			table.addCell(departments.getDepartmentStartDate());
			table.addCell(String.valueOf(departments.getNumbersOfStudent())); // for long, integer, float type data!!
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException
	{
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("LIST OF DEPARTMENTS"));
		PdfPTable table= new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}
	
	
}
