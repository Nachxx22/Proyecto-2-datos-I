package buscador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Segundo Proyecto de algoritmos y estructuras de datos 1
 * Text Finder
 * @author sergio
 * @author ignacio
 *
 */
public class Buscador extends JPanel {
	
	
	Lista lista=new Lista();
	JPanel v=new JPanel();
	JButton Buscar=new JButton("Buscar");
	JButton Archivos= new JButton("Archivos");
	JButton Quitar=new JButton("Quitar");
	
	JTextField TF=new JTextField();
	JTextArea TA=new JTextArea();
	JTextPane TP=new JTextPane();
	
	JFrame JF=new JFrame();
	JScrollPane scroll;
	JScrollPane scroll2=new JScrollPane(TP);
	
	JComboBox orden=new JComboBox();
	JLabel ordenes=new JLabel("Seleccione un orden: ");
	JLabel SelPal=new JLabel("Ingrese una letra o palabra");
	String formatted;
	String texto;
	String pal;
	
	/**
	 * Constructor de la clase, configura todos los paramentros de la ventana principal
	 */
	public Buscador() {
		
		v.setLayout(null);
		v.setBackground(Color.darkGray);
		JF.setBounds(50, 50, 600, 600);
		Buscar.setBounds(25,25,80,25);
		Archivos.setBounds(400,25,120,25);
		Quitar.setBounds(450,515,110,25);
		
		SelPal.setForeground(Color.white);
		SelPal.setBounds(185,0,200,25);
		TF.setBounds(185,25,155,25);
		
		TA.setBounds(15,100 ,550 ,400);
		TA.setEditable(false);
		ordenes.setForeground(Color.white);
		ordenes.setBounds(25,490,150,50);
		orden.setBounds(25, 525, 125, 25);
		orden.addItem("Fecha de creacion");
		orden.addItem("Nombre del archivo");
		orden.addItem("Peso del archivo");
		
		TA.setBackground(Color.white);
		scroll=new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(15,100 ,550 ,400);
		
		
		Buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lista.obt(0)==null) {
					System.out.println("Seleccione un archivo");
				}

				
				if(orden.getSelectedIndex()==0) {
					lista.bubblesort();
					archivos(lista);
				}else if(orden.getSelectedIndex()==1) {
					lista.Quicksort(lista);
					archivos(lista);
				}else {
					lista.radixsort(lista);
					archivos(lista);
				}
				
				
			}
		});
		Archivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				archivos();
			}
		});
		Quitar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitar();
			}
		});
		
		v.add(scroll);
		v.add(Archivos);
		v.add(Buscar);
		v.add(Quitar);
		v.add(TF);
		v.add(TA);
		v.add(orden);
		v.add(ordenes);	
		v.add(SelPal);
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JF.add(v);
		JF.setResizable(false);
		JF.setVisible(true);
	}
	/**
	 * Funcion para quitar archivos del Text Finder 
	 * configura parametros de la ventana desplegable
	 */
	public void quitar() {
		
		JPanel P3 =new JPanel();
		JFrame JF3=new JFrame();
		JComboBox JC =new JComboBox();
		JButton remover=new JButton("Remover");
		P3.setLayout(null);
		JF3.setBounds(550, 50, 300, 200);
		JC.setBounds(85,20,100,35);
		remover.setBounds(85,75,100,25);
		int i=0;
		while(i<lista.longitud()) {
			JC.addItem( lista.obt(i).getName().toString()  );
			i++;
		}
		remover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rutas = "";
				lista.eliminar(JC.getSelectedIndex());
				int o=0;
				if(lista.longitud()==0) {
					TA.setText("");
				}
				while(o<lista.longitud()) {
					rutas=rutas+lista.obt(o).getPath()+"\n";
					System.out.println("rutas: "+rutas);
					o++;
				}
				TA.setText(rutas);
				JF3.dispose();
				
			}
		});
		
		P3.add(remover);
		P3.add(JC);
		JF3.add(P3);
		JF3.setVisible(true);
	}
	
	
	/**
	 * Funcion que posibilita la busqueda de arhivos, posee un filtro de .TXT, .PDF y .DOCX
	 */
	public void archivos() {
		JFileChooser fc=new JFileChooser();
		
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("PDF, TXT, docx", "pdf","docx","txt");
		fc.setFileFilter(filtro);
		int seleccion=fc.showOpenDialog(v);
		
		if(seleccion==JFileChooser.APPROVE_OPTION){
			
		    File fichero=fc.getSelectedFile();
		    BasicFileAttributes attrs;
		    
		    try {
				attrs = Files.readAttributes(fichero.toPath(), BasicFileAttributes.class);
				FileTime time = attrs.creationTime();
				String forFecha = "dd";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(forFecha);
				formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		    lista.addLast(fichero);
		    TA.append(lista.getLast().getPath()+"\n");
		    scroll.getViewport().add(TA);
		}
	}
		
		/**
		 * Funcion que realiza la busqueda de la palabra en el texto y la resalta en otro color
		 * @param textComp Area donde se encuentra el texto completo a utilizar
		 * @param pattern Palabra a buscar en el texto
		 * @param ruta Ruta del archivo seleccionado
		 */
		public void pintar (JTextArea textComp, String pattern,String ruta) {
			
			
			MutableAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground(attr, Color.red);
			TP.setCharacterAttributes(attr, false);
			
			TP.setBounds(20,20,450,450);
			TP.setEditable(false);
			try {
				Document doc=textComp.getDocument();
				texto= doc.getText(0, doc.getLength());
				int pos=0;
				while( (pos =texto.toUpperCase().indexOf(pattern.toUpperCase(),pos))  >=0 ) {
					
					pal=(texto.substring(pos,pos+pattern.length()));
					String head="-->Palabra encontrada en: "+ruta+"      "+"\n";
					TP.getStyledDocument().insertString(TP.getStyledDocument().getLength(), head, null);
					
					TP.getStyledDocument().insertString(TP.getStyledDocument().getLength(),"..."+ texto.substring( (texto.indexOf(pattern,pos)-10),texto.indexOf(pattern,pos)),null);
					TP.getStyledDocument().insertString( TP.getStyledDocument().getLength(),pal,attr);
					TP.getStyledDocument().insertString(TP.getStyledDocument().getLength()+1,texto.substring( texto.indexOf(pattern,pos)+pattern.length(),texto.indexOf(pattern,pos)+10)+"...", null);
					TP.getStyledDocument().insertString( TP.getStyledDocument().getLength(),"\n",null);
					pos+=pattern.length();
				}	
			} catch (Exception e) {				
			}
		}
		/**
		 * Funcion que maneja los archivos seleccionados por el usuario
		 * Si algun archivo es de tipo PDF o DOCX, se realiza una transcripcion del mismo a formato TXT, para su correecto manejo
		 * @param lista Lista de todos los archivos seleccionados por el usuario
		 */
		public void archivos(Lista lista) {
			JPanel P2 =new JPanel();
			JFrame JF2=new JFrame();
			P2.setLayout(null);
			JF2.setBounds(550, 50, 600, 600);
			
			JTextArea prueba =new JTextArea();
			int contador=0;
			String texto;
			while(contador<lista.longitud()) {
				String tipo=lista.obt(contador).getAbsolutePath().toLowerCase();
				if(tipo.endsWith("pdf")) {
					PDFManager PDF=new PDFManager();
					try {
						texto=PDF.toText(lista.obt(contador).getPath());
						prueba.setText(texto);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(tipo.endsWith("docx")) {
					DOCX docs =new DOCX();
					texto=docs.toText(lista.obt(contador).getPath());
					prueba.setText(texto);
				}
				else {
					try(FileReader fr=new FileReader(lista.obt(contador))){
					System.out.println("TXT");
			        String cadena="";
			        int valor=fr.read();
			        while(valor!=-1){
			            cadena=cadena+(char)valor;
			            valor=fr.read();
			        }
			        texto=cadena;
			        prueba.setText(texto);
					
				}catch (IOException e) {
					e.printStackTrace();
				}
					}
				pintar(prueba,TF.getText(),lista.obt(contador).getName());
				contador++;
			}
			scroll2.setBounds(20,20,450,450);
			P2.add(scroll2);
			JF2.add(P2);
			JF2.setVisible(true);	
			
		}
}
