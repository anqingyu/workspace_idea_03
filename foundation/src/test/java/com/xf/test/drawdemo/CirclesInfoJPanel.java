package com.xf.test.drawdemo;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

/**
 * @Description: 用户输入圆的半径（单位为像素数），点击“开始绘制”按钮，程序将画出相应大小的圆，并绘制其直径、周长及面积信息。
 * @Author: xiefu
 * @Date: 2019/9/4 23:58
 */
public class CirclesInfoJPanel extends JPanel{
    private JLabel radiusJLabel;
    private JTextField radiusJTextField;
    private JButton drawJButton;
    private JPanel topJPanel;
    private float radius;
    private float diameter;
    private float perimeter;
    private float area;
    private boolean initFlag;

    CirclesInfoJPanel(){
        initFlag = true;	//初始化状态
        radiusJLabel = new JLabel("圆的半径：");
        radiusJTextField = new JTextField("0.0",10);
        drawJButton = new JButton("开始绘制");
        topJPanel = new JPanel();

        drawJButton.addActionListener(new ActionListener() {

                                          public void actionPerformed(ActionEvent event) {
                                              initFlag = false; //用户触发了动作，结束初始化状态
                                              repaint();	//绘图
                                          }
                                      }

        );

        topJPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topJPanel.add(radiusJLabel);
        topJPanel.add(radiusJTextField);
        topJPanel.add(drawJButton);
        this.add(topJPanel,BorderLayout.NORTH);

    }

    public void paint(Graphics g)
    {


        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        double width = getWidth(); // total width
        double height = getHeight(); // total height
        int rRed;
        int rGreen;
        int rBlue;
        radius =Float.parseFloat(radiusJTextField.getText());
        diameter = 2 * radius;
        perimeter = (float) (2 * Math.PI * radius);
        area = (float) (Math.PI * Math.pow(radius,2));

        //Color of arc, below color is called "Taibao Lan"
        rRed = 21;
        rGreen = 101;
        rBlue = 192;
        Color color=new Color(rRed, rGreen, rBlue);

        //画1个圆，并输出其直径、周长、面积
        if (! initFlag){	//初始界面中不显示以下内容
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(5));	//设置画笔的粗细
            g2d.draw(new Ellipse2D.Double(width/2-radius, height/2-radius,
                    2*radius, 2*radius));
            g2d.drawString("直径："+String.format("%.2f", diameter),(int)(width/2+radius+20),
                    (int)(height/2-15));	//输出直径大小
            g2d.drawString("周长："+String.format("%.2f", perimeter),(int)(width/2+radius+20),
                    (int)(height/2));	//输出周长大小
            g2d.drawString("面积："+String.format("%.2f", area),(int)(width/2+radius+20),
                    (int)(height/2+15));	//输出面积大小

        }

    }

    public static void main(String[] args) {
        // create a panel that contains our drawing
        CirclesInfoJPanel panel = new CirclesInfoJPanel();

        // create a new frame to hold the panel
        JFrame application = new JFrame();
        application.setTitle("绘制圆");

        // set the frame to exit when it is closed
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        application.add(panel,BorderLayout.CENTER); // add the panel to the frame
        application.setSize(586, 586); // set the size of the frame
        application.setVisible(true); // make the frame visible
    }
}
