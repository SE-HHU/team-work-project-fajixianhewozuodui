package com.example.appdemo.game;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.appdemo.R;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final int GAME_START = 200;    //游戏开始
    public static final int GAME_STOP = 400;    //游戏开始
    public static final int GAME_OVER = 500;    //游戏开始
    public static final int GAME_READY = 100;    //游戏准备
    public static int gameStatus;  //游戏状态

    private Bitmap background, background2, ball, xiaoren, xiaoren2;
    private Canvas mCanvas;  //canvas对象

    private int width, height;
    private SurfaceHolder surfaceHolder;
    private boolean isDraw = true;  //绘制线程开关

    private Body balloon;
    private Body people;
    private List<Body> ballloons;   //气球集合
    private Body[] backgrounds;      //地板数组

    int Score = 0;                  //分数
    private GameListener gameListener;

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

//    int ballDecm;                   //气球的间隔, 这里是由于管道是分上下的，所以才会有间隔

    public GameView(Context context) {
        super(context);
        initView(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //通过BitmapFactory方法来创建Bitmap对象，此时这些图片都转化为了Bitmap对象
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        background2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.background2);
        xiaoren = BitmapFactory.decodeResource(context.getResources(), R.drawable.feidie);
        xiaoren2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.feidie);
        ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

    }

    private void initData() {
        gameStatus = GAME_READY;

        width = getMeasuredWidth();
        height = getMeasuredHeight();
        //得到缩放之后的背景图
        background = getRatioBitmap(background, (float) width/background.getWidth(), (float) height/background.getHeight());
        background2 = getRatioBitmap(background2, (float) width/background2.getWidth(), (float) height/background2.getHeight());
        ball = getRatioBitmap(ball, 0.65f, 0.65f);
        xiaoren = getRatioBitmap(xiaoren, 0.2f, 0.2f);
        xiaoren2 = getRatioBitmap(xiaoren2, 0.2f, 0.2f);

        //初始化绘制对象
        people = new Body(xiaoren, (width - xiaoren.getWidth())/2,
                (height - xiaoren.getHeight())/2, xiaoren.getWidth(), xiaoren.getHeight());
        backgrounds = createGrounds();  //得到了背景集合
        ballloons = new ArrayList<>();   //气球集合
//        ballDecm = people.getW()*2;      //气球间的间隔是小人宽度的两倍,这个是没有用的，因为管道才分上下

        new Thread(this).start(); //开启绘制线程

    }

    public void reSet(){
        isDown = false;
//        CreatBallTime = 0;
        upStartTime = 0;
        people.setX((width - xiaoren.getWidth())/2);
        people.setY(height/2);
//        ballloons = new ArrayList<>();
        changeBitmapTime = 0;
        Score = 0;
        gameStatus = GAME_START;

        if(gameListener != null){
            gameListener.gameReady();
        }
    }



    //得到两张一样的背景图，用于以后的界面刷新
    private Body[] createGrounds(){
        Body body = new Body(background, 0, 0, background.getWidth(), background.getHeight());
        Body body2 = new Body(background2, body.getX() + body.getW(), 0, background.getWidth(), background.getHeight());
        return new Body[]{body,body2};
    }

    //获取dx dy缩放比例后的 bitmap
    public Bitmap getRatioBitmap(Bitmap bitmap, float dx, float dy){
        return Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*dx), (int)(bitmap.getHeight()*dy), true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        initData();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        //销毁时关闭线程
        isDraw = false;
    }

    @Override
    public void run() {
        while(isDraw){
            drawMain();
        }
    }

    private void drawMain() {   //这里绘制整个屏幕
        //先获取Canvas
        mCanvas = surfaceHolder.lockCanvas();

        if(gameStatus == GAME_START){
            peopleMove();       //小人动作切换
            downPeople();       //小人的跳跃与下落
//            creatBall();        //生成气球
//            moveBall();         //气球移动
            moveGround();       //背景移动
        }
        drawBackgrounds();   //先绘制背景图
        drawPeople();       //绘制小人
//        drawBalls();        //绘制气球
        checkOver();        //检测失败

        surfaceHolder.unlockCanvasAndPost(mCanvas);  //绘制完成后提交

    }

    private void checkOver() {
        //检测是否落地
        if(people.getY() + people.getH() >= height){
            //代表落出屏幕
            gameStatus = GAME_OVER;     //游戏结束
            if(gameListener != null){
                gameListener.gameOver();
            }
        }
        else{
            for(Body body: ballloons){
                if(CollsionRect(people.getX(), people.getY(), people.getW(), people.getH(),
                        body.getX(), body.getY(), body.getW(), body.getH())){
                    //碰撞了
                    if(gameListener != null){
                        gameListener.gameOver();  //游戏停止，播放死亡动画 然后结束
                    }

                }
            }
        }

    }

    //碰撞检测
    public boolean CollsionRect(int x1, int y1, int w1, int h1,
                                int x2, int y2, int w2, int h2){
        if(x1>=x2 && x1>=x2+w2){    //右侧不碰撞
            return false;
        }else if(x1<=x2 && x1+w1<=x2){
            return false;
        }else if(y1>=y2 && y1>=y2+h2){
            return false;
        }else if(y1<=y2 && y1+h1<=y2){
            return false;
        }

        //当不满足所有 不碰撞 条件的时候, 那就是碰撞了
        return true;
    }

    private void moveBall() {
        List<Body> bodies = new ArrayList<>();
        for(Body body : ballloons){
            body.setX(body.getX() - moveSpeed); //移动速度与背景图相同
            if(body.getX() + body.getW() + 10 < 0){
                //代表移出屏幕了
                bodies.add(body);
            }
            //记录分数   这是小鸟的判断逻辑, 如果在管道里面穿过去 就记录分数+1
            if(!body.isScore && body.getX() + body.getW() / 2 < people.getX()){
                //分数+1
                GamePage.Score++;
                if(gameListener != null){
                    gameListener.addScore();
                }
            }
        }
        //删除移除屏幕的障碍物
        ballloons.removeAll(bodies);
    }

    int CreatBallTime = 0;   //生成气球的时间
    private void creatBall() {
        CreatBallTime++;
        if(CreatBallTime >= 80){   //控制气球之间的间隔
            CreatBallTime = 0;
            //每80帧生产一个气球 气球的高度随机，最小值为10(比较高的位置了), 最大值为60;
            int ranH = (int) (Math.random()*50) + 10;
            balloon = new Body(ball, width+10, ranH, ball.getWidth(), ball.getHeight());
            ballloons.add(balloon);
        }
    }

    private Bitmap getBallBitmap(int h){ //生成气球图片  h为图片高度
        // 创建一个新的图片   宽度为小人的宽度, 高度随机
        Bitmap bitmap = Bitmap.createBitmap(people.getW(), h, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);

        //这里高度就不进行变化了, 其实宽度也可以不变的
        Bitmap BallBody = getRatioBitmap(ball, 1f, 1f);
        Matrix matrix = new Matrix();
        matrix.setTranslate(0, 0);
        canvas.drawBitmap(BallBody,matrix, null);

        return bitmap;
    }


    private void drawBackgrounds() {
        mCanvas.drawBitmap(backgrounds[0].getBitmap(), backgrounds[0].getX(), backgrounds[0].getY(), null);
        mCanvas.drawBitmap(backgrounds[1].getBitmap(), backgrounds[1].getX(), backgrounds[1].getY(), null);
    }

    private void drawBalls(){
        for(Body body : ballloons){
            mCanvas.drawBitmap(body.getBitmap(), body.getX(), body.getY(), null);    //绘制气球
        }
    }


    int moveSpeed = 10;   //每一帧移动10
    private void moveGround() {
        if(backgrounds[0].getX() + backgrounds[0].getW() > 0){ //没有移出屏幕
            backgrounds[0].setX(backgrounds[0].getX() - moveSpeed);
        }else{ //如果移出了屏幕，则设置 X 为另一个 背景的 x+w
            backgrounds[0].setX(backgrounds[1].getX() + backgrounds[1].getW() - 20);
        }
        if(backgrounds[1].getX() + backgrounds[1].getW() > 0){ //没有移出屏幕
            backgrounds[1].setX(backgrounds[1].getX() - moveSpeed);
        }else{ //如果移出了屏幕，则设置 X 为另一个 背景的 x+w
            backgrounds[1].setX(backgrounds[0].getX() + backgrounds[0].getW() - 20);
        }
    }


    public static boolean isDown = true;  //跳跃、下落开关
    int downSpeed = 3;          //下落速度 每一帧下落的坐标
    int upStartTime = 0;     //已经跳跃时间
    int upTime = 20;         //需要跳跃时间
    public static int upSpeed = 12;             //每一帧跳跃距离
    private void downPeople() {
        if(isDown){
            //定义下落动画
            people.setY(people.getY() + downSpeed);
        }else{
            //定义跳跃动画
            if(upTime - upStartTime > 0){
                if(people.getY() >= 0){
                    people.setY(people.getY() - upSpeed);   //Y是上面为0, -12是向上移动12
                }
                upStartTime++;
            }else{ //跳跃结束
                isDown = true;
                upStartTime = 0;
            }
        }
    }

    int peopleBitmapData = 1; //切换图片控制
    int changeBitmapTime = 0;

    //控制人物的动作变换，这个应该是加入算数判断题的逻辑
    private void peopleMove() {
        changeBitmapTime++;
        if(changeBitmapTime >= 10){  //每10帧改变一次图片
            switch (peopleBitmapData){
                case 1:
                    people.setBitmap(xiaoren);
                    break;
                case 2:
                    people.setBitmap(xiaoren2);
                    break;
            }
            changeBitmapTime = 0;
            peopleBitmapData = peopleBitmapData==2 ? 1 : peopleBitmapData+1;
        }

    }

    private void drawPeople() {
        Matrix matrix = new Matrix();
        matrix.setTranslate(people.getX(), people.getY());
        mCanvas.drawBitmap(people.getBitmap(), matrix, null);
    }







    interface GameListener{ //与Activity交互的接口
        void addScore();
        void gameOver();
        void gameReady();
    }


    class Body{
        Bitmap bitmap;
        int x, y, w, h;    //xy坐标 宽高
        boolean isScore = false;   //是否已经计分, 仅障碍物用到

        public boolean isScore() {
            return isScore;
        }

        public Body(Bitmap bitmap, int x, int y, int w, int h) {
            this.bitmap = bitmap;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }
    }







}
