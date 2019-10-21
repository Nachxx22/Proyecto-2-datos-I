package buscador;

import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DOCX {
	XWPFWordExtractor extr;
	XWPFDocument docx;
	public String toText(String filepath) {
		try {
			
			docx =new XWPFDocument(new FileInputStream(filepath));
			extr=new XWPFWordExtractor(docx);
		} catch (Exception e) {
		}
		return extr.getText();
	}
	

}
