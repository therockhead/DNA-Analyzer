import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JTable;
import javax.swing.JProgressBar;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.border.MatteBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputMotif;
	JLabel fileChoosenDialog;
	File selectedFile;
	private String selectedFilePath;
	public String sequence;
	public int countMotif;
	public JLabel motifCount;
	public double percentage;
	public int gcPercentage;
	
	public JLabel motifPercentage;
	public JLabel motifDisplayLabel;
	public JLabel gcContent;
	
	public JLabel adeninPercentage;
	public JLabel guaninPercentage;
	public JLabel cytosinPercentage;
	public JLabel thyminPercentage;
	JProgressBar progressBar;

	/*
	 * Motif Counter Function
	 */
	public int countMotifOccurrences(String dna, String motif) {
		int count = 0;
		int index = 0;

		while ((index = dna.indexOf(motif, index)) != -1) { // Find motif occurrence
			count++;
			index += 1; 
		}

		return count;
	}
	
	/*
	 * GC content percentage function
	 */
	public int gcContentPercentageFunc(String dna) {
		if (dna.isEmpty()) {
	        return 0; // Return 0% if the DNA sequence is empty
	    }

	    int count = 0;
	    dna = dna.toUpperCase();

	    for (char nucleotide : dna.toCharArray()) {
	        if (nucleotide == 'G' || nucleotide == 'C') {
	            count++;
	        }
	    }

	    
	    double p = ((double) count / dna.length()) * 100;
	    
	    int a = (int)p;

	    
	    return a;
	}
	/*
	 * adenine Nucleotide Percentage
	 */
	public int adeninPercentageFunc(String dna) {
		if (dna.isEmpty()) {
	        return 0; // Return 0% if the DNA sequence is empty
	    }

	    int count = 0;
	    dna = dna.toUpperCase();

	    for (char nucleotide : dna.toCharArray()) {
	        if (nucleotide == 'A' || nucleotide == 'a') {
	            count++;
	        }
	    }

	    
	    double p = ((double) count / dna.length()) * 100;
	    
	    int a = (int)p;

	    
	    return a;
	}
	/*
	 * guanine Nucleotide Percentage
	 */
	public int guaninPercentageFunc(String dna) {
		if (dna.isEmpty()) {
	        return 0; 
	    }

	    int count = 0;
	    dna = dna.toUpperCase();

	    for (char nucleotide : dna.toCharArray()) {
	        if (nucleotide == 'G' || nucleotide == 'g') {
	            count++;
	        }
	    }

	    
	    double p = ((double) count / dna.length()) * 100;
	    
	    int a = (int)p;

	    
	    return a;
	}
	/*
	 * Cytosine Nucleotide Percentage
	 */
	public int cytosinPercentageFunc(String dna) {
		if (dna.isEmpty()) {
	        return 0; 
	    }

	    int count = 0;
	    dna = dna.toUpperCase();

	    for (char nucleotide : dna.toCharArray()) {
	        if (nucleotide == 'C' || nucleotide == 'c') {
	            count++;
	        }
	    }

	    
	    double p = ((double) count / dna.length()) * 100;
	    
	    int a = (int)p;

	    
	    return a;
	}
	/*
	 * Thymine Nucleotide Percentage
	 */
	public int thyminPercentageFunc(String dna) {
		if (dna.isEmpty()) {
	        return 0; 
	    }

	    int count = 0;
	    dna = dna.toUpperCase();

	    for (char nucleotide : dna.toCharArray()) {
	        if (nucleotide == 'T' || nucleotide == 't') {
	            count++;
	        }
	    }

	    
	    double p = ((double) count / dna.length()) * 100;
	    
	    int a = (int)p;

	    
	    return a;
	}


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		
		setResizable(false);
		
		setBackground(Color.BLACK);
		setTitle("DNA Analyzer Tool");
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\icon.png"));
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 591);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel chooseFileLabel = new JLabel("Choose Sequence File (.txt) :");
		chooseFileLabel.setForeground(Color.GREEN);
		chooseFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chooseFileLabel.setBounds(10, 158, 197, 16);
		contentPane.add(chooseFileLabel);

		JLabel topLabel = new JLabel("DNA Analyzer");
		topLabel.setBackground(Color.BLACK);
		topLabel.setForeground(new Color(255, 255, 255));
		topLabel.setBounds(72, 58, 299, 24);
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 35));
		contentPane.add(topLabel);

		JButton browseButton = new JButton("");
		browseButton.setBackground(Color.BLACK);
		browseButton.setIcon(new ImageIcon("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\browse.png"));
		browseButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		browseButton.setBorderPainted(false);  
		browseButton.setFocusPainted(false);   
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select a Text File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);

				fileChooser.setFileFilter(
						new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));

				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					fileChoosenDialog.setText(selectedFile.getAbsolutePath());
					selectedFilePath = selectedFile.getAbsolutePath();
					// System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

			}
		});
		browseButton.setFocusable(false);
		browseButton.setBounds(217, 152, 34, 29);
		contentPane.add(browseButton);

		fileChoosenDialog = new JLabel("No File Choosen");
		fileChoosenDialog.setForeground(Color.GREEN);
		fileChoosenDialog.setBounds(264, 160, 330, 14);
		contentPane.add(fileChoosenDialog);

		JLabel motifLabel = new JLabel("Desired Motif :");
		motifLabel.setForeground(Color.GREEN);
		motifLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		motifLabel.setBounds(10, 199, 98, 14);
		contentPane.add(motifLabel);

		inputMotif = new JTextField();
		inputMotif.setForeground(Color.GREEN);
		inputMotif.setBackground(Color.DARK_GRAY);
		inputMotif.setFont(new Font("Tahoma", Font.BOLD, 15));
		inputMotif.setBounds(108, 192, 431, 29);
		contentPane.add(inputMotif);
		inputMotif.setColumns(10);
		
		
		
        ((AbstractDocument) inputMotif.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                StringBuilder filteredText = new StringBuilder();
                for (char c : text.toCharArray()) {
                	char upperC = Character.toUpperCase(c); 
                    if ("ATGC".indexOf(upperC) >= 0) { 
                        filteredText.append(upperC);
                    }
                }
                super.replace(fb, offset, length, filteredText.toString(), attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
                    throws BadLocationException {
                replace(fb, offset, 0, text, attrs);
            }
        });

		JButton findButton = new JButton("");
		findButton.setBackground(Color.BLACK);
		findButton.setBorderPainted(false); 
		findButton.setFocusPainted(false);   
		findButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String motif = inputMotif.getText().trim().toUpperCase();
		        if (motif.isEmpty()) {
		            JOptionPane.showMessageDialog(Home.this, "Please enter a motif!", "Warning",
		                    JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
		            progressBar.setIndeterminate(true); // Start loading animation
		            findButton.setEnabled(false); // Disable button during processing

		            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
		                @Override
		                protected Void doInBackground() {
		                    try (BufferedReader reader = new BufferedReader(new FileReader(selectedFilePath))) {
		                        StringBuilder dnaSequence = new StringBuilder();
		                        String line;
		                        while ((line = reader.readLine()) != null) {
		                            dnaSequence.append(line.trim());
		                        }
		                        sequence = dnaSequence.toString();

		                        // Motif Count
		                        countMotif = countMotifOccurrences(sequence, motif);
		                        String c = String.valueOf(countMotif);

		                        // Motif Percentage
		                        percentage = (((double) (countMotif * motif.length()) / sequence.length()) * 100);

		                        // GC Content
		                        gcPercentage = gcContentPercentageFunc(sequence);

		                        // Nucleotide Percentages
		                        int adeno = adeninPercentageFunc(sequence);
		                        int cyto = cytosinPercentageFunc(sequence);
		                        int guano = guaninPercentageFunc(sequence);
		                        int thy = thyminPercentageFunc(sequence);

		                        // Simulate progress
		                        for (int i = 0; i <= 100; i += 10) {
		                            Thread.sleep(100); // Simulate computation time
		                            progressBar.setValue(i);
		                        }

		                        // Update UI components
		                        SwingUtilities.invokeLater(() -> {
		                            motifDisplayLabel.setText(motif);
		                            motifCount.setText(c);
		                            motifPercentage.setText((int) percentage + "%");
		                            gcContent.setText(gcPercentage + "%");
		                            adeninPercentage.setText(adeno + "%");
		                            thyminPercentage.setText(thy + "%");
		                            guaninPercentage.setText(guano + "%");
		                            cytosinPercentage.setText(cyto + "%");
		                        });

		                    } catch (IOException | InterruptedException ex) {
		                        JOptionPane.showMessageDialog(Home.this, "Error reading file!", "Error",
		                                JOptionPane.ERROR_MESSAGE);
		                    }
		                    return null;
		                }

		                @Override
		                protected void done() {
		                    progressBar.setIndeterminate(false);
		                    progressBar.setValue(100);
		                    findButton.setEnabled(true);
		                }
		            };

		            worker.execute(); // Start background task
		        } else {
		            JOptionPane.showMessageDialog(Home.this, "Please select a file first!", "Warning",
		                    JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});

		findButton.setIcon(new ImageIcon("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\find.png"));
		findButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		findButton.setBounds(552, 189, 42, 32);
		contentPane.add(findButton);

		JButton clearButton = new JButton("");
		clearButton.setBackground(Color.BLACK);
		clearButton.setBorderPainted(false);  
		clearButton.setFocusPainted(false);   
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectedFile = null;
				fileChoosenDialog.setText("No file choosen");
				
				inputMotif.setText("");
				
				motifDisplayLabel.setText("----");
				motifCount.setText("0");
				motifPercentage.setText("0%");
				gcContent.setText("0%");
				adeninPercentage.setText("0%");
				guaninPercentage.setText("0%");
				cytosinPercentage.setText("0%");
				thyminPercentage.setText("0%");
				
				progressBar.setValue(0);
				
			}
		});
		clearButton
				.setSelectedIcon(new ImageIcon("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\clean.png"));
		clearButton.setIcon(new ImageIcon("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\clean (1).png"));
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearButton.setBounds(530, 487, 42, 41);
		contentPane.add(clearButton);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(10, 454, 581, 14);
		contentPane.add(progressBar);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.GREEN);
		panel1.setBounds(154, 237, 144, 100);
		contentPane.add(panel1);
		panel1.setLayout(null);

		motifCount = new JLabel("0");
		motifCount.setBounds(0, 12, 131, 49);
		panel1.add(motifCount);
		motifCount.setHorizontalAlignment(SwingConstants.CENTER);
		motifCount.setFont(new Font("Tahoma", Font.BOLD, 40));

		JLabel motifCountOutput = new JLabel("Motif Count");
		motifCountOutput.setBounds(23, 72, 82, 17);
		panel1.add(motifCountOutput);
		motifCountOutput.setFont(new Font("Tahoma", Font.BOLD, 14));
		motifCountOutput.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBackground(Color.GREEN);
		panel2.setBounds(305, 237, 135, 100);
		contentPane.add(panel2);

		motifPercentage = new JLabel("0%");
		motifPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		motifPercentage.setFont(new Font("Tahoma", Font.BOLD, 40));
		motifPercentage.setBounds(0, 11, 134, 49);
		panel2.add(motifPercentage);

		JLabel motifPercentageOutput = new JLabel("Motif Count");
		motifPercentageOutput.setHorizontalAlignment(SwingConstants.CENTER);
		motifPercentageOutput.setFont(new Font("Tahoma", Font.BOLD, 14));
		motifPercentageOutput.setBounds(23, 72, 82, 17);
		panel2.add(motifPercentageOutput);

		JPanel panel3 = new JPanel();
		panel3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel3.setLayout(null);
		panel3.setBackground(Color.GREEN);
		panel3.setBounds(444, 237, 147, 100);
		contentPane.add(panel3);

		gcContent = new JLabel("0%");
		gcContent.setHorizontalAlignment(SwingConstants.CENTER);
		gcContent.setFont(new Font("Tahoma", Font.BOLD, 40));
		gcContent.setBounds(10, 11, 124, 49);
		panel3.add(gcContent);

		JLabel gcContentOutput = new JLabel("GC content");
		gcContentOutput.setBounds(44, 71, 125, 14);
		panel3.add(gcContentOutput);
		gcContentOutput.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBackground(Color.GREEN);
		panel4.setBounds(12, 348, 132, 100);
		contentPane.add(panel4);

		adeninPercentage = new JLabel("0%");
		adeninPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		adeninPercentage.setFont(new Font("Tahoma", Font.BOLD, 40));
		adeninPercentage.setBounds(0, 11, 131, 49);
		panel4.add(adeninPercentage);

		JLabel adeninLabel = new JLabel("Adenine");
		adeninLabel.setHorizontalAlignment(SwingConstants.CENTER);
		adeninLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		adeninLabel.setBounds(20, 72, 82, 17);
		panel4.add(adeninLabel);

		JPanel panel5 = new JPanel();
		panel5.setLayout(null);
		panel5.setBackground(Color.GREEN);
		panel5.setBounds(154, 348, 144, 100);
		contentPane.add(panel5);

		guaninPercentage = new JLabel("0%");
		guaninPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		guaninPercentage.setFont(new Font("Tahoma", Font.BOLD, 40));
		guaninPercentage.setBounds(0, 11, 144, 49);
		panel5.add(guaninPercentage);

		JLabel guaninLabel = new JLabel("Guanine");
		guaninLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guaninLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		guaninLabel.setBounds(20, 72, 82, 17);
		panel5.add(guaninLabel);

		JPanel panel6 = new JPanel();
		panel6.setLayout(null);
		panel6.setBackground(Color.GREEN);
		panel6.setBounds(305, 348, 135, 100);
		contentPane.add(panel6);

		cytosinPercentage = new JLabel("0%");
		cytosinPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		cytosinPercentage.setFont(new Font("Tahoma", Font.BOLD, 40));
		cytosinPercentage.setBounds(0, 11, 131, 49);
		panel6.add(cytosinPercentage);

		JLabel cytosineLabel = new JLabel("Cytosine");
		cytosineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cytosineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		cytosineLabel.setBounds(20, 72, 82, 17);
		panel6.add(cytosineLabel);

		JPanel panel7 = new JPanel();
		panel7.setLayout(null);
		panel7.setBackground(Color.GREEN);
		panel7.setBounds(444, 348, 147, 100);
		contentPane.add(panel7);

		thyminPercentage = new JLabel("0%");
		thyminPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		thyminPercentage.setFont(new Font("Tahoma", Font.BOLD, 40));
		thyminPercentage.setBounds(10, 11, 131, 49);
		panel7.add(thyminPercentage);

		JLabel motifCountOutput_2_1_1_1 = new JLabel("Thymine");
		motifCountOutput_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		motifCountOutput_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		motifCountOutput_2_1_1_1.setBounds(37, 72, 82, 17);
		panel7.add(motifCountOutput_2_1_1_1);

		JPanel panel0 = new JPanel();
		panel0.setLayout(null);
		panel0.setBackground(Color.GREEN);
		panel0.setBounds(10, 237, 135, 100);
		contentPane.add(panel0);

		motifDisplayLabel = new JLabel("----");
		motifDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		motifDisplayLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		motifDisplayLabel.setBounds(0, 11, 131, 49);
		panel0.add(motifDisplayLabel);

		JLabel motifPanelLabel = new JLabel("Motif");
		motifPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		motifPanelLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		motifPanelLabel.setBounds(23, 72, 82, 17);
		panel0.add(motifPanelLabel);
		
		JLabel gifLabel = new JLabel("");
		gifLabel.setIcon(new ImageIcon("C:\\Users\\asifz\\eclipse-workspace\\DNA Analyzer\\images\\greenDNA.gif"));
		gifLabel.setBounds(272, 30, 322, 88);
		contentPane.add(gifLabel);
		
		JLabel footerLabel = new JLabel("Developed By-");
		footerLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setBounds(10, 479, 113, 32);
		contentPane.add(footerLabel);
		
		JLabel idLabel = new JLabel("ID: 2309005");
		idLabel.setForeground(Color.GREEN);
		idLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		idLabel.setBounds(10, 522, 111, 25);
		contentPane.add(idLabel);
		
		JLabel nameLabel = new JLabel("Asif");
		nameLabel.setForeground(Color.GREEN);
		nameLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		nameLabel.setBounds(12, 503, 42, 25);
		contentPane.add(nameLabel);
	}
}
