package buscador;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
/**
 * Segundo Proyecto de algoritmos y estructuras de datos 1
 * Text Finder
 * @author sergio
 * @author ignacio
 *
 */
public class PDFManager {

    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public PDFManager() {

    }
    /**
	 * Funcion que convierte archivos de tipo PD a TXT
	 * @param filepath Direccion de archivo a convertir
	 * @return String con todo el archivo en formato TXT
	 *@throws IOException
	 */
    public String toText(String filepath) throws IOException {
    	System.out.println("Pasando");
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;
        this.filePath=filepath;

        file = new File(filePath);
        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        Text = pdfStripper.getText(pdDoc);
        return Text;
    }
    
    
}