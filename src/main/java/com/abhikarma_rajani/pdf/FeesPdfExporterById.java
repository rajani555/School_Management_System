package com.abhikarma_rajani.pdf;

import java.awt.Color;
import java.io.IOException;

import com.abhikarma_rajani.entity.Fees;
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

public class FeesPdfExporterById
{
	private Fees printPdfWithId;

	public FeesPdfExporterById(Fees printPdfWithId)
	{
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
		
		
		cell.setPhrase(new Phrase("ADMISSION ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("STUDENT NAME", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("CLASS NAME", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("GENDER", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("FEES TYPE", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("FEES AMOUNT", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("PAID AMOUNT", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("REMAINING AMOUNT", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("FEES STATUS", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("FEES PAID DATE", font));
		table.addCell(cell);
	}
	
	private void writeTableData(PdfPTable table)
	{
		table.addCell(String.valueOf(printPdfWithId.getAdmissionId())); // for long, integer, float type data!!
		table.addCell(printPdfWithId.getStudentName());
		table.addCell(printPdfWithId.getClassName());
		table.addCell(printPdfWithId.getGender());
		table.addCell(printPdfWithId.getFeeType());
		table.addCell(String.valueOf(printPdfWithId.getFeeAmount()));
		table.addCell(String.valueOf(printPdfWithId.getPaidAmount()));
		table.addCell(String.valueOf(printPdfWithId.getRemainingAmount()));
		table.addCell(printPdfWithId.getFeesStatus());
		table.addCell(printPdfWithId.getPaidDate());
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException
	{
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("PARTICULAR FEES DETAILS :"));
		PdfPTable table= new PdfPTable(10);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}
	
}
