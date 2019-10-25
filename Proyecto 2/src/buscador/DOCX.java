package buscador;

import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
/**
 * Segundo Proyecto de algoritmos y estructuras de datos 1
 * Text Finder
 * @author sergio
 * @author ignacio
 *
 */
public class DOCX {
	XWPFWordExtractor extr;
	XWPFDocument docx;
	/**
	 * Funcion que convierte archivos de tipo DOCX a TXT
	 * @param filepath Direccion de archivo a convertir
	 * @return String con todo el archivo en formato TXT
	 */
	public String toText(String filepath) {
		try {
			
			docx =new XWPFDocument(new FileInputStream(filepath));
			extr=new XWPFWordExtractor(docx);
		} catch (Exception e) {
		}
		return extr.getText();
	}
	

}
