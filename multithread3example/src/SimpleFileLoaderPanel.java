import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * Created by USER on 21.11.2018.
 */
public class SimpleFileLoaderPanel extends JPanel {
    private JButton loadButton;
    private JPanel loadedFilesPanel;
    private JFileChooser fileChooser;

    private File[] loadedFiles;

    SimpleFileLoaderPanel() {
        setPreferredSize(new Dimension(320, 240));
        loadButton = new JButton("load files");
        loadedFilesPanel = new JPanel();
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        loadedFilesPanel.setLayout(new BoxLayout(loadedFilesPanel, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        add(loadButton, BorderLayout.SOUTH);
        add(loadedFilesPanel, BorderLayout.CENTER);

        loadButton.addActionListener(e -> {
            loadFiles();
        });
    }

    private void loadFiles() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadedFiles = fileChooser.getSelectedFiles();
            for (int i = 0; i < loadedFiles.length; i++) {
                System.out.printf("файл для загрузки: %s%n", loadedFiles[i].getName());
                new Thread(new FileLoader(i)).start();
            }
        }
    }

    private class FileLoader extends JPanel implements Runnable{
        private JTextArea loadedFileInfo;
        private int fileIndex, strokesCount;
        private String nextLine, fileText;
        private BufferedReader in;

        FileLoader(int fileIndex) {
            this.fileIndex = fileIndex;
        }

        public void run() {
            try {
                in = new BufferedReader(new FileReader(loadedFiles[fileIndex]));
                loadedFileInfo = new JTextArea(loadedFiles[fileIndex].getName());
                loadedFilesPanel.add(loadedFileInfo);
                nextLine = in.readLine();

                while (nextLine != null) {
                    strokesCount++;
                    loadedFileInfo.setText(loadedFiles[fileIndex].getName() + " загружен на " + strokesCount + " строк");
                    Thread.sleep((long) (Math.random() * 5000 + 1000));
                    fileText += nextLine;
                    nextLine = in.readLine();
                    revalidate();
                }

                loadedFileInfo.setText(loadedFiles[fileIndex].getName() + " загружен!");
                loadedFileInfo.setBorder(new LineBorder(Color.blue));
                loadedFileInfo.addMouseListener(new MouseAdapter() {
                    JFrame loadedFileFrame;
                    JTextArea loadedFileTextArea;
                    JScrollPane loadedFileScrollPane;

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loadedFileFrame = new JFrame(loadedFiles[fileIndex].getName());
                        loadedFileTextArea = new JTextArea(20, 40);
                        loadedFileTextArea.setText(fileText);
                        loadedFileScrollPane = new JScrollPane(loadedFileTextArea);

                        loadedFileFrame.getContentPane().add(loadedFileScrollPane);
                        loadedFileFrame.setLocationRelativeTo(SimpleFileLoaderPanel.this);
                        loadedFileFrame.pack();
                        loadedFileFrame.setVisible(true);
                    }
                });
                revalidate();
            }catch (Exception e){
                System.out.println("Something wrong " + e.getLocalizedMessage());
            }

        }
    }
}
