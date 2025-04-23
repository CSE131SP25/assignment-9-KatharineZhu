package assignment9;

import java.util.LinkedList;

public class Snake {
    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;
    
    public Snake() {
        segments = new LinkedList<>();
        // 初始蛇头位置在画面中心，避免 (0,0) 导致蛇头不可见
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = MOVEMENT_SIZE; // 初始默认向右移动
        deltaY = 0;
    }
    
    public void changeDirection(int direction) {
        // 防止180度掉头（例如：当前向右时不能直接向左）
        if ((direction == 3 && deltaX == MOVEMENT_SIZE) || 
            (direction == 4 && deltaX == -MOVEMENT_SIZE) ||
            (direction == 1 && deltaY == -MOVEMENT_SIZE) || 
            (direction == 2 && deltaY == MOVEMENT_SIZE)) {
            return;
        }
        
        if (direction == 1) {      // 上
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { // 下
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { // 左
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { // 右
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }
    
    public void move() {
        // 从蛇尾开始更新位置（避免覆盖）
        for (int i = segments.size() - 1; i > 0; i--) {
            BodySegment prev = segments.get(i - 1);
            BodySegment current = segments.get(i);
            current.setX(prev.getX());
            current.setY(prev.getY());
        }
        
        // 更新蛇头位置
        BodySegment head = segments.get(0);
        head.setX(head.getX() + deltaX);
        head.setY(head.getY() + deltaY);
    }
    
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }
    
    public boolean eatFood(Food f) {
        BodySegment head = segments.get(0);
        double distance = Math.sqrt(
            Math.pow(head.getX() - f.getX(), 2) + 
            Math.pow(head.getY() - f.getY(), 2)
        );
        
        // 判断蛇头是否碰到食物（距离 ≤ 蛇身半径 + 食物半径）
        if (distance <= SEGMENT_SIZE + Food.FOOD_SIZE) {
            // 在蛇头当前位置添加一个新身体段（模拟生长）
            segments.addFirst(new BodySegment(head.getX(), head.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }
    
    public boolean isInbounds() {
        BodySegment head = segments.get(0);
        double x = head.getX();
        double y = head.getY();
        return x >= 0 && x <= 1 && y >= 0 && y <= 1;
    }
}