package com.abhikarma_rajani.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
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

public class FeesPdfExporter
{
	private List<Fees> listFees;

	public FeesPdfExporter(List<Fees> listFees)
	{
		this.listFees = listFees;
	}
	
	private void writeTableHeader(PdfPTable table)
	{
		PdfPCell cell= new PdfPCell();
		
		cell.setBackgroundColor(Color.RED);
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
		for (Fees fees : listFees)
		{
			table.addCell(String.valueOf(fees.getAdmissionId())); // for long, integer, float type data!!
			table.addCell(fees.getStudentName());
			table.addCell(fees.getClassName());
			table.addCell(fees.getGender());
			table.addCell(fees.getFeeType());
			table.addCell(String.valueOf(fees.getFeeAmount()));
			table.addCell(String.valueOf(fees.getPaidAmount()));
			table.addCell(String.valueOf(fees.getRemainingAmount()));
			table.addCell(fees.getFeesStatus());
			table.addCell(fees.getPaidDate());
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException
	{
		Document document= new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("LIST OF FEES DETAILS :"));
		PdfPTable table= new PdfPTable(10);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}
	
}
