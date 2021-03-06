/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.view;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import tesis.OpinionMiningManager;
import tesis.opinionMining.SentimentPhrase;
import tesis.util.Rotator;

/**
 *
 * @author Cesar
 */
public class SearchResultsJFrame extends javax.swing.JFrame {
    public static Color LIGHT_POSITIVE = new Color(236,245,240);
    public static Color LIGHT_NEGATIVE = new Color(247,231,232);
    public static Color LIGHT_NEUTRAL = new Color(250,251,233);
    public static Color DARK_POSITIVE = new Color(66,155,63);
    public static Color DARK_NEGATIVE = new Color(205,18,25);
    public static Color DARK_NEUTRAL = new Color(174,164,43);
    
    /**
     * Creates new form ResultsJFrame
     */
    public SearchResultsJFrame(List<SentimentPhrase> tweetSentiments, String twitterSearch) {
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        this.setIconImage(new ImageIcon("icons"+File.separator+"twitter-search-icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jScrollPaneSearchedTerm.getViewport().setBackground(Color.WHITE);
        jLabelSearchedTerm.setText(twitterSearch);
        jLabelNegativeTweets.setForeground(DARK_NEGATIVE);
        jLabelPositiveTweets.setForeground(DARK_POSITIVE);
        jLabelNeutralTweets.setForeground(DARK_NEUTRAL);
        jLabelNegativeTweetsAmount.setForeground(DARK_NEGATIVE);
        jLabelPositiveTweetsAmount.setForeground(DARK_POSITIVE);
        jLabelNeutralTweetsAmount.setForeground(DARK_NEUTRAL);
        
        double negativeTweets = 0;
        double positiveTweets = 0;        
        double neutralTweets = 0;
        for (SentimentPhrase sentimentPhrase : tweetSentiments){            
            if (sentimentPhrase.getSentiment() == -1)
                negativeTweets++;
            else{
                if (sentimentPhrase.getSentiment() == 1)
                    positiveTweets++;
            }            
        }       
        neutralTweets = tweetSentiments.size() - positiveTweets - negativeTweets;
        
        jLabelNegativeTweetsAmount.setText(new Double(negativeTweets).toString());
        jLabelNeutralTweetsAmount.setText(new Double(neutralTweets).toString());
        jLabelPositiveTweetsAmount.setText(new Double(positiveTweets).toString());
        jLabelTotalTweetsAmount.setText(new Integer(tweetSentiments.size()).toString());
        
        setTweetsScrollPane(tweetSentiments,positiveTweets,neutralTweets,negativeTweets);        
        setTweetResultsChart(positiveTweets,neutralTweets,negativeTweets);        
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
        jScrollPanePositive = new javax.swing.JScrollPane();
        jPanelPositive = new javax.swing.JPanel();
        jScrollPaneNeutral = new javax.swing.JScrollPane();
        jPanelNeutral = new javax.swing.JPanel();
        jScrollPaneNegative = new javax.swing.JScrollPane();
        jPanelNegative = new javax.swing.JPanel();
        jLabelMovie = new javax.swing.JLabel();
        jLabelPositiveTweets = new javax.swing.JLabel();
        jLabelNeutralTweets = new javax.swing.JLabel();
        jLabelPositiveTweetsAmount = new javax.swing.JLabel();
        jLabelNeutralTweetsAmount = new javax.swing.JLabel();
        jLabelNegativeTweets = new javax.swing.JLabel();
        jLabelNegativeTweetsAmount = new javax.swing.JLabel();
        jLabelMovie7 = new javax.swing.JLabel();
        jLabelTotalTweetsAmount = new javax.swing.JLabel();
        jScrollPaneSearchedTerm = new javax.swing.JScrollPane();
        jLabelSearchedTerm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sentiment Results");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTweetPanel.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPanePositive.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPanePositive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPanePositive.setPreferredSize(new java.awt.Dimension(889, 445));

        jPanelPositive.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelPositiveLayout = new javax.swing.GroupLayout(jPanelPositive);
        jPanelPositive.setLayout(jPanelPositiveLayout);
        jPanelPositiveLayout.setHorizontalGroup(
            jPanelPositiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );
        jPanelPositiveLayout.setVerticalGroup(
            jPanelPositiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        jScrollPanePositive.setViewportView(jPanelPositive);

        jScrollPaneNeutral.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneNeutral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanelNeutral.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelNeutralLayout = new javax.swing.GroupLayout(jPanelNeutral);
        jPanelNeutral.setLayout(jPanelNeutralLayout);
        jPanelNeutralLayout.setHorizontalGroup(
            jPanelNeutralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );
        jPanelNeutralLayout.setVerticalGroup(
            jPanelNeutralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        jScrollPaneNeutral.setViewportView(jPanelNeutral);

        jScrollPaneNegative.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneNegative.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanelNegative.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelNegativeLayout = new javax.swing.GroupLayout(jPanelNegative);
        jPanelNegative.setLayout(jPanelNegativeLayout);
        jPanelNegativeLayout.setHorizontalGroup(
            jPanelNegativeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        jPanelNegativeLayout.setVerticalGroup(
            jPanelNegativeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jScrollPaneNegative.setViewportView(jPanelNegative);

        javax.swing.GroupLayout jTweetPanelLayout = new javax.swing.GroupLayout(jTweetPanel);
        jTweetPanel.setLayout(jTweetPanelLayout);
        jTweetPanelLayout.setHorizontalGroup(
            jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanePositive, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneNeutral, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneNegative, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTweetPanelLayout.setVerticalGroup(
            jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTweetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jTweetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPanePositive, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPaneNeutral)
                    .addComponent(jScrollPaneNegative, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tweets", jTweetPanel);

        jLabelMovie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelMovie.setText("Search:");

        jLabelPositiveTweets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPositiveTweets.setText("Positive Tweets");

        jLabelNeutralTweets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNeutralTweets.setText("Neutral Tweets");

        jLabelPositiveTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPositiveTweetsAmount.setText("100");

        jLabelNeutralTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNeutralTweetsAmount.setText("100");

        jLabelNegativeTweets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNegativeTweets.setText("Negative Tweets");

        jLabelNegativeTweetsAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNegativeTweetsAmount.setText("100");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelMovie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneSearchedTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPositiveTweets)
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelPositiveTweetsAmount)
                                .addGap(104, 104, 104)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNeutralTweets)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelNeutralTweetsAmount)
                                .addGap(37, 37, 37)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabelNegativeTweets)
                                .addGap(99, 99, 99))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabelNegativeTweetsAmount)
                                .addGap(138, 138, 138)
                                .addComponent(jLabelTotalTweetsAmount)
                                .addGap(33, 33, 33))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelMovie7)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPositiveTweets)
                            .addComponent(jLabelNeutralTweets)
                            .addComponent(jLabelNegativeTweets)
                            .addComponent(jLabelMovie7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPositiveTweetsAmount)
                            .addComponent(jLabelNeutralTweetsAmount)
                            .addComponent(jLabelNegativeTweetsAmount)
                            .addComponent(jLabelTotalTweetsAmount)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPaneSearchedTerm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabelMovie, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelMovie;
    private javax.swing.JLabel jLabelMovie7;
    private javax.swing.JLabel jLabelNegativeTweets;
    private javax.swing.JLabel jLabelNegativeTweetsAmount;
    private javax.swing.JLabel jLabelNeutralTweets;
    private javax.swing.JLabel jLabelNeutralTweetsAmount;
    private javax.swing.JLabel jLabelPositiveTweets;
    private javax.swing.JLabel jLabelPositiveTweetsAmount;
    private javax.swing.JLabel jLabelSearchedTerm;
    private javax.swing.JLabel jLabelTotalTweetsAmount;
    private javax.swing.JPanel jPanelNegative;
    private javax.swing.JPanel jPanelNeutral;
    private javax.swing.JPanel jPanelPositive;
    private javax.swing.JScrollPane jScrollPaneNegative;
    private javax.swing.JScrollPane jScrollPaneNeutral;
    private javax.swing.JScrollPane jScrollPanePositive;
    private javax.swing.JScrollPane jScrollPaneSearchedTerm;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jTweetPanel;
    // End of variables declaration//GEN-END:variables

    private void setTweetsScrollPane(List<SentimentPhrase> tweetSentiments, double positiveTweets, double neutralTweets, double negativeTweets) {        
        //define layout                
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
                               
        JTextArea columnNamePositive = new JTextArea("   " + new Double(Math.round(positiveTweets/tweetSentiments.size()*100)).toString() + " %  " + "POSITIVE");
        columnNamePositive.setForeground(Color.WHITE);
        columnNamePositive.setBackground(DARK_POSITIVE);
        columnNamePositive.setFont(new Font("sansserif", Font.BOLD,12));
        jPanelNames.add(columnNamePositive);
                
        JTextArea columnNameNeutral = new JTextArea("   " + new Double(Math.round(neutralTweets/tweetSentiments.size()*100)).toString() + " %  " + "NEUTRAL");
        columnNameNeutral.setForeground(Color.WHITE);
        columnNameNeutral.setBackground(DARK_NEUTRAL);
        columnNameNeutral.setFont(new Font("sansserif", Font.BOLD,12));     
        jPanelNames.add(columnNameNeutral);
                
        JTextArea columnNameNegative = new JTextArea("   " + new Double(Math.round(negativeTweets/tweetSentiments.size()*100)).toString() + " %  " + "NEGATIVE");
        columnNameNegative.setForeground(Color.WHITE);
        columnNameNegative.setBackground(DARK_NEGATIVE);
        columnNameNegative.setFont(new Font("sansserif", Font.BOLD,12));
        jPanelNames.add(columnNameNegative);
                                
        this.jTweetPanel.setLayout(new GridLayout(1, 3));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,1,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;               
        
        this.jPanelNegative.setLayout(new GridBagLayout());
        jPanelNegative.setBackground(LIGHT_NEGATIVE);
        this.jPanelPositive.setLayout(new GridBagLayout());
        jPanelPositive.setBackground(LIGHT_POSITIVE);
        this.jPanelNeutral.setLayout(new GridBagLayout());
        jPanelNeutral.setBackground(LIGHT_NEUTRAL);
        
        HashMap<String,String> originalTweets = OpinionMiningManager.getInstance().getOriginalTweets();
        //add tweets to panels
        for (SentimentPhrase sentimentPhrase : tweetSentiments){            
            JTextArea label = new JTextArea(originalTweets.get(sentimentPhrase.getPhrase()));
            label.setLineWrap(true);            
            label.setWrapStyleWord(true);
            label.setFont(this.getFont());            
            if (sentimentPhrase.getSentiment() == -1){                
                label.setBackground(LIGHT_NEGATIVE);
                jPanelNegative.add(label,gbc);                
            }
            else{
                if (sentimentPhrase.getSentiment() == 1){                    
                    label.setBackground(LIGHT_POSITIVE);
                    jPanelPositive.add(label,gbc);                    
                }
                else{                    
                    label.setBackground(LIGHT_NEUTRAL);
                    jPanelNeutral.add(label,gbc);                    
                }
            }                        
        }
        gbc.weighty = 1.0;
        JTextArea lastLabelNeg = new JTextArea("");
        lastLabelNeg.setBackground(LIGHT_NEGATIVE);
        jPanelNegative.add(lastLabelNeg,gbc);
        JTextArea lastLabelPos = new JTextArea("");
        lastLabelPos.setBackground(LIGHT_POSITIVE);
        jPanelPositive.add(lastLabelPos,gbc);
        JTextArea lastLabelNeu = new JTextArea("");
        lastLabelNeu.setBackground(LIGHT_NEUTRAL);
        jPanelNeutral.add(lastLabelNeu,gbc);                      
    }

    private void setTweetResultsChart(double positiveTweets, double neutralTweets, double negativeTweets) {
        // get tweet sentiment data                        
        
        // create a dataset...
        final DefaultPieDataset data = new DefaultPieDataset();                
        data.setValue("Negative Tweets", new Double(negativeTweets));
        data.setValue("Positive Tweets", new Double(positiveTweets));
        if (neutralTweets != 0)
            data.setValue("Neutral Tweets", new Double(neutralTweets));

        // create the chart...
        final JFreeChart chart = ChartFactory.createPieChart3D("Twitter Sentiment Chart",data,true,true,false);
        chart.setBackgroundPaint(Color.white);
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        // set colors for pie chart
        plot.setBackgroundPaint(Color.white);
        plot.setSectionPaint("Negative Tweets", DARK_NEGATIVE); 
        plot.setSectionPaint("Positive Tweets", DARK_POSITIVE);
        if (neutralTweets != 0)
            plot.setSectionPaint("Neutral Tweets", DARK_NEUTRAL);
        
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
