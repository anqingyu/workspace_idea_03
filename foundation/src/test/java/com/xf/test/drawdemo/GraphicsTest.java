package com.xf.test.drawdemo;

import javax.swing.*;
import java.awt.*;

/**
 * @Description: 使用 Graphics 类绘制线段、矩形、椭圆/圆弧/扇形、图片、文本
 * @Author: xiefu
 * @Date: 2019/9/4 19:43
 */
public class GraphicsTest {
    public static void main(String[] args) {
        /*
         * 在 AWT 的事件队列线程中创建窗口和组件, 确保线程安全,
         * 即 组件创建、绘制、事件响应 需要处于同一线程。
         */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                MyFrame frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    /**
     * 窗口
     */
    public static class MyFrame extends JFrame {

        public static final String TITLE = "Java图形绘制";

        public static final int WIDTH = 250;
        public static final int HEIGHT = 300;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            // 设置 窗口标题 和 窗口大小
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            // 设置窗口的内容面板
            MyPanel panel = new MyPanel(this);
            setContentPane(panel);
        }

    }

    /**
     * 内容面板
     */
    public static class MyPanel extends JPanel {

        private MyFrame frame;

        public MyPanel(MyFrame frame) {
            super();
            this.frame = frame;
        }

        /**
         * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
         * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
         * 系统再次调用该方法绘制更新 JPanel 的内容。
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容

            /* 自行打开下面注释查看各绘制效果 */

            // 1. 线段 / 折线
//            drawLine(g);

            // 2. 矩形 / 多边形
            // drawRect(g);

            // 3. 圆弧 / 扇形
             drawArc(g);

            // 4. 椭圆
            // drawOval(g);

            // 5. 图片
            // drawImage(g);

            // 6. 文本
            // drawString(g);
        }

        /**
         * 1. 线段 / 折线
         */
        private void drawLine(Graphics g) {
            frame.setTitle("1. 线段 / 折线");

            // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
            // 这里必须使用副本, 避免影响到 Graphics 原有的设置
            Graphics2D g2d = (Graphics2D) g.create();

            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置画笔颜色
            g2d.setColor(Color.RED);

            // 1. 两点绘制线段: 点(20, 50), 点(200, 50)
            g2d.drawLine(50, 50, 200, 50);

            // 2. 多点绘制折线: 点(50, 100), 点(100, 130), 点(150, 70), 点(200, 100)
            int[] xPoints = new int[] { 50, 100, 150, 200 };
            int[] yPoints = new int[] { 100, 120, 80, 100 };
            int nPoints = 4;
            g2d.drawPolyline(xPoints, yPoints, nPoints);

            // 3. 两点绘制线段（设置线宽为5px）: 点(50, 150), 点(200, 150)
            BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
            g2d.setStroke(bs1);
            g2d.drawLine(50, 150, 200, 150);

            // 4. 绘制虚线: 将虚线分为若干段（ 实线段 和 空白段 都认为是一段）, 实线段 和 空白段 交替绘制,
            //             绘制的每一段（包括 实线段 和 空白段）的 长度 从 dash 虚线模式数组中取值（从首
            //             元素开始循环取值）, 下面数组即表示每段长度分别为: 5px, 10px, 5px, 10px, ...
            float[] dash = new float[] { 5, 10 };
            BasicStroke bs2 = new BasicStroke(
                    1,                      // 画笔宽度/线宽
                    BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_MITER,
                    10.0f,
                    dash,                   // 虚线模式数组
                    0.0f
            );
            g2d.setStroke(bs2);
            g2d.drawLine(50, 200, 200, 200);

            // 自己创建的副本用完要销毁掉
            g2d.dispose();
        }

        /**
         * 2. 矩形 / 多边形
         */
        private void drawRect(Graphics g) {
            frame.setTitle("2. 矩形 / 多边形");
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);

            // 1. 绘制一个矩形: 起点(30, 20), 宽80, 高100
            g2d.drawRect(30, 20, 80, 100);

            // 2. 填充一个矩形
            g2d.fillRect(140, 20, 80, 100);

            // 3. 绘制一个圆角矩形: 起点(30, 150), 宽80, 高100, 圆角宽30, 圆角高30
            g2d.drawRoundRect(30, 150, 80, 100, 30, 30);

            // 4. 绘制一个多边形(收尾相连): 点(140, 150), 点(180, 250), 点(220, 200)
            int[] xPoints = new int[] { 140, 180, 220};
            int[] yPoints = new int[] { 150,  250, 200};
            int nPoints = 3;
            g2d.drawPolygon(xPoints, yPoints, nPoints);

