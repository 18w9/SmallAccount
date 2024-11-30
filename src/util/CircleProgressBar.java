package util;


import javax.swing.*;
import java.awt.*;

/**
 * 工具类 环形图
 */

public class CircleProgressBar extends JPanel {
    // 进度条的最小值
    private int minimumProgress;
    // 进度条的最大值
    private int maximumProgress;
    // 当前进度值
    private int progress;
    // 进度文本，显示进度的百分比
    private String progressText;
    // 进度条的背景颜色
    private Color backgroundColor;
    // 进度条的前景色，即进度条的填充颜色
    private Color foregroundColor;

    //CircleProgressBar 的构造函数，初始化进度条的基本属性
    public CircleProgressBar() {
        // 设置进度条的默认最小值和最大值
        minimumProgress = 0;
        maximumProgress = 100;
        // 初始化进度文本为 0%
        progressText = "0%";
    }

    //重写 JPanel 的 paint 方法，用于绘制环形进度条
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2d = (Graphics2D) g;
        // 开启抗锯齿，使绘制的图形更平滑
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 计算绘制进度条的起始位置、大小和字体大小
        int x, y, width, height, fontSize;
        if (getWidth() >= getHeight()) {
            x = (getWidth() - getHeight()) / 2 + 25;
            y = 25;
            width = getHeight() - 50;
            height = getHeight() - 50;
            fontSize = getWidth() / 8;
        } else {
            x = 25;
            y = (getHeight() - getWidth()) / 2 + 25;
            width = getWidth() - 50;
            height = getWidth() - 50;
            fontSize = getHeight() / 8;
        }

        // 绘制背景圆
        graphics2d.setStroke(new BasicStroke(20.0f));
        graphics2d.setColor(backgroundColor);
        graphics2d.drawArc(x, y, width, height, 0, 360);

        // 绘制前景圆，即实际的进度
        graphics2d.setColor(foregroundColor);
        graphics2d.drawArc(x, y, width, height, 90,
                -(int) (360 * ((progress * 1.0) / (maximumProgress - minimumProgress))));

        // 设置字体并计算文本位置
        graphics2d.setFont(new Font("黑体", Font.BOLD, fontSize));
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        int digitalWidth = fontMetrics.stringWidth(progressText);
        int digitalAscent = fontMetrics.getAscent();
        // 绘制进度文本
        graphics2d.setColor(foregroundColor);
        graphics2d.drawString(progressText, getWidth() / 2 - digitalWidth / 2, getHeight() / 2 + digitalAscent / 2);
    }

    //获取当前进度值
    public int getProgress() {
        return progress;
    }

    //设置当前进度值，并更新进度条
    public void setProgress(int progress) {
        if (progress >= minimumProgress && progress <= maximumProgress)
            this.progress = progress;
        if (progress > maximumProgress)
            this.progress = maximumProgress;

        this.progressText = String.valueOf(progress + "%");
        this.repaint();
    }

    //获取进度条的背景颜色
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    //设置进度条的背景颜色，并更新进度条
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.repaint();
    }

    //获取进度条的前景色
    public Color getForegroundColor() {
        return foregroundColor;
    }

    //设置进度条的前景色，并更新进度条
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        this.repaint();
    }
}