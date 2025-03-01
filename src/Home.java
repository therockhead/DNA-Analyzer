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
import javax.swing.JSeparator;

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
	int result; // mainly result converted to integer properly
	public int gcPercentage;
	
	public JLabel motifPercentage;
	public JLabel motifDisplayLabel;
	public JLabel gcContent;
	
	public JLabel adeninPercentage;
	public JLabel guaninPercentage;
	public JLabel cytosinPercentage;
	public JLabel thyminPercentage;
	
	String consensus, function;
	public JLabel detailsLabel, detailsLabel_1;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Asif\\eclipse-workspace\\DNA-Analyzer-Tool\\images\\icon.png"));
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 732);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel chooseFileLabel = new JLabel("Choose Sequence File (.txt) :");
		chooseFileLabel.setForeground(Color.GREEN);
		chooseFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chooseFileLabel.setBounds(10, 132, 197, 16);
		contentPane.add(chooseFileLabel);

		JLabel topLabel = new JLabel("DNA Analyzer");
		topLabel.setBackground(Color.BLACK);
		topLabel.setForeground(new Color(255, 255, 255));
		topLabel.setBounds(72, 58, 299, 24);
		topLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 35));
		contentPane.add(topLabel);

		JButton browseButton = new JButton("");
		browseButton.setBackground(Color.BLACK);
		browseButton.setIcon(new ImageIcon(".\\images\\browse.png"));
		browseButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		browseButton.setBorderPainted(false);  
		browseButton.setFocusPainted(false);   
		browseButton.setToolTipText("Browse txt File");
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
					String fileName = selectedFile.getName();
		            fileChoosenDialog.setText(fileName);
					// fileChoosenDialog.setText(selectedFile.getAbsolutePath());
					selectedFilePath = selectedFile.getAbsolutePath();
					// System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}

			}
		});
		browseButton.setFocusable(false);
		browseButton.setBounds(200, 120, 42, 35);
		contentPane.add(browseButton);

		fileChoosenDialog = new JLabel("No File Choosen");
		fileChoosenDialog.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fileChoosenDialog.setForeground(Color.LIGHT_GRAY);
		fileChoosenDialog.setBounds(282, 128, 235, 24);
		contentPane.add(fileChoosenDialog);

		JLabel motifLabel = new JLabel("Desired Motif :");
		motifLabel.setForeground(Color.GREEN);
		motifLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		motifLabel.setBounds(10, 171, 98, 14);
		contentPane.add(motifLabel);

		inputMotif = new JTextField();
		inputMotif.setForeground(Color.GREEN);
		inputMotif.setBackground(Color.DARK_GRAY);
		inputMotif.setFont(new Font("Tahoma", Font.BOLD, 15));
		inputMotif.setBounds(108, 164, 385, 29);
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
		findButton.setToolTipText("Find");
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
		                        percentage = (((double) (countMotif * motif.length()) / sequence.length())* 100);
		                        if (percentage > 1) {
	                                // Already a whole number percentage (e.g., 45)
	                                result = (int) percentage;  
	                            } else {
	                                // It's a fraction (e.g., 0.45), convert it to percentage
	                                result = (int) (percentage * 100);
	                            }

		                        // GC Content
		                        gcPercentage = gcContentPercentageFunc(sequence);

		                        // Nucleotide Percentages
		                        int adeno = adeninPercentageFunc(sequence);
		                        int cyto = cytosinPercentageFunc(sequence);
		                        int guano = guaninPercentageFunc(sequence);
		                        int thy = thyminPercentageFunc(sequence);
		                        
		                        consensus = motif;
		                        function = "<html><span>" +(int)(result) +"% of the genes are likely regulated in a "+motif+"-dependent manner. ";
		                        
		                 /*my conditions to check the percentages*/
		                        
		                        if(motif.equals("TATAA") || motif.equals("TATA") || motif.equals("TATAAA"))
		                        {
		                        	function = function + "Core promoter element in prokaryotes, similar to the TATA box in eukaryotes. ";
		                        	
		                        	// Function: Core promoter element in prokaryotes, similar to the TATA box in eukaryotes.
		                        	
		                        	if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                        	else if (result > 40)
		                        	{
		                        		function = function + result + "% Suggests that a large proportion of genes are regulated in a TATA-dependent manner. " 
		                        				+"Genes with tightly regulated, highly controlled expression (e.g., stress response, development).<span></html>"; 
		                        	}
		                        	else if (result < 40)
		                        	{
		                        		function = function + result + "% Suggests that a large proportion of genes are regulated in a TATA-dependent manner. "
		                        				+ "Genes that are constitutively expressed and use alternative promoter elements (e.g., housekeeping genes).<span></html>";  
		                        	}
		                        	
		       
		                        }
		                        else if(motif.equals("GC")) 
		                        {
		                        	function = function + " the GC box is commonly found in constitutively expressed genes. "
		                        			+ "The GC box serves as a binding site for transcription factors like Sp1, which play a role in activating gene expression under normal and stress conditions. ";
		                        	
		                        	if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                        	else if (result > 40)
		                        	{
		                        		function = function + result + "% Indicates strong constitutive expression of genes. Suggests widespread regulatory control by transcription factors like Sp1. <span></html>"; 
		                        	}
		                        	else if (result < 40)
		                        	{
		                        		function = function + result+ "% Suggests tissue-specific or condition-dependent regulation.\r\n"
		                        				+ "These genes might be more responsive to external signals or specific transcription factors rather than relying on Sp1-driven activation. <span></html>"; 
		                        	}
		       
		                        }
		                        else if (motif.equals("CCAAT")) // CAAT Box
		                        {
		                            function = function + "Essential for transcription initiation in many eukaryotic genes. Works as an enhancer element for transcription factor binding. ";

		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% indicates that a large number of genes depend on CAAT box for transcription initiation. "
		                                        + "Common in genes requiring precise transcriptional regulation.<span></html>";
		                            } else {
		                                function = function + result + "% suggests CAAT box occurrence is low, indicating transcription is regulated by alternative elements.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("TGACGTCA")) // CRE (cAMP Response Element)
		                        {
		                            function = function + "Binding site for CREB (cAMP Response Element-Binding Protein), involved in signal-dependent gene regulation. ";

		                            
		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% suggests a strong regulatory response to cAMP signaling pathways. "
		                                        + "Common in genes related to metabolism, memory formation, and hormonal regulation.<span></html>";
		                            } else {
		                                function = function + result + "% suggests a weaker role in cAMP-dependent transcriptional regulation. "
		                                        + "May indicate alternative regulatory pathways controlling gene expression.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("TGACTCA")) // AP-1 Site
		                        {
		                            function = function + "Target site for AP-1 transcription factors, involved in cell growth, differentiation, and stress response. ";

		                            
		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% indicates strong AP-1 mediated transcriptional activation. "
		                                        + "Common in genes responding to cellular stress and mitogenic signals.<span></html>";
		                            } else {
		                                function = function + result + "% suggests a low occurrence of AP-1 sites, indicating reduced dependency on AP-1-mediated transcription.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("GGGACTTTCC")) // NF-κB Site
		                        {
		                            function = function + "Binding site for NF-κB, a key regulator of immune and inflammatory responses. ";

		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% suggests a high proportion of genes regulated by NF-κB, likely involved in immune response and inflammation.<span></html>";
		                            } else {
		                                function = function + result + "% indicates a lower presence of NF-κB binding sites, suggesting alternative immune regulatory pathways.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("TATAAT")) // Pribnow Box (Prokaryotic Promoter)
		                        {
		                            function = function + "Prokaryotic promoter element, essential for RNA polymerase binding and transcription initiation. ";

		                            
		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% suggests strong transcriptional activity in prokaryotes. "
		                                        + "Indicates genes with high expression levels under normal conditions.<span></html>";
		                            } else {
		                                function = function + result + "% suggests weak transcriptional activity, indicating alternative promoter elements play a role.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("AGGAGG")) // Shine-Dalgarno Sequence
		                        {
		                            function = function + "Ribosome binding site in prokaryotic mRNA, crucial for translation initiation. ";

		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% suggests highly efficient translation initiation in prokaryotic genes.<span></html>";
		                            } else {
		                                function = function + result + "% suggests lower Shine-Dalgarno occurrence, indicating less efficient ribosome binding and translation initiation.<span></html>";
		                            }
		                        }
		                        else if (motif.equals("ATTA") || motif.equals("TAAT")) // Homeobox (Hox Genes)
		                        {
		                            function = function + "Regulatory DNA sequence found in homeobox genes, essential for body patterning and development. ";

		                            if(result == 0)
		                        	{
		                        		function = function + "<br> but there is no " + motif +" motif found. <span></html>";
		                        	}
		                            else if (result > 40) {
		                                function = function + result + "% suggests strong homeobox gene expression, critical for embryonic development and cellular differentiation.<span></html>";
		                            } else {
		                                function = function + result + "% suggests lower occurrence, indicating alternative mechanisms regulate developmental genes.<span></html>";
		                            }
		                        }

		                        // Simulate progress
		                        for (int i = 0; i <= 100; i += 10) {
		                            Thread.sleep(50); // Simulate computation time
		                            progressBar.setValue(i);
		                        }

		                        // Update UI components
		                        SwingUtilities.invokeLater(() -> {
		                            motifDisplayLabel.setText(motif);
		                            motifCount.setText(c);
		                            
		                            
		                            motifPercentage.setText((int)(result) + "%");
		                            gcContent.setText(gcPercentage + "%");
		                            adeninPercentage.setText(adeno + "%");
		                            thyminPercentage.setText(thy + "%");
		                            guaninPercentage.setText(guano + "%");
		                            cytosinPercentage.setText(cyto + "%");
		                            detailsLabel.setText(consensus);
		                            detailsLabel_1.setText(function);
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

		findButton.setIcon(new ImageIcon(".\\images\\find.png"));
		findButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		findButton.setBounds(496, 164, 42, 32);
		contentPane.add(findButton);

		JButton clearButton = new JButton("");
		clearButton.setBackground(Color.BLACK);
		clearButton.setBorderPainted(false);  
		clearButton.setFocusPainted(false);   
		clearButton.setToolTipText("Clear Calculation");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				inputMotif.setText("");
				
				motifDisplayLabel.setText("----");
				motifCount.setText("0");
				motifPercentage.setText("0%");
				gcContent.setText("0%");
				adeninPercentage.setText("0%");
				guaninPercentage.setText("0%");
				cytosinPercentage.setText("0%");
				thyminPercentage.setText("0%");
				
				detailsLabel.setText("------------");
				detailsLabel_1.setText("------------");
				
				progressBar.setValue(0);
				
			}
		});
		clearButton.setSelectedIcon(new ImageIcon(".\\images\\clean.png"));
		clearButton.setIcon(new ImageIcon(".\\images\\clean (1).png"));
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearButton.setBounds(548, 158, 42, 41);
		contentPane.add(clearButton);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(10, 212, 581, 14);
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
		motifCount.setFont(new Font("Tahoma", Font.BOLD, 30));

		JLabel motifCountOutput = new JLabel("Motif Count");
		motifCountOutput.setBounds(20, 72, 111, 17);
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
		motifPercentage.setFont(new Font("Tahoma", Font.BOLD, 30));
		motifPercentage.setBounds(0, 11, 134, 49);
		panel2.add(motifPercentage);

		JLabel motifPercentageOutput = new JLabel("Motif Percentage");
		motifPercentageOutput.setHorizontalAlignment(SwingConstants.CENTER);
		motifPercentageOutput.setFont(new Font("Tahoma", Font.BOLD, 13));
		motifPercentageOutput.setBounds(10, 72, 117, 17);
		panel2.add(motifPercentageOutput);

		JPanel panel3 = new JPanel();
		panel3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel3.setLayout(null);
		panel3.setBackground(Color.GREEN);
		panel3.setBounds(444, 237, 147, 100);
		contentPane.add(panel3);

		gcContent = new JLabel("0%");
		gcContent.setHorizontalAlignment(SwingConstants.CENTER);
		gcContent.setFont(new Font("Tahoma", Font.BOLD, 30));
		gcContent.setBounds(10, 11, 124, 49);
		panel3.add(gcContent);

		JLabel gcContentOutput = new JLabel("GC content");
		gcContentOutput.setBounds(40, 68, 99, 14);
		panel3.add(gcContentOutput);
		gcContentOutput.setFont(new Font("Tahoma", Font.BOLD, 14));

		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBackground(Color.GREEN);
		panel4.setBounds(12, 348, 132, 100);
		contentPane.add(panel4);

		adeninPercentage = new JLabel("0%");
		adeninPercentage.setHorizontalAlignment(SwingConstants.CENTER);
		adeninPercentage.setFont(new Font("Tahoma", Font.BOLD, 30));
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
		guaninPercentage.setFont(new Font("Tahoma", Font.BOLD, 30));
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
		cytosinPercentage.setFont(new Font("Tahoma", Font.BOLD, 30));
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
		thyminPercentage.setFont(new Font("Tahoma", Font.BOLD, 30));
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
		motifDisplayLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		motifDisplayLabel.setBounds(0, 11, 131, 49);
		panel0.add(motifDisplayLabel);

		JLabel motifPanelLabel = new JLabel("Motif");
		motifPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		motifPanelLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		motifPanelLabel.setBounds(23, 72, 82, 17);
		panel0.add(motifPanelLabel);
		
		JLabel gifLabel = new JLabel("");
		gifLabel.setIcon(new ImageIcon(".\\images\\greenDNA.gif"));
		gifLabel.setBounds(272, 30, 322, 88);
		contentPane.add(gifLabel);
		
		JLabel footerLabel = new JLabel("©Developed By-");
		footerLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setBounds(206, 661, 135, 32);
		contentPane.add(footerLabel);
		
		JLabel nameLabel = new JLabel("Asif");
		nameLabel.setForeground(Color.GREEN);
		nameLabel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 14));
		nameLabel.setBounds(340, 665, 42, 25);
		nameLabel.setToolTipText("<html><span>©Asif, Bioinformatics Engineering @BAU<br>asifzaman.bie@gmail.com<br> All Rights Reserved by the Creator</span><html>");
		contentPane.add(nameLabel);
		
		
		
		JPanel sigPanel = new JPanel();
		sigPanel.setBackground(Color.DARK_GRAY);
		sigPanel.setBounds(10, 485, 581, 165);
		contentPane.add(sigPanel);
		sigPanel.setLayout(null);
		
		detailsLabel = new JLabel("------------");
		detailsLabel.setVerticalAlignment(SwingConstants.TOP);
		detailsLabel.setBounds(181, 13, 390, 28);
		detailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		detailsLabel.setForeground(Color.GREEN);
		sigPanel.add(detailsLabel);
		
		JLabel consLabel = new JLabel("Consensus Sequence:");
		consLabel.setForeground(new Color(192, 192, 192));
		consLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		consLabel.setBounds(10, 15, 161, 14);
		sigPanel.add(consLabel);
		
		JLabel functionLabel = new JLabel("Function:");
		functionLabel.setForeground(new Color(192, 192, 192));
		functionLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		functionLabel.setBounds(10, 40, 66, 14);
		sigPanel.add(functionLabel);
		
		detailsLabel_1 = new JLabel("------------");
		detailsLabel_1.setVerticalAlignment(SwingConstants.TOP);
		detailsLabel_1.setForeground(Color.GREEN);
		detailsLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		detailsLabel_1.setBounds(82, 40, 489, 120);
		sigPanel.add(detailsLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 661, 484, 2);
		contentPane.add(separator);
		
		JButton removeFileButton = new JButton("");
		removeFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedFile = null;
				fileChoosenDialog.setText("No file choosen");
			}
		});
		removeFileButton.setForeground(Color.GREEN);
		removeFileButton.setIcon(new ImageIcon(".\\images\\delete.png"));
		removeFileButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		removeFileButton.setBackground(Color.BLACK);
		removeFileButton.setBounds(239, 120, 35, 34);
		removeFileButton.setFocusable(false);
		removeFileButton.setBorderPainted(false); 
		removeFileButton.setFocusPainted(false);
		removeFileButton.setToolTipText("Remove File/Clear Path");
		contentPane.add(removeFileButton);
		
		JLabel predictionLabel = new JLabel("Predictions");
		predictionLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		predictionLabel.setForeground(Color.GREEN);
		predictionLabel.setBounds(249, 460, 122, 14);
		contentPane.add(predictionLabel);
	}
}
