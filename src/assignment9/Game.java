package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Food food;
    private Snake snake;
    
    public Game() {
        // 初始化画布和游戏对象
        StdDraw.setXscale(0, 1);          // 明确设置坐标范围
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();   // 启用双缓冲
        StdDraw.clear(Color.BLACK);        // 清屏为黑色背景
        
        food = new Food();
        snake = new Snake();
    }
    
    public void play() {
        // 等待空格键开始游戏
        while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(0.5, 0.8, "Press SPACE to Start!");
            StdDraw.show();
        }
        StdDraw.clear(Color.BLACK);

        // 主游戏循环
        while (snake.isInbounds()) {
            int dir = getKeypress();      // 获取键盘输入
            snake.changeDirection(dir);   // 更新蛇的方向
            snake.move();                // 移动蛇
            
            // 检测是否吃到食物
            if (snake.eatFood(food)) {
                food = new Food();        // 生成新食物
            }
            
            updateDrawing();             // 更新画面
            StdDraw.pause(100);           // 控制游戏速度
        }
        
        // 游戏结束提示
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.show();
    }
    
    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) return 1;  // 上
        if (StdDraw.isKeyPressed(KeyEvent.VK_S)) return 2;  // 下
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) return 3;  // 左
        if (StdDraw.isKeyPressed(KeyEvent.VK_D)) return 4;  // 右
        return -1;  // 无按键
    }
    
    private void updateDrawing() {
        StdDraw.clear(Color.BLACK);  // 清屏（黑色背景）
        snake.draw();                // 绘制蛇
        food.draw();                 // 绘制食物
        StdDraw.show();              // 显示缓冲内容
    }
    
    public static void main(String[] args) {
        new Game().play();
    }
}