/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.view;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import tesis.OpinionMiningManager;
import tesis.util.Rotator;

/**
 *
 * @author Cesar
 */
public class SubjectiveSearchResultsJFrame extends javax.swing.JFrame {
    public static Color LIGHT_SUBJECTIVE = new Color(236,245,240);
    public static Color LIGHT_OBJECTIVE = new Color(247,231,232);    
    public static Color DARK_SUBJECTIVE = new Color(66,155,63);
    public static Color DARK_OBJECTIVE = new Color(205,18,25);        

    /**
     * Creates new form SubjectiveSearchResultsJFrame
     */
    public SubjectiveSearchResultsJFrame(List<String> objectiveTweets, List<String> subjectiveTweets, String twitterSearch) {
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        this.setIconImage(new ImageIcon("icons"+File.separator+"twitter-search-icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jScrollPaneSearchedTerm.getViewport().setBackground(Color.WHITE);
        jLabelSearchedTerm.setText(twitterSearch);
        jLabelObjectiveTweets.setForeground(DARK_OBJECTIVE);
        jLabelSubjectiveTweets.setForeground(DARK_SUBJECTIVE);        
        jLabelObjectiveTweets.setForeground(DARK_OBJECTIVE);
        jLabelSubjectiveTweetsAmount.setForeground(DARK_SUBJECTIVE);
        
        jLabelObjectiveTweetsAmount.setText(new Integer(objectiveTweets.size()).toString());        
        jLabelSubjectiveTweetsAmount.setText(new Integer(subjectiveTweets.size()).toString());
        jLabelTotalTweetsAmount.setText(new Integer(objectiveTweets.size()+subjectiveTweets.size()).toString());
        
        setTweetsScrollPane(objectiveTweets, subjectiveTweets);        
        setTweetResultsChart(objectiveTweets.size(), subjectiveTweets.size());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTweetPanel = new javax.swing.JPanel();
        jScrollPaneSubjective = new javax.swing.JScrollPane();
        jPanelSubjective = new javax.swing.JPanel();
        jScrollPaneObjective = new javax.swing.JScrollPane();
        jPanelObjective = new javax.swing.JPanel();
        jLabelMovie = new javax.swing.JLabel();
        jLabelSubjectiveTweets = new javax.swing.JLabel();
        jLabelSubjectiveTweetsAmount = new javax.swing.JLabel();
        jLabelObjectiveTweets = new javax.swing.JLabel();
        jLabelObjectiveTweetsAmount = new javax.swing.JLabel();
        jLabelMovie7 = new javax.swing.JLabel();
        jLabelTotalTweetsAmount = new javax.swing.JLabel();
        jScrollPaneSearchedTerm = new javax.swing.JScrollPane();
        jLabelSearchedTerm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subjectivity Results");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTweetPanel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPaneSubjective.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneSubjective.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPaneSubjective.setPreferredSize(new java.awt.Dimension(889, 445));

        jPanelSubjective.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelSubjectiveLayout = new javax.swing.GroupLayout(jPanelSubjective);
        jPanelSubjective.setLayout(jPanelSubjectiveLayout);
        jPanelSubjectiveLayout.setHorizontalGroup(
            jPanelSubjectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );
        jPanelSubjectiveLayout.setVerticalGroup(
            jPanelSubjectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        jScrollPaneSubjective.setViewportView(jPanelSubjective);

        jScrollPaneObjective.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneObjective.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanelObjective.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelObjectiveLayout = new javax.swing.GroupLayout(jPanelObjective);
        jPanelObjective.setLayout(jPanelObjectiveLayout);
        jPanelObjectiveLayout.setHorizontalGroup(
            jPanelObjectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanelObjectiveLayout.setVerticalGroup(
            jPanelObjectiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jScrollPaneObjective.setViewportView(jPanelObjective);

        javax.swing.GroupLayout jTweetPanelLayout = new javax.swing.GroupLayout(jTweetPanel);
        jTweetPanel.setLayout(jTweetPanelLayout);
        jTweetPanelLayout.setHorizontalGroup(
            jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneSubjective, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292)
                .addComponent(jScrollPaneObjective, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTweetPanelLayout.setVerticalGroup(
            jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneSubjective, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPaneObjective, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tweets", jTweetPanel);

        jLabelMovie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelMovie.setText("Search:");

        jLabelSubjectiveTweets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSubjectiveTweets.setText("Subjective Tweets");

        jLabelSubjectiveTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSubjectiveTweetsAmount.setText("100");

        jLabelObjectiveTweets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelObjectiveTweets.setText("Objective Tweets");

        jLabelObjectiveTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelObjectiveTweetsAmount.setText("100");

        jLabelMovie7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelMovie7.setText("Total Tweets");

        jLabelTotalTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTotalTweetsAmount.setText("100");

        jScrollPaneSearchedTerm.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneSearchedTerm.setBorder(null);
        jScrollPaneSearchedTerm.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jLabelSearchedTerm.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSearchedTerm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelSearchedTerm.setText("Searched Term");
        jScrollPaneSearchedTerm.setViewportView(jLabelSearchedTerm);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelMovie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPaneSearchedTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabelSubjectiveTweets))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelSubjectiveTweetsAmount)
                                .addGap(47, 47, 47)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabelObjectiveTweets)
                                        .addGap(99, 99, 99))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabelObjectiveTweetsAmount)
                                        .addGap(138, 138, 138)
                                        .addComponent(jLabelTotalTweetsAmount)
                                        .addGap(33, 33, 33))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(jLabelMovie7)))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSubjectiveTweets)
                            .addComponent(jLabelObjectiveTweets)
                            .addComponent(jLabelMovie7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSubjectiveTweetsAmount)
                            .addComponent(jLabelObjectiveTweetsAmount)
                            .addComponent(jLabelTotalTweetsAmount)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPaneSearchedTerm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabelMovie, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelMovie;
    private javax.swing.JLabel jLabelMovie7;
    private javax.swing.JLabel jLabelObjectiveTweets;
    private javax.swing.JLabel jLabelObjectiveTweetsAmount;
    private javax.swing.JLabel jLabelSearchedTerm;
    private javax.swing.JLabel jLabelSubjectiveTweets;
    private javax.swing.JLabel jLabelSubjectiveTweetsAmount;
    private javax.swing.JLabel jLabelTotalTweetsAmount;
    private javax.swing.JPanel jPanelObjective;
    private javax.swing.JPanel jPanelSubjective;
    private javax.swing.JScrollPane jScrollPaneObjective;
    private javax.swing.JScrollPane jScrollPaneSearchedTerm;
    private javax.swing.JScrollPane jScrollPaneSubjective;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jTweetPanel;
    // End of variables declaration//GEN-END:variables

     private void setTweetsScrollPane(List<String> objectiveTweets,List<String> subjectiveTweets) {        
        //define layout          
        int tweetsAmount = objectiveTweets.size()+subjectiveTweets.size();
        JPanel jPanelFirstTab = new JPanel(new GridBagLayout());
        jTabbedPane1.setComponentAt(0, jPanelFirstTab);
        
        GridBagConstraints gbcJPanelFirstTab = new GridBagConstraints();        
        
        gbcJPanelFirstTab.fill = GridBagConstraints.HORIZONTAL;
        gbcJPanelFirstTab.gridwidth = GridBagConstraints.REMAINDER;
        gbcJPanelFirstTab.gridx = 0;
        gbcJPanelFirstTab.gridy = 0;
        gbcJPanelFirstTab.anchor = GridBagConstraints.FIRST_LINE_START;
        gbcJPanelFirstTab.weightx = 1.0;        
        JPanel jPanelNames = new JPanel(new GridLayout(1, 3, 1, 0));        
        jPanelFirstTab.add(jPanelNames,gbcJPanelFirstTab);
                        
        gbcJPanelFirstTab.fill = GridBagConstraints.BOTH;
        gbcJPanelFirstTab.gridwidth = GridBagConstraints.REMAINDER;
        gbcJPanelFirstTab.gridx = 0;
        gbcJPanelFirstTab.gridy = 1;
        gbcJPanelFirstTab.weighty = 1.0;        
        jPanelFirstTab.add(this.jTweetPanel,gbcJPanelFirstTab);        
                               
        JTextArea columnNameSubjective = new JTextArea("   " + new Double(Math.round(new Double(subjectiveTweets.size())/tweetsAmount*100)).toString() + " %  " + "SUBJECTIVE");
        columnNameSubjective.setForeground(Color.WHITE);
        columnNameSubjective.setBackground(DARK_SUBJECTIVE);
        columnNameSubjective.setFont(new Font("sansserif", Font.BOLD,12));
        jPanelNames.add(columnNameSubjective);                        
                
        JTextArea columnNameObjective = new JTextArea("   " + new Double(Math.round(new Double(objectiveTweets.size())/tweetsAmount*100)).toString() + " %  " + "OBJECTIVE");
        columnNameObjective.setForeground(Color.WHITE);
        columnNameObjective.setBackground(DARK_OBJECTIVE);
        columnNameObjective.setFont(new Font("sansserif", Font.BOLD,12));
        jPanelNames.add(columnNameObjective);
                                
        this.jTweetPanel.setLayout(new GridLayout(1, 3));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,1,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;               
        
        this.jPanelObjective.setLayout(new GridBagLayout());
        jPanelObjective.setBackground(LIGHT_OBJECTIVE);
        this.jPanelSubjective.setLayout(new GridBagLayout());
        jPanelSubjective.setBackground(LIGHT_SUBJECTIVE);        
        
        HashMap<String,String> originalTweets = OpinionMiningManager.getInstance().getOriginalTweets();
        //add tweets to panels
        for (String subjectiveTweet : subjectiveTweets){            
            JTextArea label = new JTextArea(originalTweets.get(subjectiveTweet));
            label.setLineWrap(true);            
            label.setWrapStyleWord(true);
            label.setFont(this.getFont());                                        
            label.setBackground(LIGHT_SUBJECTIVE);
            jPanelSubjective.add(label,gbc);                                                               
        }
        
        for (String objectiveTweet : objectiveTweets){            
            JTextArea label = new JTextArea(originalTweets.get(objectiveTweet));
            label.setLineWrap(true);            
            label.setWrapStyleWord(true);
            label.setFont(this.getFont());                                        
            label.setBackground(LIGHT_OBJECTIVE);
            jPanelObjective.add(label,gbc);                                                               
        }
     
        gbc.weighty = 1.0;
        JTextArea lastLabelObj = new JTextArea("");
        lastLabelObj.setBackground(LIGHT_OBJECTIVE);
        jPanelObjective.add(lastLabelObj,gbc);
        JTextArea lastLabelSubj = new JTextArea("");
        lastLabelSubj.setBackground(LIGHT_SUBJECTIVE);
        jPanelSubjective.add(lastLabelSubj,gbc);                              
    }

    private void setTweetResultsChart(int objectiveTweets, int subjectiveTweets) {
        // get tweet sentiment data                        
        
        // create a dataset...
        final DefaultPieDataset data = new DefaultPieDataset();                
        data.setValue("Objective Tweets", new Double(objectiveTweets));
        data.setValue("Subjective Tweets", new Double(subjectiveTweets));        

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart3D("Twitter Subjectivity Chart",data,true,true,false);
        chart.setBackgroundPaint(Color.white);
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        // set colors for pie chart
        plot.setBackgroundPaint(Color.white);
        plot.setSectionPaint("Objective Tweets", DARK_OBJECTIVE); 
        plot.setSectionPaint("Subjective Tweets", DARK_SUBJECTIVE);        
        
        plot.setStartAngle(270);
        plot.setDirection(Rotation.ANTICLOCKWISE);
        plot.setForegroundAlpha(0.60f);        
        // add the chart to a panel...

        final ChartPanel chartPanel = new ChartPanel(chart);
        jTabbedPane1.addTab("Chart", chartPanel);        
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        final Rotator rotator = new Rotator(plot);
        rotator.start();
    }

}
