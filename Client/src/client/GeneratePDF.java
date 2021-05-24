package client;

import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


public class GeneratePDF {
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public void crearPDF(File pdfNewFile, ArrayList<String> response, String ruta) throws DocumentException, IOException, FileNotFoundException {
        try {
            try {
                Document document = new Document(PageSize.LETTER, 30, 40, 40, 30);
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("No such file was found to generate the PDF "
                            + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
                }
                document.open();

                // Añadimos los metadatos del PDF
                document.addTitle("Archivos de la PC Remota");
                document.addSubject("Leonel Gonzalez Vidales");
                document.addKeywords("Java, PDF, iText");
                document.addAuthor("Leonel Gonzalez Vidales");
                document.addCreator("L3oNeT");
                // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF

                // First page
                // Primera página
                Chunk chunk = new Chunk("REPORTE DE ARCHIVOS PC Remota", chapterFont);

                // Let's create de first Chapter (Creemos el primer capítulo)
                Chapter chapter = new Chapter(new Paragraph(chunk), 1);
                chapter.setNumberDepth(0);
                chapter.add(new Phrase(Chunk.NEWLINE));

                // How to use PdfPTable
                // Utilización de PdfPTable

                // We use various elements to add title and subtitle
                // Usamos varios elementos para añadir título y subtítulo

                Paragraph paragraph = new Paragraph("Lista de Archivos", subcategoryFont);
                Section paragraphMore = chapter.addSection(paragraph);
                chapter.add(new Phrase(Chunk.NEWLINE));
                Integer numColumns = 1;


                // We create the table (Creamos la tabla).
                PdfPTable table = new PdfPTable(numColumns);
                // Now we fill the PDF table
                // Ahora llenamos la tabla del PDF
                PdfPCell columnHeader;
                // Encabezado de la columna.
                columnHeader = new PdfPCell(new Phrase("Ruta archivos " + ruta));
                columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(columnHeader);

                table.setHeaderRows(1);

                Iterator<String> Iterator = response.iterator();
                while(Iterator.hasNext()){
                    String elemento = Iterator.next();
                    table.addCell(elemento);

                }

                // We add the table (Añadimos la tabla)
                paragraphMore.add(table);
                // We add the paragraph with the table (Añadimos el elemento con la tabla).
                document.add(chapter);

                document.close();
                System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
            } catch (DocumentException documentException){
                System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
            }
        } finally {
            //out.close();
        }
    }
}