            g2d.dispose();
        }

        /**
         * 3. 圆弧 / 扇形
         */
        private void drawArc(Graphics g) {
            frame.setTitle("3. 圆弧 / 扇形");
            Graphics2D g2d = (Graphics2D) g.create();

            /**
             *  RenderingHints 类定义了多种着色微调，它们存储在一个映射集的 Graphics2D 对象里。setRenderingHint() 方法的参数是一个键以及对应的键值。在我们的代码中，第一个参数是代表 alpha 合成微调的键，第二个参数是该微调的值。该微调的其它可能的值有 VALUE_ALPHA_INTERPOLATION_DEFAULT，代表平台缺省值；以及 VALUE_ALPHA_INTERPOLATION_SPEED，代表追求速度而不是质量。
             *
             * 键描述KEY_ANTIALIASING决定是否使用抗锯齿。当着色有倾斜角度的线时，通常会得到一组阶梯式的像素排列，使这条线看上去不平滑，经常被称为 锯齿状图形。抗锯齿是一种技术，它设置有倾斜角度的线的像素亮度，以使线看起来更平滑。因此，这个微调是用来决定在着色有倾斜角度的线时是否在减少锯齿状图形上花费时间。可能的值有 VALUE_ANTIALIAS_ON, _OFF 或 _DEFAULT。KEY_COLOR_RENDERING控制颜色着色的方式。可能的值有 VALUE_COLOR_RENDER_SPEED, _QUALITY 或 _DEFAULT。KEY_DITHERING控制如何处理抖动。抖动是用一组有限的颜色合成出一个更大范围的颜色的过程，方法是给相邻像素着色以产生不在该组颜色中的新的颜色幻觉。可能的值有 VALUE_DITHER_ENABLE, _DISABLE 或 _DEFAULT。KEY_FRACTIONALMETRICS控制显示文本的质量。可能的值有 VALUE_FRACTIONALMETRICS_ON, _OFF 或 _DEFAULT。KEY_INTERPOLATION确定怎样做内插
             */
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.RED);

            // 1. 绘制一条圆弧: 椭圆的外切矩形 左上角坐标为(0, 0), 宽100, 高100,
            //                弧的开始角度为0度, 需要绘制的角度数为-90度,
            //                椭圆右边水平线为0度, 逆时针为正角度, 顺时针为负角度
            g2d.drawArc(0, 0, 200, 200, 0, -360);

            // 2. 绘制一个圆: 圆的外切矩形 左上角坐标为(120, 20), 宽高为100
            g2d.drawArc(120, 20, 100, 100, 0, 360);

            g2d.setColor(Color.GRAY);

            // 3. 填充一个扇形
            g2d.fillArc(80, 150, 100, 100, 90, 270);

            g2d.dispose();
        }

        /**
         * 4. 椭圆 (实际上通过绘制360度的圆弧/扇形也能达到绘制圆/椭圆的效果)
         */
        private void drawOval(Graphics g) {
            frame.setTitle("4. 椭圆");
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.RED);

            // 1. 绘制一个圆: 圆的外切矩形 左上角坐标为(0, 0), 宽高为100
            g2d.drawOval(0, 0, 100, 100);

            g2d.setColor(Color.GRAY);

            // 2. 填充一个椭圆
            g2d.fillOval(120, 100, 100, 150);

            g2d.dispose();
        }

        /**
         * 5. 图片
         */
        private void drawImage(Graphics g) {
            frame.setTitle("5. 图片");
            Graphics2D g2d = (Graphics2D) g.create();

            // 从本地读取一张图片
            String filepath = "demo.jpg";
            Image image = Toolkit.getDefaultToolkit().getImage(filepath);

            // 绘制图片（如果宽高传的不是图片原本的宽高, 则图片将会适当缩放绘制）
            g2d.drawImage(image, 50, 50, image.getWidth(this), image.getHeight(this), this);

            g2d.dispose();
        }

        /**
         * 6. 文本
         */
        private void drawString(Graphics g) {
            frame.setTitle("6. 文本");
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 设置字体样式, null 表示使用默认字体, Font.PLAIN 为普通样式, 大小为 25px
            g2d.setFont(new Font(null, Font.PLAIN, 25));

            // 绘制文本, 其中坐标参数指的是文本绘制后的 左下角 的位置
            // 首次绘制需要初始化字体, 可能需要较耗时
            g2d.drawString("Hello World!", 20, 60);
            g2d.drawString("你好, 世界!", 20, 120);

            g2d.dispose();
        }

    }
}
