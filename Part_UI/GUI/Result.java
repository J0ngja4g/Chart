
import javax.swing.*;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.demo.BarChartDemo1;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import Lib.PieChartPanel;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;

public class Result extends JFrame {
    public Result() {
        setTitle("Result");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(null);

        // ฟอนต์
        Font thaiFontBold = new Font("Tahoma", 0, 24);

        // JPanel ใหญ่ ที่อยู่บน JFrame อีกที
        JPanel bg = new JPanel();
        bg.setBackground(new Color(240, 231, 252));
        bg.setLayout(null);
        bg.setBounds(0, 0, 1200, 800); // สำคัญมาก    

        // หัว
        JLabel Texthead = new JLabel("Calculation Results");
        Texthead.setFont(new Font("Tahoma", Font.PLAIN, 32));
        Texthead.setBounds(452, 30, 400, 50);

         // JButton back
        JButton back = new JButton("ย้อนกลับ");
        back.setFont(new java.awt.Font("Tahoma", 0, 24));
        back.setBounds(25, 25, 130, 40);
        back.setFocusPainted(false);
        back.setBorder(new kobmon(20));
        back.setContentAreaFilled(true);
        back.setOpaque(true);
        back.setBackground(new Color(238, 226, 251));
        
         // JButton rekumnuan
        JButton newround = new JButton("เริ่มคำนวณใหม่");
        newround.setFont(new java.awt.Font("Tahoma", 0, 24));
        newround.setBounds(950, 25, 200, 40);
        newround.setFocusPainted(false);//ขอบมน ปิดเส้นกรอบตอนกด
        newround.setBorder(new kobmon(20));
        newround.setBorderPainted(true);//ปิดวาดขอบ
        newround.setContentAreaFilled(true);//มีแอ็กชั่น
        newround.setOpaque(true);
        newround.setBackground(new Color(238, 226, 251));

        //Jpanel สีฟ้า
        JPanel headbar = new JPanel();
        headbar.setLayout(null);
        headbar.setBounds(0, 0, 1200, 90);
        headbar.setBackground(new Color(182,227,245));
        
        // JLabel
        JLabel year = new JLabel("ปีที่คำนวณ : ");
        year.setFont(new java.awt.Font("Tahoma", 0, 24));
        year.setBounds(705, 150, 200, 40);
        JLabel totalincome = new JLabel("รายได้สุทธิ : ");
        totalincome.setFont(thaiFontBold);
        totalincome.setBounds(705, 200,200 , 40);
        JLabel cutTax = new JLabel("ส่วนลดหย่อน : ");
        cutTax.setFont(thaiFontBold);
        cutTax.setBounds(682,250 ,200 ,40 );
        JLabel payTax = new JLabel("ภาษีที่ต้องจ่าย : ");
        payTax.setFont(thaiFontBold);
        payTax.setBounds(670, 300, 200, 40);

        //ใส่ข้อมูลกราฟแท่ง
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double income = 170000;
        double deduction = 23000;
        double remaining = income - deduction;

        dataset.addValue(income, "รายได้", "ผลรวม");
        dataset.addValue(deduction, "ส่วนลดหย่อน", "ผลรวม");
        dataset.addValue(remaining, "คงเหลือ", "ผลรวม");

        //สร้างกราฟแท่ง
        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "ประจำปี 2565",
                "จำนวนเงิน (บาท)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // เซตขอบ
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(220, 220, 220)); // สีเส้นกริด
        plot.setRangeGridlinesVisible(true);


        //ปรับสเกลกราฟ
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 2000000);            // ตั้งค่าสูงสุด
        rangeAxis.setTickUnit(new NumberTickUnit(500000)); // แสดงทุก 50,000 บาท
        rangeAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 18));
        rangeAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 16));

        //สีแท่งกราฟ
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(93, 173, 226));  // ฟ้า
        renderer.setSeriesPaint(1, new Color(88, 214, 141));  // เขียว
        renderer.setSeriesPaint(2, new Color(245, 176, 65));  // เหลือง

        renderer.setBarPainter(new StandardBarPainter()); // ลบเงา
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("Tahoma", Font.BOLD, 16));
        renderer.setBaseItemLabelPaint(Color.DARK_GRAY);
        renderer.setBarPainter(new StandardBarPainter());//
        renderer.setShadowVisible(false);

        barChart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 22));
        barChart.setBackgroundPaint(new Color(240, 231, 252));
        

        //สร้างกราฟแท่ง
        ChartPanel chartPanel = new ChartPanel(barChart);//ย้อนหลังปีแรก
        chartPanel.setBounds(100, 420, 470, 330);
        chartPanel.setBackground(new Color(240, 231, 252));
        bg.add(chartPanel); //ใส่ลง bg

        ChartPanel chartPane2 = new ChartPanel(barChart);//ย้อนหลังปีแรก
        chartPane2.setBounds(600, 420, 470, 330);
        chartPane2.setBackground(new Color(204, 209, 255));
        bg.add(chartPane2); //ใส่ลง bg

        // สร้างกราฟวงกลม
        PieChartPanel pieChart = new PieChartPanel("ผลการคำนวณภาษี");
        pieChart.setBounds(0, 105, 650, 300);
        pieChart.setBackground(new Color(240, 231, 252));
        
        
        // เพิ่มข้อมูล      
        pieChart.addSlice("คงเหลือส่วนต่าง", 45.5, new Color(255, 99, 132));
        pieChart.addSlice("ภาษีที่ต้องจ่าย", 30.2, new Color(54, 162, 235));
        pieChart.addSlice("ส่วนลดหย่อน", 15.8, new Color(255, 205, 86));
        

        // เพิ่มลงใน bg
        headbar.add(back);
        headbar.add(Texthead);
        headbar.add(newround);
        bg.add(year);
        bg.add(pieChart);//Insert Circle Chart
        bg.add(headbar);
        bg.add(totalincome);
        bg.add(cutTax);
        bg.add(payTax);

        // เพิ่ม bg ลงใน JFrame
        add(bg);

        setVisible(true);
    }

    public class kobmon implements Border {

        private int radius;

        kobmon(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
           return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
        
    }

   
    public static void main(String[] args) {
        new Result();
    }
}

