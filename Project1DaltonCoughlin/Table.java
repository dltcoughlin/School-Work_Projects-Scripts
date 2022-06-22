// Packages to import
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

 
public class Table {
    // frame
    JFrame f;
    // Table
    JTable j;
    Table(String data[][])
    {
        f = new JFrame();
        f.setTitle("Bubble Sort");
        String[] columnNames = { "Size", "Avg Move Count", "Coef Count %", "Avg Function Time", "Avg Function Coef %" };
        j = new JTable(data, columnNames);
        j.setBounds(60, 60, 400, 400);
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(600, 300);
        f.setVisible(true);
    }
    public static void main(String[] args)
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(fileChooser);
        File selectedFile = fileChooser.getSelectedFile();
        //building 2d array from input file
        try {
            File myObj = new File(selectedFile.getAbsolutePath());
            Scanner myReader = new Scanner(myObj);
            List<String> lines
            = new ArrayList<String>();
            while (myReader.hasNext()) {
                String str = myReader.next();
                lines.add(str);
            }
            myReader.close();
            String[] arraySplit;
            String data[][] = new String[10][5];
            for (int i = 0; i < lines.size(); i++) {
                arraySplit = lines.get(i).split(",");
                data[i] = arraySplit;
            }
            new Table(data);
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }
}
